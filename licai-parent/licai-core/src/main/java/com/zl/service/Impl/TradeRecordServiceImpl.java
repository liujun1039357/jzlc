/**
 * 
 */
package com.zl.service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.ProductMapper;
import com.zl.mapper.TradeRecordMapper;
import com.zl.service.ITradeRecordService;
import com.zl.util.UserContext;

/**
 * @author ivy
 *
 */
@Service
public class TradeRecordServiceImpl implements ITradeRecordService {
	@Autowired
	private TradeRecordMapper tradeRecordMapper;
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public BigDecimal querySumMoney(String productId) throws JZLCException {
		int productProfitType = productMapper.queryProductProfitType(productId);
		System.out.println(productProfitType);
		String consumerId = UserContext.getLogininfo().getConsumerId();
		if(productProfitType==0) {
			return tradeRecordMapper.querySumMoneyByDay(consumerId,productId);
		}else {
			return tradeRecordMapper.querySumMoneyByTerm(consumerId,productId);
		}
	}

}
