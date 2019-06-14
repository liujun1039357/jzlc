/**
 * 
 */
package com.zl.service;

/**
 * 处理定时任务的service层
 * @author ivy
 *
 */
public interface ITimingTasksService {

	/**
	 * 计算consumerInfo的总金额
	 */
	void calculatSumMoneyTest();

	/**
	 * 插入每日收益流水
	 */
	void insertProfitlist();

	/**
	 * 计算总金额
	 */
	void calculatSumMoney();

	/**
	 * 
	 */
	void calculatProfit();

	/**
	 * 
	 */
	void automaticTurnOut();
}
