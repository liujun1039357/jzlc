package com.zl.service;

import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.util.AjaxJson;

public interface IBusinessService {
	/*
	 * 企业注册
	 */
	AjaxJson registerBusiness(Business bs,BusinessInfo bsi);
	
	AjaxJson loginBusiness(Business bs);
}
