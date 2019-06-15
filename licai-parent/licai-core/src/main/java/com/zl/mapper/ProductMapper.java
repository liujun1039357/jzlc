package com.zl.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zl.exception.JZLCException;
import com.zl.pojo.BigDecimalParam;
import com.zl.pojo.Product;

import com.zl.pojo.ProductShowInfo;
import com.zl.pojo.SelectCondition;


@Mapper
public interface ProductMapper {

	/**
	 * 获取产品
	 * */
	List<Product> getProducts();

	List<Product> queryProductByCond(SelectCondition sc);
	
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
	
	/**查询产品收益类型
	 * @param productId
	 * @return
	 */
	Integer queryProductProfitType(String productId) throws JZLCException;

	/**更新产品已售额度
	 * @param productId
	 */
	void updateProductSaledQuota(@Param("productId")String productId,@Param("buyMoney")BigDecimal buyMoney) throws JZLCException;

	/**查询可购买额度
	 * @param productId
	 * @return
	 */
	BigDecimal queryCanSaledQuota(String productId) throws JZLCException;

	/**检验购买金额是否大于起投金
	 * @param productId
	 * @param buyMoney
	 */
	int queryProductLowQuata(BigDecimalParam bigDecimalParam) throws JZLCException;
}
