package com.zl.service;

import java.util.List;

import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.Product;
import com.zl.pojo.ProductManager;
import com.zl.pojo.SelectCondition;
import com.zl.util.AjaxJson;

public interface IBusinessService {
	/*
	 * 企业注册
	 */
	AjaxJson registerBusiness(Business bs, BusinessInfo bsi);

	AjaxJson loginBusiness(Business bs);

	
	 BusinessInfo queryBusinessInfoByID(Business bs) ;
	
	 AjaxJson addProductManger(ProductManager pm);
	 
	 List<ProductManager> queryProductMangerByBID(SelectCondition selectCondition);
	 
	 List<Product> queryProductByBID(SelectCondition sc);
		Product queryProductByPID(String pid);





}
