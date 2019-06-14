package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ProductMapper;
import com.zl.pojo.Product;
import com.zl.pojo.SelectCondition;
import com.zl.pojo.ProductShowInfo;
import com.zl.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductMapper productMapper;

	@Override
	public List<Product> getProducts() {
		List<Product> products = productMapper.getProducts();
		return products;
	}

	@Override
	public List<Product> queryProductByCond(SelectCondition sc) {
		// TODO Auto-generated method stub
		return productMapper.queryProductByCond(sc);
	}

	public ProductShowInfo queryProductInfo(String productId) throws JZLCException {
		return productMapper.queryProductInfo(productId);
	}

	@Override
	public int queryProductType(String productId) throws JZLCException {
		return productMapper.queryProductType(productId);

	}
}
