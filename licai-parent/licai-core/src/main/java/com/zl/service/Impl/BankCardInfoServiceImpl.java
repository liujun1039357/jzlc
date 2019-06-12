/**
 * 
 */
package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.exception.JZLCException;
import com.zl.mapper.BankCardInfoMapper;
import com.zl.service.IBankCardInfoService;
import com.zl.util.UserContext;

/**
 * @author ivy
 *
 */
@Service
public class BankCardInfoServiceImpl implements IBankCardInfoService {
	@Autowired
	private BankCardInfoMapper bankCardInfoMapper;
	
	@Override
	public List<String> queryCardId() throws JZLCException {
		return bankCardInfoMapper.queryCardId(UserContext.getLogininfo().getConsumerId());
	}

}
