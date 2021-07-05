package com.dosung.practice.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import com.dosung.practice.batch.BoardJob;

@WebListener
public class BoardListener implements ServletContextListener {

	private Scheduler scheduler;
	private SchedulerFactory factory;
	
	public BoardListener() {
		try {
			factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("시~작");
		try {
			JobDetail job = JobBuilder.newJob(BoardJob.class)
					.withIdentity("myBoardJob", "myGroup")
					.build();
			CronTrigger trigger = TriggerBuilder.newTrigger()
							.withIdentity("myBoardTrigger", "myGroup")
							.withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
							.build();
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
			
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("끝");
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
