package com.zl.service;

import java.util.List;

import com.zl.exception.LoginInfoException;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.ConsumerRecord;
import com.zl.pojo.Notice;

public interface ILogininfoService {

	/**
	 * 管理员登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password, String ip) throws LoginInfoException;

	/**
	 * 管理员登录记录查询
	 * 
	 * @param id
	 * @return
	 */
	ConsumerRecord queryById(String id);

	/* 保存公告 */
	void gg(Notice notice);

	// 查询用户数
	int queryCountConsumerInfo();
	
	//查询所有用户详情信息
	  public List<ConsumerInfo> queryAll(ConsumerInfoCondition cc);

	// 查询企业数
	int queryCountBusinessInfo();

	// 查询产品数
	int queryCountProduct();
	
	
	//根据id查询用户
	public ConsumerInfo selectYonghu(String id);

}
