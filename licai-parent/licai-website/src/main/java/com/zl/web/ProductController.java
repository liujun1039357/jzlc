/**
 * 
 */
package com.zl.web;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zl.pojo.ProductShowInfo;
import com.zl.service.IProductService;

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
	
	
}
