package com.zl.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	public String index(@RequestParam(required=true,defaultValue="1")Integer pageindex,Model model) {
		//在查询调用方法前声明分页信息（当前页，页容量）
	    //PageHelper.startPage(page, pageSize);这段代码表示，程序开始分页了，
		//page默认值是1，pageSize默认是10，意思是从第1页开始，每页显示10条记录。
		PageHelper.startPage(pageindex,6);
		List <Notice> notices =  iNoticeService.getNotices();
		PageInfo<Notice> noticePageInfo = new PageInfo<Notice>(notices);
		model.addAttribute("noticePageInfo",noticePageInfo);
		
		PageHelper.startPage(pageindex, 3);
		List<Product> plist = productService.queryProductByCond(null);
		PageInfo<Product> productPageInfo = new PageInfo<Product>(plist);
		model.addAttribute("productPageInfo",productPageInfo);
		return "index";
	}
	

	
	
	

}
