package com.zl.pojo;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.zl.util.BitStateUtil;
import com.zl.util.SystemConstant;

/**
 * 客户详细信息
 * */

@Data
public class ConsumerInfo implements Serializable{
	/**投资风格： 0未测试  12345*/
	public static Integer UNTESTED = 0;

	private static final long serialVersionUID = 1L;

	/**用户id*/
	private String consumerId;
	/**电话*/
	private String tel;
	/**邮箱*/
	private String email;
	/**身份证号码*/
	private String idCard;
	/**身份证正面照片*/
	private String imageA;
	/**身份证反面照片*/
	private String imageB;
	/**认证状态:身份证验证 邮箱验证 手机验证 银行卡绑定 密保问题是否设置*/
	private long bitState = UNTESTED;
	/**投资风格： 0未测试  12345*/
	private Integer style = UNTESTED;
	/**MD5加密的六位支付密码*/
	private Integer password;
	/**账户总资产*/
	private BigDecimal sumMoney = SystemConstant.ZARO;
	/**余额*/
	private BigDecimal balance = SystemConstant.ZARO;
	/**已用金额*/
	private BigDecimal usedMoney = SystemConstant.ZARO;
	/**用户真实姓名*/
	private String name;
	/**用户现住址*/
	private String address;
	/**用户性别 0表示男 1表示女*/
	private Integer sex;
	/**创建时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date createTime;
	/**最后修改时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date updateTime;
	/**
	 * 是否已经实名制认证
	 * 
	 * @return
	 */
	public boolean isRealAuth() {
		return BitStateUtil.hasState(bitState, BitStateUtil.OPEN_REAL_AUTH);
	}

	/**
	 * 是否已经手机号码认证
	 * 
	 * @return
	 */
	public boolean isMsgAuth() {
		return BitStateUtil.hasState(bitState, BitStateUtil.OPEN_MSG_AUTH);
	}

	/**
	 * 是否已经邮箱认证
	 * 
	 * @return
	 */
	public boolean isMailAuth() {
		return BitStateUtil.hasState(bitState, BitStateUtil.OPEN_EMAIL_AUTH);
	}

	/**
	 * 是否已綁定銀行卡
	 * 
	 * @return
	 */
	public boolean isVIP() {
		return BitStateUtil.hasState(bitState, BitStateUtil.OPEN_BANKCARD);
	}

	/**
	 * 判断是否存在某个状态
	 * 
	 * @param stateCode
	 * @return
	 */
	public boolean hasState(long stateCode) {
		return BitStateUtil.hasState(bitState, stateCode);
	}

	/**
	 * 添加某个状态
	 * 
	 * @param stateCode
	 * @return
	 */
	public long addState(long stateCode) {
		return BitStateUtil.addAuthentication(bitState, stateCode);
	}

	/**
	 * 移除某个状态
	 * 
	 * @param stateCode
	 * @return
	 */
	public long removeState(long stateCode) {
		return BitStateUtil.removeState(bitState, stateCode);
	}

}
