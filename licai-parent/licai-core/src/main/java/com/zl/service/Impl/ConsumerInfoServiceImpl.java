/**
 * 
 */
package com.zl.service.Impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.AuthMapper;
import com.zl.mapper.BankCardInfoMapper;
import com.zl.mapper.CardRuleMapper;
import com.zl.mapper.ConsumerInfoMapper;
import com.zl.mapper.ProductMapper;
import com.zl.mapper.TradeListMapper;
import com.zl.mapper.TradeRecordMapper;
import com.zl.pojo.BankcardInfo;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.MailInfo;
import com.zl.pojo.ProductShowInfo;
import com.zl.pojo.RealAuthShow;
import com.zl.pojo.TradeList;
import com.zl.pojo.TradeRecord;
import com.zl.service.IAuthentication;
import com.zl.service.IConsumerInfoService;
import com.zl.util.BitStateUtil;
import com.zl.util.DateUtil;
import com.zl.util.SystemConstant;
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
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private TradeRecordMapper tradeRecordMapper;
	@Autowired
	private TradeListMapper tradeListMapper;
	
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
	public boolean buyProduct(String productId,BigDecimal buyMoney) throws JZLCException {
		String consumerId = UserContext.getLogininfo().getConsumerId();
		BigDecimal balance = consumerInfoMapper.queryBalance(consumerId);
		//判断余额
		System.out.println(balance.doubleValue()<buyMoney.doubleValue());
		if(balance.doubleValue()<buyMoney.doubleValue()) {
			return false;
		}
		//判断购买金额是否小于剩余可买额度
		BigDecimal canSaledQuota = productMapper.queryCanSaledQuota(productId);
		if(canSaledQuota.doubleValue()<buyMoney.doubleValue()) {
			throw new JZLCException("购买金额不能大于剩余可买额度!");
		}
		//更新总金额余额及已用金额
		consumerInfoMapper.updateMoneyOfBuyProduct(consumerId,buyMoney);
		//更新产品已售额度
		
		productMapper.updateProductSaledQuota(productId,buyMoney);
		ProductShowInfo product = productMapper.queryProductInfo(productId);
		//插入tradeRecord表
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setProductId(productId);
		tradeRecord.setProductName(product.getProductName());
		tradeRecord.setBaseMoney(buyMoney);
		tradeRecord.setRate(product.getProductProfit());
		tradeRecord.setConsumerId(consumerId);
		tradeRecord.setEffectDate(DateUtil.addDays(new Date(), product.getAcountCycle()));
		tradeRecord.setProductProfitType(product.getProductProfitType());
		tradeRecordMapper.insertTradeRecord(tradeRecord);
		//插入tradeList表
		TradeList tradeList = new TradeList();
		tradeList.setConsumerId(consumerId);
		tradeList.setProductId(productId);
		tradeList.setTradeType(TradeList.PURCHASE);
		tradeList.setTradeMoney(buyMoney);
		tradeListMapper.insertTradeList(tradeList);
		return true;
	}

	@Override
	public boolean turnOutProduct(String productId, BigDecimal sumMoney) throws JZLCException {
		String consumerId = UserContext.getLogininfo().getConsumerId();
		//更新总金额余额及已用金额
		consumerInfoMapper.updateMoneyOfTurnOut(consumerId,sumMoney);
		System.out.println(productId);
		//修改tradeRecord表的状态
		tradeRecordMapper.updateTradeRecord(productId,consumerId,TradeRecord.TURNOUT);
		//插入tradeList表
		TradeList tradeList = new TradeList();
		tradeList.setConsumerId(consumerId);
		tradeList.setProductId(productId);
		tradeList.setTradeType(TradeList.TURNOUT);
		tradeList.setTradeMoney(sumMoney);
		tradeListMapper.insertTradeList(tradeList);
		return true;
	}

	@Override
	public Boolean recharge(BigDecimal money) throws JZLCException {

		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal balance = consumerInfoMapper.queryBalance(consumerId);
		int count = consumerInfoMapper.recharge(balance,money,consumerId);
		if(count <= 0) {
			return false;
		}
		//插入tradeRecord表
		TradeRecord tradeRecord = new TradeRecord();
		tradeRecord.setProductId(SystemConstant.JUZILICAI_ID);
		tradeRecord.setProductName(SystemConstant.JUZILICAI_NAME);
		tradeRecord.setBaseMoney(money);
		tradeRecord.setRate(SystemConstant.JUZILICAI_RATE);
		tradeRecord.setConsumerId(consumerId);
		tradeRecord.setEffectDate(new Date());
		tradeRecord.setProductProfitType(SystemConstant.JUZILICAI_PROFIT_TYPE);
		tradeRecordMapper.insertTradeRecord(tradeRecord);

		//插入tradeList表
		TradeList tradeList = new TradeList();
		tradeList.setConsumerId(consumerId);
		tradeList.setProductId(SystemConstant.JUZILICAI_ID);
		tradeList.setTradeType(TradeList.PURCHASE);
		tradeList.setTradeMoney(money);
		tradeListMapper.insertTradeList(tradeList);
		return true;
	}

	@Override
	public Boolean cashOut(BigDecimal money) throws JZLCException {

		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal balance = consumerInfoMapper.queryBalance(consumerId);
		if(money.compareTo(balance)==1) {
			return false;
		}
		int count = consumerInfoMapper.cashOut(balance, money,consumerId);
		if(count <= 0) {
			return false;
		}
		return true;
	}


}
