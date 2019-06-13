package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.SelectTradeListCondition;
import com.zl.pojo.TradeList;

@Mapper
public interface TradeListMapper {

	/**
	 * 获取交易记录
	 * */
	List<TradeList> getTradeLists(SelectTradeListCondition condition);

	/**
	 * 获取交易类型
	 * */
	List<Integer> getTradeTypes();
	
}
/*
*/