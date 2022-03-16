package com.gaoxinfu.quartz.sample.standalone.sample01;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Sample01JobTest {

    public static void main(String[] args) throws SchedulerException {
        String currentClassName =  Thread.currentThread().getStackTrace()[1].getClassName();

        System.out.println(currentClassName+",初始化JobDetail开始");
        JobDetail jobDetail = JobBuilder.newJob(Sample01Job.class).withIdentity("sample01Job","sample-group").build();
        System.out.println(currentClassName+",初始化JobDetail完成");

        System.out.println(currentClassName+",初始化Trigger开始");
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("sample01Trigger","sample-group")
                //每1s跑一次job
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .startNow().build();
        System.out.println(currentClassName+",初始化Trigger完成");

        System.out.println(currentClassName+",初始化Scheduler开始");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        System.out.println(currentClassName+",初始化Scheduler完成");

        //绑定job与trigger：设置job使用的trigger信息，即job调用的频率
        scheduler.scheduleJob(jobDetail,trigger);
        //启动schedule
        scheduler.start();
        System.out.println(currentClassName+",Started Scheduler ");

        try {
            //睡眠10s
            System.out.println(currentClassName+",睡眠10s开始");
            Thread.sleep(10 * 1000L);
            System.out.println(currentClassName+",睡眠10s完成");
        } catch (InterruptedException e) {
            //do nothing
        }

        System.out.println(currentClassName+",停止schedule开始");
        scheduler.shutdown();
        System.out.println(currentClassName+",停止schedule完成");
    }
}
