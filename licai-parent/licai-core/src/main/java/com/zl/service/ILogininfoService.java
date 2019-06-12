package com.zl.service;

import com.zl.exception.JZLCException;

public interface  ILogininfoService {

	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username,String password) throws  JZLCException;

	

	
	


	
}
