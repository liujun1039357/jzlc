/**
 * 
 */
package com.zl.web;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zl.exception.JZLCException;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.MailInfo;
import com.zl.service.IAuthentication;
import com.zl.service.IConsumerInfoService;
import com.zl.util.AjaxJson;
import com.zl.util.CheckLogin;
import com.zl.util.UploadUtil;
import com.zl.util.UserContext;

/**
 * @author ivy
 *
 */
@Controller
public class ConsumerInfoController {
	@Autowired
	private IConsumerInfoService consumerInfoService;
	@Autowired
	private IAuthentication authentication;

	/**发送邮箱验证码*/
	@RequestMapping("personalEmailSend")
	@CheckLogin
	@ResponseBody
	public AjaxJson personalEmailSend(String email) {
		AjaxJson json = new AjaxJson();
		String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
		String message = "您的注册验证码为：" + checkCode;
		try {
			authentication.sendSimpleMail(email, "注册验证码", message);
			MailInfo mailInfo = new MailInfo();
			mailInfo.setEmail(email);
			mailInfo.setEmailCode(checkCode);
			mailInfo.setLastTime(new Date());
			UserContext.setMailCodeInSession(mailInfo);
		} catch (JZLCException e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}

	/**邮箱绑定*/
	@RequestMapping("personalEmailBind")
	@CheckLogin
	@ResponseBody
	public AjaxJson personalEmailBind(String email, String emailCode) {
		AjaxJson json = new AjaxJson();
		try {
			consumerInfoService.personalEmailBind(email, emailCode);
		} catch (JZLCException e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	/**更改邮箱发送短信验证码*/
	@RequestMapping("updateVerifySend")
	@CheckLogin
	@ResponseBody
	public AjaxJson updateVerifySend() {
		AjaxJson json = new AjaxJson();
		String tel = consumerInfoService.queryTel();
		try{
			authentication.sendVertifyCode(tel);
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	/**更改邮箱*/
	@RequestMapping("personalEmailUpdate")
	@CheckLogin
	@ResponseBody
	public AjaxJson personalEmailUpdate(String email,String verifyCode) {
		AjaxJson json = new AjaxJson();
		System.out.println(email);
		String tel = consumerInfoService.queryTel();
		boolean check = true;
		try {
			check = consumerInfoService.personalEmailUpdate(tel,email, verifyCode);
			if(!check) {
				json.setSuccess(false);
				json.setMsg("验证码错误!");
			}
		} catch (JZLCException e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
		
	/**更改手机号发送旧手机短信验证码*/
	@RequestMapping("sendOldVerify")
	@CheckLogin
	@ResponseBody
	public AjaxJson sendOldVerify() {
		AjaxJson json = new AjaxJson();
		String tel = consumerInfoService.queryTel();
		try{
			authentication.sendVertifyCode(tel);
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	/**检验旧手机的短信验证码*/
	@RequestMapping("checkOldTel")
	@CheckLogin
	@ResponseBody
	public AjaxJson checkOldTel(String tel,String verifyCode) {
		AjaxJson json = new AjaxJson();
		boolean check = true;
		try{
			check = authentication.bindPhone(tel, verifyCode);
			if(!check) {
				json.setSuccess(false);
				json.setMsg("验证码错误!");
			}
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		json.setMsg("验证成功,请输入新手机号");
		return json;
	}
	
	/**更改手机号发送新手机短信验证码*/
	@RequestMapping("sendNewVerify")
	@CheckLogin
	@ResponseBody
	public AjaxJson sendNewVerify(String newTel) {
		AjaxJson json = new AjaxJson();
		try{
			authentication.sendVertifyCode(newTel);
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	/**提交更改手机号表单*/
	@RequestMapping("updateTel")
	@CheckLogin
	@ResponseBody
	public AjaxJson updateTel(String newTel,String newVerify) {
		AjaxJson json = new AjaxJson();
		boolean check = true;
		try{
			check = consumerInfoService.updateTel(newTel, newVerify);
			if(!check) {
				json.setSuccess(false);
				json.setMsg("验证码错误!");
			}
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
	
	/**异步上传文件*/
	@RequestMapping("realAuthUpload")
	@ResponseBody
	@CheckLogin
	public String realAuthUpload(MultipartFile file) {
		String fileName = UploadUtil.upload(file);
		return "/upload/" + fileName;
	}
	
	/**实名认证*/
	@RequestMapping("realAuthSubmit")
	@CheckLogin
	@ResponseBody
	public AjaxJson realAuthSubmit(ConsumerInfo consumerInfo) {
		AjaxJson json = new AjaxJson();
		boolean check = true;
		try{
			check = consumerInfoService.realAuth(consumerInfo);
			if(!check) {
				json.setSuccess(false);
				json.setMsg("身份信息错误,认证失败!");
			}
		}catch(JZLCException e){
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
	}
}
