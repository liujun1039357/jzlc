package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.exception.JZLCException;
import com.zl.pojo.TradeList;

@Mapper
public interface TradeListMapper {

	/**
	 * 获取交易记录
	 * */
	List<TradeList> getTradeLists()throws JZLCException;

	/**插入交易记录
	 * @param tradeList
	 */
	void insertTradeList(TradeList tradeList)throws JZLCException;
	
}
/*
*/