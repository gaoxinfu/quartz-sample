package com.gaoxinfu.quartz.sample.standalone.sample05;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class Sample05Job implements Job {

    public static final String NUM_EXECUTIONS  = "NumExecutions";
    public static final String EXECUTION_DELAY = "ExecutionDelay";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String currentDate = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        //从jobDetail中获取业务数据
        System.out.println(Sample05Job.class.getSimpleName() + ",运行时间 currentDate = " + currentDate + ",jobKey = " + context.getJobDetail().getKey());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        int executeCount = 0;
        if (jobDataMap.containsKey(NUM_EXECUTIONS)) {
            executeCount = jobDataMap.getInt(NUM_EXECUTIONS);
        }
        executeCount++;
        jobDataMap.put(NUM_EXECUTIONS, executeCount);

        long delay = 5000l;
        if (jobDataMap.containsKey(EXECUTION_DELAY)) {
            delay = jobDataMap.getLong(EXECUTION_DELAY);
        }
        try {
            Thread.sleep(delay);
        } catch (Exception ignore) {
            //
        }
        currentDate = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        //System.out.println(Sample05Job.class.getSimpleName() + ",完成时间 currentDate = " + currentDate + ",jobKey = " + context.getJobDetail().getKey()+"--------------");
    }
}
