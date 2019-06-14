package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.exception.JZLCException;
import com.zl.pojo.Product;

import com.zl.pojo.SelectCondition;

import com.zl.pojo.ProductShowInfo;


@Mapper
public interface ProductMapper {

	/**
	 * 获取产品
	 * */
	List<Product> getProducts();

	/**查询产品详情
	 * @param productId
	 * @return
	 */
	ProductShowInfo queryProductInfo(String productId) throws JZLCException;

	/**查询产品种类
	 * @param productId
	 * @return
	 */
	int queryProductType(String productId) throws JZLCException;
	
	List<Product> queryProductByCond(SelectCondition sc);
}
