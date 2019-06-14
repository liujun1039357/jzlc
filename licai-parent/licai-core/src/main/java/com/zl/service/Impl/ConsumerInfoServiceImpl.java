/**
 * 
 */
package com.zl.service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.AuthMapper;
import com.zl.mapper.BankCardInfoMapper;
import com.zl.mapper.CardRuleMapper;
import com.zl.mapper.ConsumerInfoMapper;
import com.zl.pojo.BankcardInfo;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.MailInfo;
import com.zl.pojo.RealAuthShow;
import com.zl.service.IAuthentication;
import com.zl.service.IConsumerInfoService;
import com.zl.util.BitStateUtil;
import com.zl.util.UserContext;

/**
 * @author ivy
 *
 */
@Service
public class ConsumerInfoServiceImpl implements IConsumerInfoService{
	@Autowired
	private IAuthentication authentication;
	@Autowired
	private ConsumerInfoMapper consumerInfoMapper;
	@Autowired
	private AuthMapper authMapper;
	@Autowired
	private CardRuleMapper cardRuleMapper;
	@Autowired
	private BankCardInfoMapper bankCardInfoMapper;
	
	@Override
	public void personalEmailBind(String email, String emailCode) throws JZLCException {
		MailInfo mailInfo = UserContext.getMailCodeInSession();
		if(mailInfo == null) {
			throw new JZLCException("系统忙!稍后重试...");
		}
		if(authentication.isOvertime()) {
			throw new JZLCException("已超时,请稍后重试");
		}
		if(!authentication.bindEmail(email, emailCode)) {
			throw new JZLCException("验证码错误!");
		}
		//验证通过,将邮箱认证信息更新到用户信息表
		consumerInfoMapper.updataEmailInfo(UserContext.getLogininfo().getConsumerId(),email,new ConsumerInfo().addState(BitStateUtil.OPEN_EMAIL_AUTH));
	}

	@Override
	public String queryEmail() throws JZLCException {
		return consumerInfoMapper.queryEmail(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public String queryIdCard() throws JZLCException {
		return consumerInfoMapper.queryIdCard(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public String queryTel() throws JZLCException {
		System.out.println(UserContext.getLogininfo().getConsumerId());
		return consumerInfoMapper.queryTel(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public RealAuthShow queryRealAuthInfo() {
		return consumerInfoMapper.queryRealAuthInfo(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public String queryRealName() throws JZLCException {
		return consumerInfoMapper.queryRealName(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public long queryBitState() throws JZLCException {
		return consumerInfoMapper.queryBitState(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public boolean personalEmailUpdate(String tel,String email, String verifyCode) throws JZLCException {
		boolean bindPhone = authentication.bindPhone(tel, verifyCode);
		if(!bindPhone) {
			return false;
		}
		consumerInfoMapper.updataEmail(UserContext.getLogininfo().getConsumerId(),email);
		return true;
	}

	@Override
	public boolean updateTel(String newTel, String newVerify) throws JZLCException {
		boolean bindPhone = authentication.bindPhone(newTel, newVerify);
		if(!bindPhone) {
			return false;
		}
		consumerInfoMapper.updateTel(UserContext.getLogininfo().getConsumerId(),newTel);
		return true;
	}

	@Override
	public boolean realAuth(ConsumerInfo consumerInfo) throws JZLCException {
		int count = authMapper.queryMan(consumerInfo.getName(),consumerInfo.getIdCard());
		if(count<=0) {
			return false;
		}
		System.out.println(consumerInfo.getImageB());
		String consumerId = UserContext.getLogininfo().getConsumerId();
		long bitState = consumerInfoMapper.queryBitState(consumerId);
		consumerInfo.setConsumerId(UserContext.getLogininfo().getConsumerId());
		consumerInfo.setBitState(BitStateUtil.addAuthentication(bitState, BitStateUtil.OPEN_REAL_AUTH));
		consumerInfoMapper.updateRealAuth(consumerInfo);
		return true;
	}

	@Override
	public String judgeBank(String cardId) throws JZLCException {
		return cardRuleMapper.queryBankName(cardId.substring(0, 6));
	}

	@Override
	public void bindBankSubmit(String cardId, String password) throws JZLCException {
		if(password != null) {
			//首次绑定银行卡
			long bitState = consumerInfoMapper.queryBitState(UserContext.getLogininfo().getConsumerId());
			consumerInfoMapper.updateBankBind(BitStateUtil.addAuthentication(bitState, BitStateUtil.OPEN_BANKCARD),password,UserContext.getLogininfo().getConsumerId());
		}
		//添加银行卡
		BankcardInfo bankcardInfo = new BankcardInfo();
		bankcardInfo.setConsumerId(UserContext.getLogininfo().getConsumerId());
		bankcardInfo.setCardId(cardId);
		bankCardInfoMapper.addBankCard(bankcardInfo);
	}

	@Override
	public BigDecimal queryBalance() throws JZLCException {
		return consumerInfoMapper.queryBalance(UserContext.getLogininfo().getConsumerId());
	}

	@Override
	public boolean buyProduct(BigDecimal buyMoney) throws JZLCException {
		String consumerId = UserContext.getLogininfo().getConsumerId();
		BigDecimal balance = consumerInfoMapper.queryBalance(consumerId);
		if(balance.doubleValue()<buyMoney.doubleValue()) {
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean recharge(String consumerId,BigDecimal balance,BigDecimal money) {
		ConsumerInfo consumerInfo = consumerInfoMapper.recharge(consumerId,balance,money);
		return consumerInfo != null;
	}

	@Override
	public Boolean cashOut(String consumerId, BigDecimal balance, BigDecimal money) {
		ConsumerInfo consumerInfo = consumerInfoMapper.cashOut(consumerId, balance, money);
		return consumerInfo!=null;
	}


}
