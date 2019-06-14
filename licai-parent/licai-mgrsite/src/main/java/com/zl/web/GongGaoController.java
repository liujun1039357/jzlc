package com.zl.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zl.pojo.Notice;
import com.zl.service.ILogininfoService;
import com.zl.util.AjaxJson;

@Controller
public class GongGaoController {
	@Autowired
	private ILogininfoService logininfoService;

	@GetMapping("gonggao")
	@ResponseBody
	public AjaxJson personal(HttpSession session, Notice notice) {
		AjaxJson json = new AjaxJson();
		logininfoService.gg(notice);

//		model.addAttribute("userinfo",userinfoService.getById(UserContext.getCurrent().getId()));
//		model.addAttribute("lastLoginTime",ipLogService.getLastLoginTime(UserContext.getCurrent().getUsername(),UserContext.getCurrent().getUsertype()));
//		return "index/GongGao";

		return json;
	}
}
