package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.TradeListMapper;
import com.zl.pojo.SelectTradeListCondition;
import com.zl.pojo.TradeList;
import com.zl.service.ITradeListService;
import com.zl.util.UserContext;

@Service
public class TradeListServiceImpl implements ITradeListService {
	@Autowired
	private TradeListMapper tradeListMapper;

	@Override
	public List<TradeList> getTradeLists(SelectTradeListCondition condition) throws JZLCException {
		String consumerId = UserContext.getLogininfo().getConsumerId();
		condition.setConsumerId(consumerId);
		return tradeListMapper.getTradeLists(condition);
	}

	@Override
	public List<Integer> getTradeTypes() throws JZLCException {
		return tradeListMapper.getTradeTypes();
	}
	
	
	


}
