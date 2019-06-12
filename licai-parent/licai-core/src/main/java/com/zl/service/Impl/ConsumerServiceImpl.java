package com.zl.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ConsumerInfoMapper;
import com.zl.mapper.ConsumerMapper;
import com.zl.mapper.ConsumerRecordMapper;
import com.zl.pojo.Consumer;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.ConsumerRecord;
import com.zl.service.IAuthentication;
import com.zl.service.IConsumerService;
import com.zl.util.BitStateUtil;
import com.zl.util.UserContext;

@Service
public class ConsumerServiceImpl implements IConsumerService {
	@Autowired
	private ConsumerMapper consumerMapper;
	@Autowired
	private ConsumerRecordMapper consumerRecordMapper;
	@Autowired
	private IAuthentication authentication;
	@Autowired
	private ConsumerInfoMapper consumerInfoMapper;

	@Override
	public boolean personalLogin(String consumerName, String password, String ip) throws JZLCException {
		Consumer consumer = consumerMapper.personalLogin(consumerName, password);
		if (consumer == null) {
			return false;
		}
		ConsumerRecord consumerRecord = new ConsumerRecord();
		consumerRecord.setConsumerId(consumer.getConsumerId());
		consumerRecord.setIp(ip);
		consumerRecord.setLoginTime(new Date());
		
		if (consumer.getAccountsFlag() == Consumer.CANCELLED) {
			consumerRecord.setLoginFlag(ConsumerRecord.FAIL);
			consumerRecordMapper.insertLoginRecord(consumerRecord);
			throw new JZLCException("该用户已注销,请联系管理员!");
		} else if (consumer.getAccountsFlag() == Consumer.FROZEN) {
			consumerRecord.setLoginFlag(ConsumerRecord.FAIL);
			consumerRecordMapper.insertLoginRecord(consumerRecord);
			throw new JZLCException("该用户已冻结,请联系管理员!");
		} /*else if (UserContext.getLogininfo() != null
				&& UserContext.getLogininfo().getConsumerName().equals(consumer.getConsumerName())) {
			consumerRecord.setLoginFlag(ConsumerRecord.FAIL);
			consumerRecordMapper.insertLoginRecord(consumerRecord);
			throw new JZLCException("该用户已登录!");
		}*/
		if (consumer.getAccountsFlag() == Consumer.NORMAL) {
			consumerRecord.setLoginFlag(ConsumerRecord.SUCCESS);
			consumerRecordMapper.insertLoginRecord(consumerRecord);
		}
		UserContext.setLogininfo(consumer);
		return true;
	}

	@Override
	public boolean checkConsumerName(String consumerName) throws JZLCException {
		return consumerMapper.checkConsumerName(consumerName) <= 0;
	}
	
	@Override
	public boolean checkTel(String tel) throws JZLCException {
		return consumerInfoMapper.checkTel(tel) <= 0;
	}
	
	@Override
	public boolean personalRegister(String consumerName, String tel, String password, String verifyCode,
			String ip) throws JZLCException {
		boolean bindPhone = authentication.bindPhone(tel, verifyCode);
		if(!bindPhone) {
			return false;
		}
		/**防止攻击,后台再次判断用户是否已存在*/
		if(!checkConsumerName(consumerName)){
			throw new JZLCException("用户已存在!");
		}
		
		Consumer consumer = new Consumer();
		consumer.setConsumerName(consumerName);
		consumer.setPassword(password);
		consumer.setCreateTime(new Date());
		consumer.setAccountsFlag(Consumer.NORMAL);
		consumerMapper.insertConsumer(consumer);
		
		ConsumerInfo consumerInfo = new ConsumerInfo();
		String consumerId = consumerMapper.queryConsumerId(consumerName);
		consumerInfo.setConsumerId(consumerId);  
		consumerInfo.setTel(tel);	
		consumerInfo.setBitState(consumerInfo.addState(BitStateUtil.OPEN_MSG_AUTH));
		consumerInfoMapper.insertConsumerInfo(consumerInfo);
		
		ConsumerRecord consumerRecord = new ConsumerRecord();
		consumerRecord.setConsumerId(consumerId);
		consumerRecord.setIp(ip);
		consumerRecord.setLoginTime(new Date());
		consumerRecord.setLoginFlag(ConsumerRecord.SUCCESS);
		consumerRecordMapper.insertLoginRecord(consumerRecord);
		
		consumer.setConsumerId(consumerId);
		UserContext.setLogininfo(consumer);
		return true;
	}

	@Override
	public void cancellation(String consumerId) throws JZLCException {
		int cancel = consumerMapper.updateConsumerFlag(consumerId,Consumer.CANCELLED,new Date());
		if(cancel<=0) {
			throw new JZLCException("注销失败!");
		}
	}

	@Override
	public boolean loginPasswordUpdate(String password, String newPassword) throws JZLCException {
		System.out.println(consumerMapper.personalLogin(UserContext.getLogininfo().getConsumerName(),password));
		System.out.println(UserContext.getLogininfo().getConsumerName()+"------"+password);
		if(consumerMapper.personalLogin(UserContext.getLogininfo().getConsumerName(), password) == null) {
			return false;
		}
		consumerMapper.updateLoginPassword(UserContext.getLogininfo().getConsumerId(),newPassword,new Date());
		return true;
	}


}
