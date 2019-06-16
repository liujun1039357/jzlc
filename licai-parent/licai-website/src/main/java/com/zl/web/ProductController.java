/**
 * 
 */
package com.zl.web;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zl.pojo.Product;
import com.zl.pojo.ProductRecordCondition;
import com.zl.pojo.ProductShowInfo;
import com.zl.service.IProductService;
import com.zl.util.AjaxJson;
import com.zl.util.DateUtil;

/**
 * @author ivy
 *
 */
@Controller
public class ProductController {
	@Autowired
	private IProductService productService;
	
	@RequestMapping("productInfo")
	public String queryProductInfo(String productId,HttpServletRequest request) {
		ProductShowInfo product = productService.queryProductInfo(productId);
		request.setAttribute("product", product);
		return "personal/productInfo";
	}
	/**
	 * 	产品添加
	 * @param response
	 * @param request
	 * @param product
	 * @return
	 */
	@RequestMapping("addProductList")
	@ResponseBody
	public AjaxJson addProductList(HttpServletRequest request, Product product) {
		AjaxJson json = new AjaxJson();
		boolean flag = true;
		try {
			flag = productService.addProduct(product);
			product.setProductEffectDate(DateUtil.startOfDate(product.getProductEffectDate()));
			product.setProductSaledTime(DateUtil.startOfDate(product.getProductSaledTime()));
			product.setProductLowProfitDate(DateUtil.startOfDate(product.getProductLowProfitDate()));
			if(flag == false) {
				json.setSuccess(false);
				json.setMsg("添加产品失败!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("系统忙!稍后重试...");
		}
		return json;
		/*	boolean flag = productService.addProduct(product);
		product.setProductEffectDate(DateUtil.startOfDate(product.getProductEffectDate()));
		product.setProductSaledTime(DateUtil.startOfDate(product.getProductSaledTime()));
		product.setProductLowProfitDate(DateUtil.startOfDate(product.getProductLowProfitDate()));
		if(flag) {
			
			return "enterprise/addProduct";
		}else {
			System.out.println("添加失败");
		}
//		System.out.println("返回空");
		return null;*/
	}
	
	/**
	 * 产品审核记录
	 * @param productCheckList
	 * @return
	 */
	@RequestMapping("productRecord")
	public String productAuditProgress(@RequestParam(required=true,defaultValue="1")Integer pageindex,
			ProductRecordCondition preCondition,Model model) {
//		System.out.println("aaa");
//		System.err.println(preCondition.toString());
		if(preCondition.getStart()==null && preCondition.getEnd()==null && preCondition.getCheckFlag()==null) {
			preCondition.setStart(new Date(0l));
			preCondition.setEnd(new Date());
			preCondition.setCheckFlag(1);
		}
		
		PageHelper.startPage(pageindex, 4);
		List<Product> proCheList = productService.findProductRecord(preCondition);
		
		PageInfo<Product> pageInfo = new PageInfo<Product>(proCheList,5);
//		System.out.println(proCheList);
//		System.out.println("bbb");
		model.addAttribute("pageInfo", pageInfo);
//		model.addAttribute("proCheLists", proCheList);
		model.addAttribute("preConditions", preCondition);
//		System.out.println(preCondition);
//		System.out.println("ccc");
		return "enterprise/productRecord";
	}
	
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
	} 
	
}
