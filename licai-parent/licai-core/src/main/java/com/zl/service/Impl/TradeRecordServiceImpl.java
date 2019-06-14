package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.TradeRecordMapper;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;
import com.zl.service.ITradeRecordService;
import com.zl.util.UserContext;

@Service
public class TradeRecordServiceImpl implements ITradeRecordService {
	@Autowired
	private TradeRecordMapper tradeRecordMapper;

	@Override
	public List<TradeRecord> queryTradeRecord() throws JZLCException {
		String consumerId =  UserContext.getLogininfo().getConsumerId();
		List<TradeRecord> list = tradeRecordMapper.queryTradeRecord(consumerId);
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
