package com.zl.pojo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VerifyCodeInfo {
	/**手机号*/
	private String phoneNumber;
	/**验证码*/
	private String verifyCode;
	/**最后发送时间*/
	private Date lastTime;
	private String reason;
	private int error_code;
}
