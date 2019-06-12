package com.zl.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.exception.JZLCException;
import com.zl.service.ILogininfoService;
import com.zl.util.AjaxJson;


@Controller
public class LogininfoController {
	
	
	@Autowired
	private ILogininfoService logininfoService;

	 @RequestMapping("login") 
	  public String Backstage() { 
		  return "login"; 
		  }
	  
	
	@RequestMapping(path="login2")
	@ResponseBody
	public AjaxJson login(String username,String password,HttpServletRequest request){
		AjaxJson json = new AjaxJson();
		System.out.println("进来了");
		try {
			boolean isOk = logininfoService.login(username, password);
		//	System.out.println("boolean:"+isOk);
			if(isOk == false){
				json.setSuccess(false);
				json.setMsg("用户名或者密码错误");
			}
		} catch (JZLCException e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		//System.out.println("json:"+json.toString());
		return json;
		
	}
	
	
	 
	/*登入查询数据进入个人页面*/
	@GetMapping("mgindex")
	public String personal(Model model){
//		model.addAttribute("account",accountService.getById(UserContext.getCurrent().getId()));
//		model.addAttribute("userinfo",userinfoService.getById(UserContext.getCurrent().getId()));
//		model.addAttribute("lastLoginTime",ipLogService.getLastLoginTime(UserContext.getCurrent().getUsername(),UserContext.getCurrent().getUsertype()));
		return "index/index";
	}
	
	
	@RequestMapping(path="wecome")
	public String wecome() {
		return "index/welcome";
	}
	
	@ResponseBody
	@RequestMapping("hello")
	public String hello() {
		System.out.println(">>>>>>>>>>>>>>>>>>");
		throw new JZLCException("出错了");
	}
//	@RequestMapping(path="/register.do",method=RequestMethod.POST)
//	@ResponseBody
//	public JsonResult register(String username,String password){
//		JsonResult  json = new JsonResult();
//		try{
//		logininfoService.register(username,password);
//		}catch(Exception e){
//			json.setSuccess(false);
//		}
//		return json;
//		
//		
//	}
//	
	
	
	
	
	
}
