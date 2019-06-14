package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.zl.pojo.Business;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.Product;
import com.zl.pojo.ProductManager;
import com.zl.pojo.SelectCondition;

@Mapper
public interface BusinessMapper {
	void insertSimpleBusinssInfo(Business businss);
	void insertBusinssInfo(BusinessInfo businssInfo );
	Business querySimpleBusinessInfoByEmail(Business businss);
	BusinessInfo queryBusinessInfoById(Business bs);
	void addProductManger(ProductManager pm);
	List<ProductManager> queryProductMangerByBID(SelectCondition sc);
	List<Product> queryProductByBID(SelectCondition sc);
	Product queryProductByPID(String pid);
}
