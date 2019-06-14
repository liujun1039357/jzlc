package com.zl.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.exception.LoginInfoException;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.ConsumerInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.service.ILogininfoService;
import com.zl.util.AjaxJson;
import com.zl.util.CheckLogin;
import com.zl.util.UserContext;

@Controller
public class LogininfoController {

	@Autowired
	private ILogininfoService logininfoService;

	@RequestMapping("login")
	public String Backstage() {
		return "index/login";
	}

	@RequestMapping("login2")
	@ResponseBody
	public AjaxJson login(String username, String password, HttpServletRequest request) {
		AjaxJson json = new AjaxJson();
//		System.out.println("进来了");
		try {
			boolean isOk = logininfoService.login(username, password,/*获取用户ip*/request.getRemoteAddr());
			// System.out.println("boolean:"+isOk);
			if (isOk == false) {
				json.setSuccess(false);
				json.setMsg("用户名或者密码错误");
			}
		} catch (LoginInfoException e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		// System.out.println("json:"+json.toString());
		return json;

	}

	/* 登入查询数据进入主页 */
	@GetMapping("mgindex")
	@CheckLogin
	public String personal(HttpSession session) {
	//	System.out.println(UserContext.getCurrent().toString());
		session.setAttribute("controller", UserContext.getCurrent());
		session.setAttribute("ConsumerRecord", logininfoService.queryById(UserContext.getCurrent().getJobNumber()));
		session.setAttribute("ConsumerInfo",logininfoService.queryCountConsumerInfo());
		session.setAttribute("BusinessInfo",logininfoService.queryCountBusinessInfo());
		session.setAttribute("Product",logininfoService.queryCountProduct());
		return "index/index";
	}

//	主页小图
	@RequestMapping(path = "welcome")
	public String wecome() {
		return "index/welcome";
	}
	

//	查看用户
	@RequestMapping("menu3")
	@CheckLogin
	public String menu3(@RequestParam(required=true,defaultValue="1")Integer pageindex,ConsumerInfoCondition cc,HttpSession session) {
	
		//在查询调用方法前声明分页信息（当前页，页容量）
		//PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex, 8);
		List<ConsumerInfo> emps = logininfoService.queryAll(cc);
	//	emps.forEach(System.out::print);
		/**再对查询结果进行包装成PageInfo对象,保存查询出的结果，PageInfo是pageHelper中的对象
		 * PageInfo有两个有参构造,其中一个设置了导航页码,如下
		 * */
		PageInfo<ConsumerInfo> pageInfo = new PageInfo<ConsumerInfo>(emps,3);

		//将数据存到域中
		// list.forEach(System.out::print);
		session.setAttribute("pageInfo", pageInfo);
		session.setAttribute("condition", cc);
		return "index/YongHu";
	}
	

	
	
//	修改用户
	@RequestMapping(path = "alter1")
	@CheckLogin
	public String menu2(String id, HttpSession session) {
		ConsumerInfo ci= logininfoService.selectYonghu(id);
		session.setAttribute("pageInfo6", ci);
		return "index/YongHuinfo";
	}
	


// 公告设置
	@RequestMapping("email-write")
	@CheckLogin
	public String email() {
		return "index/GongGao";
	}

	// 公告设置小图
	@RequestMapping("email-write2")
	public String email2() {
		return "index/email-write";
	}



	

	// 个人信息
	@RequestMapping("admin-info")
	@CheckLogin
	public String admin() {
		return "index/GeRen";
	}

	// 个人信息小图
	@RequestMapping("admin-info2")
	public String email3() {
		return "index/admin-info";
	}
}
