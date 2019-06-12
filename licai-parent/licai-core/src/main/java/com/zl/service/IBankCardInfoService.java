/**
 * 
 */
package com.zl.service;

import java.util.List;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
public interface IBankCardInfoService {

	/**查询当前用户银行卡
	 * @return
	 */
	List<String> queryCardId() throws JZLCException;

}
