package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.ConsumerRecord;
import com.zl.pojo.Controller;
import com.zl.pojo.Notice;

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
	
	/* 管理员登入记录 */
	
	void saveConsumerRecord(ConsumerRecord consumerRecord);
	
	/* 查询管理员登入记录 */
	ConsumerRecord queryById(String id);

	/* 保存公告 */
	void savenotice(Notice notice);
	
	//查询用户数
	int queryCountConsumerInfo();
	
	//查询所有用户详情信息
	List<ConsumerInfo> queryByAllConsumerInfo(ConsumerInfoCondition cc);
	
	//查询企业数
	int queryCountBusinessInfo();
	
	//查询产品数
	int queryCountProduct();

	//根据id查询用户
	ConsumerInfo selectYonghu(@Param("id")String id);
	
	

}

