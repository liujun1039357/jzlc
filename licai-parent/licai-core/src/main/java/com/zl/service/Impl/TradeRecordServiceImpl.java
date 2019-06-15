package com.zl.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ProductMapper;
import com.zl.mapper.TradeRecordMapper;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;
import com.zl.service.ITradeRecordService;
import com.zl.util.UserContext;

/**
 * @author ivy
 *
 */
@Service
public class TradeRecordServiceImpl implements ITradeRecordService {
	@Autowired
	private TradeRecordMapper tradeRecordMapper;
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public BigDecimal querySumMoney(String productId) throws JZLCException {
		int productProfitType = productMapper.queryProductProfitType(productId);
		System.out.println(productProfitType);
		String consumerId = UserContext.getLogininfo().getConsumerId();
		if(productProfitType==0) {
			return tradeRecordMapper.querySumMoneyByDay(consumerId,productId);
		}else {
			return tradeRecordMapper.querySumMoneyByTerm(consumerId,productId);
		}
	}

	@Override
	public List<TradeRecord> queryTradeRecord() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		List<TradeRecord> list = tradeRecordMapper.queryPersonTradeRecord(consumerId);
		return list;
	}

	@Override
	public int queryBaseMoney() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		int baseMoney = tradeRecordMapper.queryBaseMoney(consumerId);
		return baseMoney;
	}

	@Override
	public int queryInterest() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		int interest = tradeRecordMapper.queryInterest(consumerId);
		return interest;
	}

	@Override
	public int queryInstableBaseMoney() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		int instableBaseMoney = tradeRecordMapper.queryInstableBaseMoney(consumerId);
		return instableBaseMoney;
	}

	@Override
	public int queryInstableInterest() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		int instableInterest = tradeRecordMapper.queryInstableInterest(consumerId);
		return instableInterest;
	}

	@Override
	public List<Profit> queryProfitList() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		List<Profit> list = tradeRecordMapper.queryProfitList(consumerId);
		return list;
	}


}
