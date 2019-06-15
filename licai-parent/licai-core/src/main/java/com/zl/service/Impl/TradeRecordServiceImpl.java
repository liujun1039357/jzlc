package com.zl.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ProductMapper;
import com.zl.mapper.TradeRecordMapper;
import com.zl.pojo.Product;
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
		Integer productProfitType = productMapper.queryProductProfitType(productId);
		System.out.println(productProfitType);
		String consumerId = UserContext.getLogininfo().getConsumerId();
		if(productProfitType.equals(Product.DAY)) {
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
	public BigDecimal queryBaseMoney() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal baseMoney = tradeRecordMapper.queryBaseMoney(consumerId);
		return baseMoney;
	}

	@Override
	public BigDecimal queryInterest() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal interest = tradeRecordMapper.queryInterest(consumerId);
		return interest;
	}

	@Override
	public BigDecimal queryInstableBaseMoney() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal instableBaseMoney = tradeRecordMapper.queryInstableBaseMoney(consumerId);
		return instableBaseMoney;
	}

	@Override
	public BigDecimal queryInstableInterest() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		BigDecimal instableInterest = tradeRecordMapper.queryInstableInterest(consumerId);
		return instableInterest;
	}

	@Override
	public List<Profit> queryProfitList() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		List<Profit> list = tradeRecordMapper.queryProfitList(consumerId);
		return list;
	}


}
