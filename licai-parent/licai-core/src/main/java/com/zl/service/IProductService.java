package com.zl.service;

import java.math.BigDecimal;
import java.util.List;

import com.zl.exception.JZLCException;
import com.zl.pojo.Product;
import com.zl.pojo.ProductRecordCondition;
import com.zl.pojo.SelectCondition;
import com.zl.pojo.ProductShowInfo;

public interface IProductService {
	
	List<Product> getProducts() throws JZLCException;

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

	/**检验购买金额是否大于起投金
	 * @param productId
	 * @param buyMoney
	 * @return
	 */
	boolean checkProductLowQuata(String productId, BigDecimal buyMoney);

	List<Product> queryProductByCond(SelectCondition sc);
	/** 添加产品  */
	boolean addProduct(Product product);
	/** 审核进度 
	 * @param preCondition */
	List<Product> findProductRecord(ProductRecordCondition preCondition);
}
