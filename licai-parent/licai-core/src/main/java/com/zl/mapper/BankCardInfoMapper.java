/**
 * 
 */
package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
@Mapper
public interface BankCardInfoMapper {

	/**查询银行卡
	 * @param consumerId
	 * @return
	 */
	List<String> queryCardId(String consumerId) throws JZLCException;

}
