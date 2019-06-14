package com.zl.service.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ProductMapper;
import com.zl.pojo.BigDecimalParam;
import com.zl.pojo.Product;
import com.zl.pojo.ProductShowInfo;
import com.zl.service.IProductService;
import com.zl.util.DateUtil;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> getProducts() {
		List<Product> products= productMapper.getProducts();
		return products;
	}

	@Override
	public ProductShowInfo queryProductInfo(String productId) throws JZLCException {
		ProductShowInfo productShowInfo = productMapper.queryProductInfo(productId);
		productShowInfo.setRevenueTime(DateUtil.addDays(new Date(), productShowInfo.getAcountCycle()));
		return productShowInfo;
	}

	@Override
	public int queryProductType(String productId) throws JZLCException {
		return productMapper.queryProductType(productId);
	}
	
	@Override
	public boolean checkProductLowQuata(String productId, BigDecimal buyMoney) throws JZLCException {
		BigDecimalParam bigDecimalParam = new BigDecimalParam();
		bigDecimalParam.setStr(productId);
		bigDecimalParam.setNum1(buyMoney);
		System.out.println(bigDecimalParam.getNum1());
		int check = productMapper.queryProductLowQuata(bigDecimalParam);
		System.out.println(check);
		if(check>0) {
			return false;
		}
		return true;
	}
}
