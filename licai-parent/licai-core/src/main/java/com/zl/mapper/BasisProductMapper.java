package com.zl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zl.pojo.BasisProduct;
@Mapper
public interface BasisProductMapper {
	/** 用户查询产品简单信息（联合企业表）    */
	List<BasisProduct>  queryBasisProducts(); 

}
