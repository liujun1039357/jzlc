/**
 * 
 */
package com.zl.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;

/**
 * @author ivy
 *
 */
@Mapper
public interface TradeRecordMapper {

	/**查询日结方式可转出总金额
	 * @param consumerId
	 * @return
	 */
	BigDecimal querySumMoneyByDay(@Param("consumerId")String consumerId,@Param("productId")String productId) throws JZLCException;
	
	/**查询期结方式可转出总金额
	 * @param consumerId
	 * @return
	 */
	BigDecimal querySumMoneyByTerm(@Param("consumerId")String consumerId,@Param("productId")String productId) throws JZLCException;

	/**插入购买产品记录
	 * @param tradeRecord
	 */
	void insertTradeRecord(TradeRecord tradeRecord) throws JZLCException;

	/**软删除转出的产品
	 * @param productId
	 * @param consumerId
	 * @param tURNOUT
	 */
	void updateTradeRecord(@Param("productId")String productId, @Param("consumerId")String consumerId, @Param("states")Integer states) throws JZLCException;

	/**
	 * 查询所有用户购买的所有活期产品
	 * @param dAY
	 * @return
	 */
	List<TradeRecord> queryTradeRecords(@Param("states")Integer states);

	/**查询所有本金
	 * @param consumerId
	 * @param bUY
	 * @return
	 */
	BigDecimal querySumPrincipal(@Param("consumerId")String consumerId, @Param("states")Integer states);

	/**更新利息
	 * @param tradeRecordId
	 */
	void updateInterest(String tradeRecordId);

	/**查询所有购买中的活期产品订单
	 * @param productProfitType
	 * @param states
	 * @return
	 */
	List<String> queryTradeRecordId(@Param("productProfitType")Integer productProfitType, @Param("states")Integer states);

	/**查询所有购买中的死期产品订单
	 * @param productProfitType
	 * @param states
	 * @return
	 */
	List<String> queryTermTradeRecord(@Param("productProfitType")Integer productProfitType, @Param("states")Integer states);

	/**根据产品记录id查产品记录
	 * @param tradeRecordId
	 * @return
	 */
	TradeRecord queryTradeRecord(String tradeRecordId);

	/**
	 * 查詢个人购买的产品
	 * */
	List<TradeRecord> queryPersonTradeRecord(String consumerId) throws JZLCException;

	/**
	 * 查詢个人购买的产品累计投资
	 * */
	BigDecimal queryBaseMoney(String consumerId) throws JZLCException;

	/**
	 * 查詢个人购买的累计收益
	 * */
	BigDecimal queryInterest(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人购买的待收本金
	 * */
	BigDecimal queryInstableBaseMoney(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人购买的待收收益
	 * */
	BigDecimal queryInstableInterest(String consumerId) throws JZLCException;
	
	/**
	 * 查詢个人的回款记录
	 * */
	List<Profit> queryProfitList(String consumerId) throws JZLCException;
}
