package com.zl.service;

import java.util.List;

import com.zl.exception.JZLCException;
import com.zl.pojo.SelectTradeListCondition;
import com.zl.pojo.TradeList;

public interface ITradeListService {

	List<TradeList> getTradeLists(SelectTradeListCondition condition) throws JZLCException;
	
	List<Integer> getTradeTypes() throws JZLCException;

}
