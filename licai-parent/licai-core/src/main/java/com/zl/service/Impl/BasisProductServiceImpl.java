package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.mapper.BasisProductMapper;
import com.zl.pojo.BasisProduct;
import com.zl.service.IBasisProductService;

@Service
public class BasisProductServiceImpl implements IBasisProductService {
	@Autowired
	private BasisProductMapper basisProductMapper;
	@Override
	public List<BasisProduct> findBasisProducts() {
		return basisProductMapper.queryBasisProducts();
	}
}
