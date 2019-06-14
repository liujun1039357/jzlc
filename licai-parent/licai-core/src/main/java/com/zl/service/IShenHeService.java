package com.zl.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zl.exception.LoginInfoException;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.Product;

public interface IShenHeService {

	// <!-- 查询一级企业 -->
	List<BusinessInfo> SelectOneQiye(ConsumerInfoCondition cc);

	// <!-- 查询二级企业 -->
	List<BusinessInfo> SelectTwoQiye(ConsumerInfoCondition cc);

	// <!-- 查询三级企业 -->
	List<BusinessInfo> SelectThreeQiye(ConsumerInfoCondition cc);

	// <!-- 查询审核完成企业 -->
	List<BusinessInfo> SelectFulfillQiye(ConsumerInfoCondition cc);

	// <!-- 查询一级产品 -->
	List<Product> SelectOneChangping(ConsumerInfoCondition cc);

	// <!-- 查询二级产品 -->
	List<Product> SelectTwoChangping(ConsumerInfoCondition cc);

	// <!-- 查询三级产品 -->
	List<Product> SelectThreeChangping(ConsumerInfoCondition cc);

	// <!-- 查询审核完成产品 -->
	List<Product> SelectFulfillChangping(ConsumerInfoCondition cc);

	// <!-- 根据id查询企业 -->
	BusinessInfo selectQiye(String id);

	// 通过修改企业审核状态码
	void Pass(String passid, Integer state);

	// 拒绝修改企业审核状态码
	void Refuse(String refuseid, Integer state);

	// <!-- 根据id查询产品 -->
	Product selectChangPing(String id);

}
