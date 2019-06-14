/**
 * 
 */
package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;
import com.zl.pojo.BankcardInfo;

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
	List<String> queryCardId(@Param("consumerId")String consumerId,@Param("cardStates")int cardStates) throws JZLCException;

	/**添加银行卡
	 * @param bankcardInfo
	 */
	void addBankCard(BankcardInfo bankcardInfo) throws JZLCException;
	
	/**
	 * 验证是否绑定银行卡
	 * @param consumerId
	 * @param cardId
	 * */
	BankcardInfo isBoundCard(String consumerId,String cardId)  throws JZLCException;
	
}
