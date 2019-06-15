/**
 * 
 */
package com.zl.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.mapper.ConsumerInfoMapper;
import com.zl.mapper.ProfitListMapper;
import com.zl.mapper.TradeRecordMapper;
import com.zl.pojo.Profit;
import com.zl.pojo.TradeRecord;
import com.zl.pojo.BigDecimalParam;
import com.zl.service.ITimingTasksService;

/**
 * @author ivy
 *
 */
@Service
public class TimingTasksServiceImpl implements ITimingTasksService {
	@Autowired
	private ConsumerInfoMapper consumerInfoMapper;
	@Autowired
	private TradeRecordMapper tradeRecodeMapper;
	@Autowired
	private ProfitListMapper profitListMapper;
	
	@Override
	public void calculatSumMoneyTest() {
		consumerInfoMapper.updateSumMoneyTest();
	}
	
	@Override
	public void insertProfitlist() {
		List<TradeRecord> tradeRecords = tradeRecodeMapper.queryTradeRecords(TradeRecord.BUY);
		if(tradeRecords.size()>0) {
			for (TradeRecord tradeRecord : tradeRecords) {
				Profit profit = new Profit();
				profit.setConsumerId(tradeRecord.getConsumerId());
				profit.setProductName(tradeRecord.getProductName());
				profit.setPrincipal(tradeRecord.getBaseMoney());
				profit.setProfit(tradeRecord.getInterest());
				profitListMapper.insertProfitList(profit);
			}
		}
	}

	//余额+今日所有利息+所有本金
	@Override
	public void calculatSumMoney() {
		List<String> consumers = consumerInfoMapper.queryConsumers();
		if(consumers.size()>0) {
			for (String consumerId : consumers) {
				BigDecimalParam bigDecimalParam = new BigDecimalParam();
				bigDecimalParam.setStr(consumerId);
				//更新用户余额(加上平台利息)
				consumerInfoMapper.updateBalance(consumerId);
				//查询每个用户今日所有利息.
				bigDecimalParam.setNum1(profitListMapper.querySumProfit(consumerId));
				//查询所有购买产品的本金
				bigDecimalParam.setNum2(tradeRecodeMapper.querySumPrincipal(consumerId, TradeRecord.BUY));
				//更新总金额
				consumerInfoMapper.updateSumMoney(bigDecimalParam);
			}
		}
	}

	@Override
	public void calculatProfit() {
		List<String> tradeRecords = tradeRecodeMapper.queryTradeRecordId(TradeRecord.BUY,TradeRecord.DAY);
		if(tradeRecords.size()>0) {
			for (String tradeRecordId : tradeRecords) {
				tradeRecodeMapper.updateInterest(tradeRecordId);
			}
		}
	}

	@Override
	public void automaticTurnOut() {
		List<String> tradeRecords = tradeRecodeMapper.queryTermTradeRecord(TradeRecord.BUY,TradeRecord.TERM);
		if(tradeRecords.size()>0) {
			for (String tradeRecordId : tradeRecords) {
				TradeRecord tradeRecord = tradeRecodeMapper.queryTradeRecord(tradeRecordId);
				new ConsumerInfoServiceImpl().turnOutProduct(tradeRecord.getProductId(), tradeRecord.getBaseMoney().add(tradeRecord.getInterest()));
			}
		}
	}

}
