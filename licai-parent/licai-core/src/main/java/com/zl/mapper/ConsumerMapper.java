package com.zl.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;
import com.zl.pojo.Consumer;

@Mapper
public interface ConsumerMapper {
	/**
	 *个人用户 登录
	 */
	Consumer personalLogin(@Param("consumerName")String consumerName,@Param("password")String password)throws JZLCException;

	/**验证用户名是否已存在
	 * @param consumerName
	 * @return
	 */
	int checkConsumerName(String consumerName)throws JZLCException;

	/**用户注册,添加新用户信息
	 * @param consumer
	 */
	void insertConsumer(Consumer consumer)throws JZLCException;

	/**根据用户名查找用户id
	 * @param consumerName
	 * @return
	 */
	String queryConsumerId(String consumerName)throws JZLCException;

	/**注销账号
	 * @param consumerId
	 * @param cANCELLED
	 * @return
	 */
	int updateConsumerFlag(@Param("consumerId")String consumerId, @Param("accountsFlag")Integer accountsFlag,@Param("updateTime")Date updateTime)throws JZLCException;

	/**修改密码
	 * @param consumerId
	 * @param newPassword
	 */
	void updateLoginPassword(@Param("consumerId")String consumerId, @Param("password")String password,@Param("updateTime")Date updateTime) throws JZLCException;

}
/*
*/