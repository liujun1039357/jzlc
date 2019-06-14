/**
 * 
 */
package com.zl.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.Profit;

/**
 * @author ivy
 *
 */
@Mapper
public interface ProfitListMapper {

	/**
	 * 插入利息流水
	 * @param profit
	 */
	void insertProfitList(Profit profit);

	/**查询用户今日所有利息
	 * @param consumerId
	 * @return
	 */
	BigDecimal querySumProfit(String consumerId);

}
