package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.mapper.TradeListMapper;
import com.zl.pojo.TradeList;
import com.zl.service.ITradeListService;

@Service
public class TradeListServiceImpl implements ITradeListService {
	@Autowired
	private TradeListMapper tradeListMapper;

	@Override
	public List<TradeList> getTradeLists() {
		List<TradeList> tradeLists= tradeListMapper.getTradeLists();
		return tradeLists;
	}
	
	
	


}
