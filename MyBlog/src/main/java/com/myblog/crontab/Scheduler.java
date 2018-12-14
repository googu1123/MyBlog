package com.myblog.crontab;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	
	/**
	 * 1. 5초마다 호출이 되는 스케줄러
	 */
	@Scheduled(fixedDelay=5000)
	public void cronTest1()
	{
		System.out.println("5초마다 호출이 됩니다.(1)");
		
//		try {
//			Thread.sleep(10000);
//		}catch(InterruptedException e){
//			System.out.println(e.getMessage()); //sleep 메소드가 발생하는 InterruptedException 
//		}
		
		System.out.println("5초마다 호출이 됩니다.(2)");
	}
	
	/**
	 * 2. 오후 05:51:00에 호출이 되는 스케쥴러 
	 */
	@Scheduled(cron = "0 22 9 * * *")
	public void cronTest2()
	{
		System.out.println("오후 05:51:00에 호출이 됩니다 ");
	}
}
