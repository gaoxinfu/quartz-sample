package com.gaoxinfu.quartz.sample.standalone.sample05;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;
import static org.quartz.DateBuilder.nextGivenSecondDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class Sample05JobTest {

    public static void main(String[] args) throws SchedulerException {
        System.out.println("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        System.out.println("------- Initialization Complete -----------");
        System.out.println("------- Scheduling Jobs -----------");

        // jobs can be scheduled before start() has been called get a "nice round" time a few seconds in the future...
        Date startTime = nextGivenSecondDate(null, 15);

        // Sample05Job01 每3s运营一次，10s之后开始运行
        JobDetail jobDetail = JobBuilder.newJob(Sample05Job.class)
                .withIdentity("sample05Job01", "sample-group")
                .usingJobData(Sample05Job.EXECUTION_DELAY, 10000L)
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "sample-group")
                .startAt(startTime)
                //每5s运营一次，
                .withSchedule(simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .build();

        Date ft = scheduler.scheduleJob(jobDetail, trigger);
        System.out.println(jobDetail.getKey() + " will run at: " + DateUtil.format(ft, DatePattern.NORM_DATETIME_MS_PATTERN) + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        // statefulJob2 will run every three seconds
        // (but it will delay for ten seconds - and therefore purposely misfire after a few iterations)
        jobDetail = JobBuilder.newJob(Sample05Job.class)
                .withIdentity("sample05Job02", "sample-group")
                .usingJobData(Sample05Job.EXECUTION_DELAY, 10000L)
                .build();

        //sample05Job02 每3s运行一次，10s之后执行，
        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "sample-group")
                .startAt(startTime)
                //每3s运营一次，
                .withSchedule(simpleSchedule().withIntervalInSeconds(3).repeatForever()
                //// set misfire instructions 默认的一个策略设置或者不设置都一样
                .withMisfireHandlingInstructionNowWithExistingCount())
                .build();

        ft = scheduler.scheduleJob(jobDetail, trigger);
        System.out.println(jobDetail.getKey() + " will run at: " + DateUtil.format(ft, DatePattern.NORM_DATETIME_MS_PATTERN) + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        System.out.println("------- Starting Scheduler ----------------");
        // jobs don't start firing until start() has been called...
        scheduler.start();
        System.out.println("------- Started Scheduler -----------------");

        try {
            // sleep for ten minutes for triggers to file....
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
            //
        }

        System.out.println("------- Shutting Down ---------------------");
        scheduler.shutdown(true);
        System.out.println("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = scheduler.getMetaData();
        System.out.println("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
}
