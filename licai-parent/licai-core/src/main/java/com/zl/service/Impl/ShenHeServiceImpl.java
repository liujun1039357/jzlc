package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zl.mapper.ShenHeMapper;
import com.zl.pojo.BusinessInfo;
import com.zl.pojo.ConsumerInfoCondition;
import com.zl.pojo.Product;
import com.zl.service.IShenHeService;

@Service
public class ShenHeServiceImpl implements IShenHeService {
	
	@Autowired
	private ShenHeMapper shenheMapper ;

	@Override
	public List<BusinessInfo> SelectOneQiye(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectOneQiye(cc);
	}

	@Override
	public List<BusinessInfo> SelectTwoQiye(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectTwoQiye(cc);
	}

	@Override
	public List<BusinessInfo> SelectThreeQiye(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectThreeQiye(cc);
	}
	
	@Override
	public List<BusinessInfo> SelectFulfillQiye(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectFulfillQiye(cc);
	}


	@Override
	public List<Product> SelectOneChangping(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectOneChangping(cc);
	}

	@Override
	public List<Product> SelectTwoChangping(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectTwoChangping(cc);
	}

	@Override
	public List<Product> SelectThreeChangping(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectThreeChangping(cc);
	}
	
	@Override
	public List<Product> SelectFulfillChangping(ConsumerInfoCondition cc) {
		// TODO Auto-generated method stub
		return shenheMapper.SelectFulfillChangping(cc);
	}


	@Override
	public BusinessInfo selectQiye(String id) {
		return shenheMapper.selectQiye(id);
	}

	@Override
	public void Pass(String passid, Integer state) {
		shenheMapper.alterPass(passid, state);
	}

	@Override
	public void Refuse(String refuseid, Integer state) {
		shenheMapper.alterRefuse(refuseid, state);
	}

	@Override
	public Product selectChangPing(String id) {
		return shenheMapper.selectChangping(id);
	}

	
	
}
