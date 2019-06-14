package com.zl.service;

import java.util.List;

import com.zl.pojo.Product;
import com.zl.pojo.SelectCondition;

public interface IProductService {
	
	List<Product> getProducts();

	List<Product> queryProductByCond(SelectCondition sc);
}
