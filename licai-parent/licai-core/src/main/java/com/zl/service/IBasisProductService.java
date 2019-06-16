package com.zl.service;

import java.util.List;

import com.zl.pojo.BasisProduct;

public interface IBasisProductService {
	/** 用户户查询简单产品信息列表(联合企业信息表) */
	List<BasisProduct> findBasisProducts();
	/**根据条件查询*/
	List<BasisProduct> findByCondition(BasisProduct basisProduct);

}
