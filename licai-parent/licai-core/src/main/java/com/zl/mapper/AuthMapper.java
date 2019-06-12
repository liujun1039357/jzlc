/**
 * 
 */
package com.zl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;

/**
 * @author ivy
 *
 */
@Mapper
public interface AuthMapper {

	/**
	 * @param name
	 * @param idCard
	 * @return
	 */
	int queryMan(@Param("realName")String realName, @Param("idCard")String idCard) throws JZLCException;

}
