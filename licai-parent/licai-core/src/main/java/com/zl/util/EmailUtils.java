package com.zl.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 邮箱验证工具类
 * @author 亚索
 *
 */
public class EmailUtils {
	
	private static final String FROM = "liurui89757@163.com";//写自己的邮箱

	public static int sendAccountActivateEmail(String email) {
		
		Session session = getSession();
		
		MimeMessage message = new MimeMessage(session);  
		int email_code = (int)((Math.random()*9+1)*100000);
	//	String checkURL = "http://127.0.0.1/emaicodecheck.action?emailcheckcode="+email_code	;
		getHttpSession().setAttribute("randomemailcode",email_code);
	    String content = new String("您正在进行邮箱的验证，验证码是：" + email_code + "。请不要把验证码泄露给其他人。谢谢你的使用");

/*		 String content = new String("您正在进行邮箱的验证，点击链接以激活邮箱：" + checkURL + "。请不要把链接泄露给其他人。谢谢你的使用");*/
		try {
			message.setSubject("尊敬的客户，这是一封验证邮箱的邮件");
			message.setSentDate(new Date());
			//setFrom 表示用哪个邮箱发送邮件
			message.setFrom(new InternetAddress(FROM));
			/**
			 * RecipientType.TO||BCC||CC
			 *     TO表示主要接收人
			 *     BCC表示秘密抄送人
			 *     CC表示抄送人
			 * InternetAddress  接收者的邮箱地址
			 */
			message.setRecipient(RecipientType.TO, new InternetAddress(email));
			/*message.setContent("<a target='_BLANK' href='"+GenerateLinkUtils.generateActivateLink(user)+"'>"+user.getUsername()+"先生/女士您好，请点击此链接激活账号"+GenerateLinkUtils.generateActivateLink(user)
			+"</a>","text/html;charset=utf-8");*/
			/*message.setContent("<a target='_BLANK' href='http://www.baidu.com'>先生/女士您好，请点击此链接激活账号>>>>>>>>>>>>>>>>>>>>>>>"
			+"</a>","text/html;charset=utf-8");*/
			message.setText(content); 
			Transport.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return email_code;
	}
	
	public static Session getSession() {
		
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");//指定发送的邮箱的邮箱协议
		props.setProperty("mail.smtp.host","smtp.163.com");//指定SMTP服务器
//		props.setProperty("mail.smtp.port", "587");  //smtp是发信邮件服务器,端口是25
		props.setProperty("mail.smtp.port", "25");  //smtp是发信邮件服务器,端口是25
		props.setProperty("mail.smtp.auth","true");//指定是否需要SMTP验证
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(FROM, "123qweasd");
			}
		});
		return session;
	}

	private static HttpSession getHttpSession(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
	}

	
}

