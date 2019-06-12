package com.zl.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 公司帐号信息
 */

@Data
public class Business implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 企业注册流水号 */
	private String businessId;
	/** 企业注册邮箱号 */
	private String email;
	/** 登录密码 */
	private String loginPwd;
	/** 支付密码 */
	private Integer payPwd;

	//创建时间
	private Date createTime;
	// 修改时间
	private Date updateTime;

	// 软删除标识符 0正常 1已删除
	private Integer isDel;

}
