package com.koreait.integration1.batch;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class SearchListener implements ServletContextListener {

	private Scheduler scheduler;
	private SchedulerFactory factory;
	
	public SearchListener() {
		
		try {
			factory = new StdSchedulerFactory();
			scheduler = factory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("-------시작-------");
		JobDetail job = JobBuilder.newJob(SearchJob.class)
						.withIdentity("mySearchJob", "mySearchGroup")
						.build();
		
		CronTrigger trigger = TriggerBuilder.newTrigger()
							  .withIdentity("mySearchJob", "mySearchGroup")
							  .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
							  .build();
		try {
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("-------종료-------");
		
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
