package com.gaoxinfu.quartz.sample.standalone.sample02;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Sample02JobTest {

    public static void main(String[] args) throws SchedulerException {
        //业务参数数据设置
        JobDetail jobDetail = JobBuilder.newJob(Sample02Job.class)
                .withIdentity("firstJob","my-group")
                .usingJobData("name","gaoxinfu")
                .usingJobData("sex","man")
                .build();

        //调用的频率设置
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("firstTrigger","my-group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever())
                .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();
    }
}
