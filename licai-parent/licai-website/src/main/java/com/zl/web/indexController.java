package com.zl.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zl.pojo.Notice;
import com.zl.pojo.Product;
import com.zl.service.INoticeService;
import com.zl.service.IProductService;

@Controller
public class indexController {
	@Autowired
	private IProductService productService;	
	@Autowired
	private INoticeService iNoticeService;

	@RequestMapping("index")
	public String index(Model model) {
	
		Collection <Product> products =  productService.getProducts();
		Collection <Notice> notices =  iNoticeService.getNotices();
		
		model.addAttribute("products",products);
		model.addAttribute("notices",notices);
		return "index";
	}
	

	
	
	

}
