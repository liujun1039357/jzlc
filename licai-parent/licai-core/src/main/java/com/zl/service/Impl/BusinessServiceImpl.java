package com.zl.service.Impl;

import org.springframework.stereotype.Service;

import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.ProductManager;
import com.zl.service.IBusinessService;
import com.zl.util.AjaxJson;

@Service
public class BusinessServiceImpl implements IBusinessService {

	@Override
	public AjaxJson registerBusiness(Business bs, BusinessInfo bsi) {
		return null;
	}

	@Override
	public AjaxJson loginBusiness(Business bs) {
		return null;
	}

	@Override
	public BusinessInfo queryBusinessInfoByID(Business bs) {
		return null;
	}

	@Override
	public AjaxJson addProductManger(ProductManager pm) {
		return null;
	}

	
}
