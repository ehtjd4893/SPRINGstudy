package com.dosung.practice.batch;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BoardJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello job");

	}

}
