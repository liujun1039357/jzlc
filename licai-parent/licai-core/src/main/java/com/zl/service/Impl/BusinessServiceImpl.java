package com.zl.service.Impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.zl.mapper.BusinessMapper;
import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.Product;
import com.zl.pojo.ProductManager;
import com.zl.pojo.SelectCondition;
import com.zl.service.IBusinessService;
import com.zl.util.AjaxJson;

@Service
public class BusinessServiceImpl implements IBusinessService {

	@Autowired
	private BusinessMapper businessMapper;
	
	
	@Override
	public AjaxJson registerBusiness(Business bs, BusinessInfo bsi) {
		return null;
	}

	@Override
	public AjaxJson loginBusiness(Business bs) {
		return null;
	}



	@Override
	public AjaxJson addProductManger(ProductManager pm) {
		AjaxJson aj = new AjaxJson();
		try {
			businessMapper.addProductManger(pm);
			aj.setSuccess(true);
			aj.setMsg("经理人信息已提交");
		} catch (Exception e) {
			e.printStackTrace();
			aj.setMsg("提交失败");
			aj.setSuccess(false);
			return aj;
		}
		
		return aj;
	}


	@Override
	public List<ProductManager> queryProductMangerByBID(SelectCondition sc) {
		return businessMapper.queryProductMangerByBID(sc);
	}


	@Override
	public List<Product> queryProductByBID(SelectCondition sc) {
		// TODO Auto-generated method stub
		return businessMapper.queryProductByBID(sc);
	}


	@Override
	public Product queryProductByPID(String pid) {
		// TODO Auto-generated method stub
		return businessMapper.queryProductByPID(pid);
	}


	@Override
	public BusinessInfo queryBusinessInfoByID(Business bs) {
		return null;
	}


}
