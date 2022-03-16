package com.gaoxinfu.quartz.sample.standalone.sample04;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Sample04JobTest {

    public static void main(String[] args) throws SchedulerException {
        String currentClassName =  Thread.currentThread().getStackTrace()[1].getClassName();

        JobDetail jobDetail = JobBuilder.newJob(Sample04Job.class).withIdentity("sample04Job","sample-group").build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("sample04-trigger","sample-group")
                //每5s执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        Date firstRunTime = scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
        System.out.println(currentClassName+",第一次运行的时间 = "+ DateUtil.format(firstRunTime, DatePattern.NORM_DATETIME_PATTERN)+",cron表达式 = "+trigger.getCronExpression());
    }
}
