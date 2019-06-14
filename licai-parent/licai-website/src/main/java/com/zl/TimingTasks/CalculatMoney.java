/**
 * 
 */
package com.zl.TimingTasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zl.service.ITimingTasksService;

/**定时任务
 * @author ivy
 *@Scheduled 定义定时任务的信息  属性 cron表达式:定义执行的时间
 */
@Configuration
@EnableScheduling
public class CalculatMoney {
	@Autowired
	private ITimingTasksService timingTasksService;
	
	/*测试用,每10给sumMoney加1*/
	/*@Scheduled(cron="0/10 * * * * ?")
	public void calculatSumMoneyTest(){
		timingTasksService.calculatSumMoneyTest();
	}*/
	
	/**插入收益流水 每天23:00*/
	//@Scheduled(cron="0/10 * * * * ?")
	@Scheduled(cron="0 10 23 * * ?")
	public void insertProfitlist(){
		timingTasksService.insertProfitlist();
	}
	
	/**计算总金额 每天23:10*/
	//@Scheduled(cron="0/10 * * * * ?")
	@Scheduled(cron="0 20 23 * * ?")
	public void calculatSumMoney(){
		timingTasksService.calculatSumMoney();
	}
	
	/**更新利息 每天23:20*/
	//@Scheduled(cron="0/10 * * * * ?")
	@Scheduled(cron="0 30 23 * * ?")
	public void calculatProfit(){
		timingTasksService.calculatProfit();
	}
	
	/**期结产品到期自动转出  每天23:30*/
	//@Scheduled(cron="0/10 * * * * ?")
	@Scheduled(cron="0 40 23 * * ?")
	public void automaticTurnOut(){
		timingTasksService.automaticTurnOut();
	}
}
