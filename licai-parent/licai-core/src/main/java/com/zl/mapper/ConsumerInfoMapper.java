/**
 * 
 */
package com.zl.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.RealAuthShow;
import com.zl.pojo.TradeRecord;

/**
 * @author ivy
 *
 */
@Mapper
public interface ConsumerInfoMapper {

	/**检验手机号是否存在
	 * @param tel 手机号
	 * @return
	 */
	int checkTel(String tel)throws JZLCException;

	/**添加用户信息
	 * @param consumerInfo
	 */
	void insertConsumerInfo(ConsumerInfo consumerInfo)throws JZLCException;

	/**邮箱绑定
	 * @param consumerId 用户id
	 * @param email 邮箱
	 * @param bitState 认证状态码
	 */
	void updataEmailInfo(@Param("consumerId")String consumerId, @Param("email")String email, @Param("bitState")long bitState)throws JZLCException;

	/**查询邮箱号
	 * @param consumerId
	 * @return
	 */
	String queryEmail(String consumerId) throws JZLCException;

	/**查询银行卡
	 * @param consumerId
	 * @return
	 */
	String queryIdCard(String consumerId) throws JZLCException;

	/**查询用户手机号
	 * @param consumerId
	 * @return
	 */
	String queryTel(String consumerId) throws JZLCException;

	/**根据用户id查询实名认证信息
	 * @param consumerId
	 * @return
	 */
	RealAuthShow queryRealAuthInfo(String consumerId) throws JZLCException;

	/**查询真实姓名
	 * @param consumerId
	 * @return
	 */
	String queryRealName(String consumerId) throws JZLCException;

	/**查询认证状态
	 * @param consumerId
	 * @return
	 */
	long queryBitState(String consumerId) throws JZLCException;

	/**修改邮箱
	 * @param consumerId
	 * @param email
	 */
	void updataEmail(@Param("consumerId")String consumerId, @Param("email")String email) throws JZLCException;

	/**修改手机号
	 * @param consumerId
	 * @param newTel
	 */
	void updateTel(@Param("consumerId")String consumerId, @Param("tel")String tel) throws JZLCException;

	/**实名认证信息修改
	 * @param consumerInfo
	 */
	void updateRealAuth(ConsumerInfo consumerInfo) throws JZLCException;

	/**绑定银行卡
	 * @param cardId
	 * @param password
	 */
	void updateBankBind(@Param("bitState")long bitState, @Param("password")String password,@Param("consumerId")String consumerId) throws JZLCException;

	/**查询金额
	 * @param consumerId
	 * @return
	 */
	BigDecimal queryBalance(String consumerId) throws JZLCException;

	/**
	 *充值
	 *@param consumerId
	 *@param balance
	 *@param money
	 */
	int recharge(@Param("balance")BigDecimal balance,@Param("money")BigDecimal money,@Param("consumerId")String consumerId) throws JZLCException;
	
	/**
	 *提现
	 *@param consumerId
	 *@param balance
	 *@param money
	 */
	int cashOut(@Param("balance")BigDecimal balance,@Param("money")BigDecimal money,@Param("consumerId")String consumerId) throws JZLCException;
}
