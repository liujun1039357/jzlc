/**
 * 
 */
package com.zl.service;

import java.math.BigDecimal;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
public interface ITradeRecordService {

	/**查询可转出总金额
	 * @return
	 */
	BigDecimal querySumMoney(String productId) throws JZLCException;

}
