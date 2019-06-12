package com.zl.service;

import java.util.List;

import com.zl.exception.JZLCException;
import com.zl.pojo.Product;
import com.zl.pojo.ProductShowInfo;

public interface IProductService {
	
	List<Product> getProducts();

	/**
	 * @param productId
	 * @return
	 */
	ProductShowInfo queryProductInfo(String productId) throws JZLCException;

	/**
	 * @param productId
	 * @return
	 */
	int queryProductType(String productId) throws JZLCException;

}
