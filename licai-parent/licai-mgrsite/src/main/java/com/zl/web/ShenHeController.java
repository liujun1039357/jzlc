package com.zl.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.Product;
import com.zl.service.IShenHeService;
import com.zl.util.CheckLogin;
import com.zl.util.UserContext;

@Controller
public class ShenHeController {
	@Autowired
	private IShenHeService IshenheService;

	// 企业审核
	@RequestMapping("qiye")
	@CheckLogin
	public String menu(@RequestParam(required = true, defaultValue = "1") Integer pageindex, ConsumerInfoCondition cc,
			HttpSession session) {
		PageHelper.startPage(pageindex, 8);

		if (UserContext.getCurrent().getJurisdiction() == 1) {
			List<BusinessInfo> xxx = IshenheService.SelectOneQiye(cc);
			PageInfo<BusinessInfo> pageInfo = new PageInfo<BusinessInfo>(xxx, 3);
			session.setAttribute("pageInfo2", pageInfo);

		} else if (UserContext.getCurrent().getJurisdiction() == 2) {
			List<BusinessInfo> xxx = IshenheService.SelectTwoQiye(cc);
			PageInfo<BusinessInfo> pageInfo = new PageInfo<BusinessInfo>(xxx, 3);
			session.setAttribute("pageInfo2", pageInfo);
		} else if (UserContext.getCurrent().getJurisdiction() == 3) {
			List<BusinessInfo> xxx = IshenheService.SelectThreeQiye(cc);
			PageInfo<BusinessInfo> pageInfo = new PageInfo<BusinessInfo>(xxx, 3);
			session.setAttribute("pageInfo2", pageInfo);
		}else {
			List<BusinessInfo> xxx = IshenheService.SelectFulfillQiye(cc);
			PageInfo<BusinessInfo> pageInfo = new PageInfo<BusinessInfo>(xxx, 3);
			session.setAttribute("pageInfo2", pageInfo);
		}

		session.setAttribute("condition2", cc);

		return "index/qiye";
	}

	// 产品审核
	@RequestMapping("menu-add3")
	@CheckLogin
	public String menu5(@RequestParam(required = true, defaultValue = "1") Integer pageindex, ConsumerInfoCondition cc,
			HttpSession session) {
		PageHelper.startPage(pageindex, 8);

		if (UserContext.getCurrent().getJurisdiction() == 1) {
			List<Product> xxx = IshenheService.SelectOneChangping(cc);
			PageInfo<Product> pageInfo = new PageInfo<Product>(xxx, 3);
			session.setAttribute("pageInfo3", pageInfo);

		} else if (UserContext.getCurrent().getJurisdiction() == 2) {
			List<Product> xxx = IshenheService.SelectTwoChangping(cc);
			PageInfo<Product> pageInfo = new PageInfo<Product>(xxx, 3);
			session.setAttribute("pageInfo3", pageInfo);
		}else if (UserContext.getCurrent().getJurisdiction() == 3) {
			List<Product> xxx = IshenheService.SelectThreeChangping(cc);
			PageInfo<Product> pageInfo = new PageInfo<Product>(xxx, 3);
			session.setAttribute("pageInfo3", pageInfo);
		}  else {
			List<Product> xxx = IshenheService.SelectFulfillChangping(cc);
			PageInfo<Product> pageInfo = new PageInfo<Product>(xxx, 3);
			session.setAttribute("pageInfo3", pageInfo);
		}

		session.setAttribute("condition3", cc);
		return "index/ChangPing2";
	}

	@RequestMapping("qiyeinfo")
	@CheckLogin
	public String passdwda(String id, HttpSession session) {
		BusinessInfo si = IshenheService.selectQiye(id);
		session.setAttribute("pageInfo4", si);
		return "index/qiyeinfo";
	}

	
	//通过
	@RequestMapping("pass")
	@CheckLogin
	public String pass(String passid, Integer state, HttpSession session) {
		IshenheService.Pass(passid, state);
		return "forward:qiye";
	}

	//拒绝
	@RequestMapping("Refuse")
	@CheckLogin
	public String Refuse(String Refuseid, Integer state, HttpSession session) {
		IshenheService.Refuse(Refuseid, state);
		return "forward:qiye";
	}
	
	
	
	@RequestMapping("changpinginfo")
	@CheckLogin
	public String a(String id, HttpSession session) {
		Product pd = IshenheService.selectChangPing(id);
		session.setAttribute("pageInfo5", pd);
		return "index/changpinginfo";
	}

	
	//通过产品审核
		@RequestMapping("Changpingpass")
		@CheckLogin
		public String Changpinpass(String passid, Integer state, HttpSession session) {
			IshenheService.ChangpingPass(passid, state);
			return "forward:menu-add3";
		}

		//拒绝产品审核
		@RequestMapping("ChangpinRefuse")
		@CheckLogin
		public String ChangpinRefuse(String refuseid, Integer state, HttpSession session) {
			IshenheService.ChangpingRefuse(refuseid, state);
			return "forward:menu-add3";
		}
	
	

}