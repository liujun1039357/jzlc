package com.zl.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.LoginInfoException;
import com.zl.mapper.LogininfoMapper;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.ConsumerRecord;
import com.zl.pojo.Controller;
import com.zl.pojo.Notice;
import com.zl.service.ILogininfoService;
import com.zl.util.UserContext;

@Service
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
	private LogininfoMapper logininfoMapper;

	/**
	 * 管理员登录
	 */
	@Override
	public boolean login(String username, String password, String ip) throws LoginInfoException {
		ConsumerRecord cr = new ConsumerRecord();
		Controller current = logininfoMapper.queryByUsernameAndPassword(username, password);
		cr.setIp(ip);
		cr.setLoginTime(new Date());
		cr.setLoginFlag(ConsumerRecord.FAIL);

		if (current != null) {
			cr.setConsumerId(current.getJobNumber());
			if (current.getStates() == Controller.DELETE) {
				throw new LoginInfoException("账户已删除，联系领导");
			}
			UserContext.setCurrent(current);
			cr.setLoginFlag(ConsumerRecord.SUCCESS);
		}
		logininfoMapper.saveConsumerRecord(cr);
		return current != null;
	}

	/* 保存公告 */
	@Override
	public void gg(Notice notice) {
		logininfoMapper.savenotice(notice);
	}

	/**
	 * 管理员登录记录查询
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ConsumerRecord queryById(String id) {
		return logininfoMapper.queryById(id);
	}

	// 查询用户数
	@Override
	public int queryCountConsumerInfo() {
		return logininfoMapper.queryCountConsumerInfo();
	}

	// 查询所有用户详情信息
	public List<ConsumerInfo> queryAll(ConsumerInfoCondition cc) {
		return logininfoMapper.queryByAllConsumerInfo(cc);
	}

	// 查询企业数
	@Override
	public int queryCountBusinessInfo() {
		return logininfoMapper.queryCountBusinessInfo();
	}

	// 查询产品数
	@Override
	public int queryCountProduct() {
		return logininfoMapper.queryCountProduct();
	}

	@Override
	public ConsumerInfo selectYonghu(String id) {
		return logininfoMapper.selectYonghu(id);
	}
}
