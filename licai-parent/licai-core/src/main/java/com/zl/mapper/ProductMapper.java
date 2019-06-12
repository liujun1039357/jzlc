package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.Product;

@Mapper
public interface ProductMapper {

	/**
	 * 获取产品
	 * */
	List<Product> getProducts();
	
}
/*
*/