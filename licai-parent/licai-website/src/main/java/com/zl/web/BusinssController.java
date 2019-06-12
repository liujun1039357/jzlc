package com.zl.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.City;
import com.zl.pojo.Provincial;
import com.zl.service.IBusinessService;
import com.zl.service.IProvincesService;
import com.zl.util.AjaxJson;
import com.zl.util.EmailUtils;
import com.zl.util.UploadUtil;

@Controller
public class BusinssController {

	@Autowired
	private IProvincesService provincesService;
	@Autowired
	private IBusinessService businessService;
	
	
	@RequestMapping("loginBusiness")
	@ResponseBody
	public AjaxJson loginBusiness(Business business,HttpSession hs) {
		AjaxJson aj = businessService.loginBusiness(business);
		return aj;
	}
	
	
	
	
	
	
	@RequestMapping("enterpriseRegister")
	@ResponseBody
	public AjaxJson enterpriseRegister(Business business, BusinessInfo businessInfo,HttpSession hs,String provincial,String city) {
		 
		String useremail = (String) hs.getAttribute("email");
		business.setEmail(useremail);
		String address = provincial+city;
		businessInfo.setAddress(address);
		AjaxJson aj = businessService.registerBusiness(business, businessInfo);
		return aj;
	}

	@RequestMapping("querycitybypid")
	@ResponseBody
	public List<City> querycitybypid(String pname) {
		System.out.println("pname");
		List<City> cityList = provincesService.querycitybyPid(pname);
		return cityList;
	}

	@RequestMapping("picupload")
	@ResponseBody
	public String realAuthUpload(MultipartFile file) {
		System.out.println("123321");
		String fileName = UploadUtil.upload(file);
		System.err.println(fileName);
		return "/upload/" + fileName;
	}

	@RequestMapping("emailcodecheckaction")
	public String emailcode(HttpSession hs, String businssemail, Integer emailcheckcode, HttpServletRequest hsr) {
		System.out.println("123");
		Integer randomemailcode = (Integer) hs.getAttribute("randomemailcode");
		System.err.println("email=" + businssemail);

		System.err.println("emailcheckcode=" + emailcheckcode);
		String useremail = (String) hs.getAttribute("email");
		System.err.println("useremail=" + useremail);
		System.err.println("randomemailcode=" + randomemailcode);
		hsr.setAttribute("isSuccess", true);
		System.out.println(emailcheckcode.equals(randomemailcode));
		System.out.println(useremail.equals(businssemail));
		if (emailcheckcode.equals(randomemailcode) && useremail.equals(businssemail)) {
			hsr.setAttribute("isSuccess", false);
			System.err.println("成功了");
			// 加载省级城市信息
			List<Provincial> provincialList = provincesService.queryallProvincial();
			hsr.setAttribute("provincialList", provincialList);
			for (Provincial provincial : provincialList) {
				System.out.println(provincial.toString());
			}
			return "enterprise/eregister2";
		} else {

			System.err.println("失败了");
			return "register";
		}
	}

	@RequestMapping("emailcode")
	@ResponseBody
	public AjaxJson sendEmail(String businssemail, HttpSession hs) {
		AjaxJson aj = new AjaxJson();
		EmailUtils eu = new EmailUtils();
		System.out.println("email" + businssemail);
		try {
			Integer code = eu.sendAccountActivateEmail(businssemail);
			/*
			 * Integer emailcheckcode = (Integer) hs.getAttribute("randomemailcode");
			 * System.err.println("emailcheckcode="+emailcheckcode);
			 */
			aj.setMsg("邮件发送成功，请登录相应邮箱激活");
			aj.setSuccess(true);
			hs.setAttribute("email", businssemail);
			System.err.println(businssemail);

		} catch (Exception e) {
			e.printStackTrace();
			aj.setMsg("邮件发送失败");
			aj.setSuccess(false);
		}
		return aj;
	}

}
