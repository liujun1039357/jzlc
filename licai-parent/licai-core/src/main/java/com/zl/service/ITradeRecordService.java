/**
 * 
 */
package com.zl.service;

import java.math.BigDecimal;
import java.util.List;

import com.zl.exception.JZLCException;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;

/**
 * @author ivy
 *
 */
public interface ITradeRecordService {

	/**查询可转出总金额
	 * @return
	 */
	BigDecimal querySumMoney(String productId) throws JZLCException;

	/**查询个人购买产品
	 * @return
	 */
	List<TradeRecord> queryTradeRecord() throws JZLCException;
	
	
	

	/**
	 * 查詢个人购买的产品累计投资
	 * */
	int queryBaseMoney() throws JZLCException;

	/**
	 * 查詢个人购买的累计收益
	 * */
	int queryInterest() throws JZLCException;
	
	/**
	 * 查詢个人购买的待收本金
	 * */
	int queryInstableBaseMoney() throws JZLCException;
	
	/**
	 * 查詢个人购买的待收收益
	 * */
	int queryInstableInterest() throws JZLCException;

	/**
	 * 查詢个人购买的待收收益
	 * */
	List<Profit> queryProfitList() throws JZLCException;

}
