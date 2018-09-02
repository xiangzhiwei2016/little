package com.fengdu.schedule;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Named
@Singleton
public class JobUtils {
	private static final Logger logger = Logger.getLogger(JobUtils.class);
	
	private static final String JOB_GROUP="fixture";
	
	private static final String JOB_NAME="clearImages";
	
	// cron表达式（每天的晚上12点）0 0 0 * * ? 
	private static final String CRON = "0 0 0 * * ?";
	
	@Inject
	public SchedulerFactoryBean schedulerFactoryBean;
	
	@PostConstruct
	public void addJob() throws SchedulerException {
		//
		logger.info("定时任务初始化。。。。。。。。。。。。。。。。。。。");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		logger.info(scheduler + ".............添加");
		// 触发器
		TriggerKey triggerKey = TriggerKey.triggerKey(JOB_NAME, JOB_GROUP);
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		if (null == trigger) {
			// 不存在，创建个
			Class<ClearImagesJob> clazz = ClearImagesJob.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz)
					.withIdentity(JOB_NAME, JOB_GROUP).build();
			// 可以添加参数
			// jobDetail.getJobDataMap().put(JOB_NAME, "hello,word");

			// cron表达（每3秒执行一次）
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(CRON);
			trigger = TriggerBuilder.newTrigger()
					.withIdentity(JOB_NAME, JOB_GROUP)
					.withSchedule(scheduleBuilder).build();

			// 定时任务
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// 存在，则更新对应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(CRON);
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}
}
