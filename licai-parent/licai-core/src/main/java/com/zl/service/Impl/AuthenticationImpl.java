/**
 * 
 */
package com.zl.service.Impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.pojo.MailInfo;
import com.zl.pojo.VerifyCodeInfo;
import com.zl.service.IAuthentication;
import com.zl.util.DateUtil;
import com.zl.util.SystemConstant;
import com.zl.util.UserContext;
import com.zl.util.VerifyCodeUtil;

/**
 * @author ivy
 *
 */
@Service("authenticationImpl")
public class AuthenticationImpl implements IAuthentication {
	@Value("${spring.mail.username}")
	private String from;
	@Autowired
	private JavaMailSender mailSender;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sendVertifyCode(String tel) throws JZLCException {
		VerifyCodeInfo verifyCodeInfo = UserContext.getVerifyCodeInSession();
		if (verifyCodeInfo == null || DateUtil.secondBetween(verifyCodeInfo.getLastTime(), new Date()) > 20) {
			try {
				VerifyCodeUtil.sendMessage(tel);
			} catch (RuntimeException e) {
				throw e;
			}
		} else {
			throw new JZLCException("操作过于频繁!");
		}
	}

	@Override
	public boolean bindPhone(String tel, String verifyCode) throws JZLCException {
		VerifyCodeInfo verifyCodeInfo = UserContext.getVerifyCodeInSession();
		System.out.println("验证时的手机号" + tel);
		System.out.println("验证时的验证码" + verifyCode);
		System.out.println("验证时是否超时" + (DateUtil.secondBetween(new Date(), verifyCodeInfo.getLastTime())));
		System.out.println("验证码信息" + verifyCodeInfo.getVerifyCode());
		System.out.println("验证码信息" + verifyCodeInfo.getPhoneNumber());
		if (verifyCodeInfo != null && verifyCodeInfo.getPhoneNumber().equals(tel)
				&& verifyCodeInfo.getVerifyCode().equalsIgnoreCase(verifyCode)
				&& DateUtil.secondBetween(verifyCodeInfo.getLastTime(),
						new Date()) < SystemConstant.VERIFYCODE_VALIDATE_TIME) {
			return true;
		}
		return false;
	}

	@Override
	public void sendSimpleMail(String to, String title, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(title);
		message.setText(content);
		mailSender.send(message);
		logger.info("邮件发送成功");
	}

	@Override
	public boolean bindEmail(String email, String emailCode) throws JZLCException {
		MailInfo mailInfo = UserContext.getMailCodeInSession();
		if (mailInfo != null && mailInfo.getEmail().equals(email)
				&& mailInfo.getEmailCode().equalsIgnoreCase(emailCode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOvertime() throws JZLCException {
		MailInfo mailInfo = UserContext.getMailCodeInSession();
		if (DateUtil.secondBetween(mailInfo.getLastTime(),
						new Date()) > SystemConstant.MAILCODE_VALIDATE_TIME) {
			return true;
		}
		return false;
	}
}
