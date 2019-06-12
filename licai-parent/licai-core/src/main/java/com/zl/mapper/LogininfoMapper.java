package com.zl.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.pojo.Controller;

@Mapper
public interface  LogininfoMapper {
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	Controller queryByUsernameAndPassword(@Param("username")String username,
			@Param("password")String password);

	

}
