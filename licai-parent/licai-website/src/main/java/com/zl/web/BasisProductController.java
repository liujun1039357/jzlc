package com.zl.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.pojo.BasisProduct;
import com.zl.service.IBasisProductService;

@Controller
public class BasisProductController {
	@Autowired
	private IBasisProductService basisProductService;
	/**
	 * 用户查询产品信息列表
	 * @param binder
	 */
	@RequestMapping("invest")
	public String invest(HttpServletRequest request) {
		System.out.println("aaa");
		List<BasisProduct> proBasList = basisProductService.findBasisProducts();
		System.out.println(proBasList);
		System.out.println("bbb");
		request.setAttribute("proBasLists", proBasList);
		System.out.println("ccc");
		return "personal/invest";
	}

	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	} 

}
