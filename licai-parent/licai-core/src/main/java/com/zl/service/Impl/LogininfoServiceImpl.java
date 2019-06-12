package com.zl.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.LogininfoMapper;
import com.zl.pojo.Controller;
import com.zl.service.ILogininfoService;




@Service
public class LogininfoServiceImpl implements ILogininfoService {

	@Autowired
	private LogininfoMapper logininfoMapper;

	/**
	 * 管理员登录
	 */
	@Override
	public boolean login(String username, String password)  throws  JZLCException {
		Controller  current = logininfoMapper.queryByUsernameAndPassword(username, password);
		if(current != null){	
			if(current.getStates() ==Controller.DELETE)
			throw  new JZLCException("账户已删除，联系领导");
		}
		
		return current != null;
	}


	

	

}
