/**
 * 
 */
package com.zl.pojo;

import java.util.Date;

import lombok.Data;

/**
 * @author ivy
 *
 */
@Data
public class MailInfo {
	/**邮箱*/
	private String email;
	/**验证码*/
	private String emailCode;
	/**最后发送验证码的时间*/
	private Date lastTime;
	
}
