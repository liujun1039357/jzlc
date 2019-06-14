/**
 * 
 */
package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.exception.JZLCException;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;

@Mapper
public interface TradeRecordMapper {

	/**
	 * 查詢个人购买的产品
	 * */
	List<TradeRecord> queryTradeRecord(String consumerId) throws JZLCException;

	/**
	 * 查詢个人购买的产品累计投资
	 * */
	int queryBaseMoney(String consumerId) throws JZLCException;

	/**
	 * 查詢个人购买的累计收益
	 * */
	int queryInterest(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人购买的待收本金
	 * */
	int queryInstableBaseMoney(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人购买的待收收益
	 * */
	int queryInstableInterest(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人的回款记录
	 * */
	List<Profit> queryProfitList(String consumerId) throws JZLCException;
}
