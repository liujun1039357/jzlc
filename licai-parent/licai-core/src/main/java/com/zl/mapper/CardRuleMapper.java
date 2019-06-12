/**
 * 
 */
package com.zl.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
@Mapper
public interface CardRuleMapper {

	/**
	 * @param substring
	 * @return
	 */
	String queryBankName(String preSixNum) throws JZLCException;

}
