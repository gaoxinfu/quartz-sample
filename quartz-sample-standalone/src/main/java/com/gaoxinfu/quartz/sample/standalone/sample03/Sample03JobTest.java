package com.gaoxinfu.quartz.sample.standalone.sample03;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Sample03JobTest {

    public static void main(String[] args) throws SchedulerException {
        String currentClassName =  Thread.currentThread().getStackTrace()[1].getClassName();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(Sample03Job.class).withIdentity("sample03Job","sample-group").build();

        Trigger trigger01 = TriggerBuilder.newTrigger().withIdentity("sample03Trigger-01","sample-group")
                //每1s跑一次job
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .startNow().build();
        //绑定job与trigger：设置job使用的trigger信息，即job调用的频率
        scheduler.scheduleJob(jobDetail,trigger01);

        Trigger trigger02 = TriggerBuilder.newTrigger().withIdentity("sample03Trigger-02","sample-group")
                //每1s跑一次job
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .startNow().build();
        //绑定job与trigger：设置job使用的trigger信息，即job调用的频率
        scheduler.scheduleJob(jobDetail,trigger02);

        //启动schedule
        scheduler.start();
        System.out.println(currentClassName+",Started Scheduler ");
    }
}
