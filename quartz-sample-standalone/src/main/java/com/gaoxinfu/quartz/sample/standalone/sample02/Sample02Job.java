package com.gaoxinfu.quartz.sample.standalone.sample02;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import java.util.Date;

public class Sample02Job implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        String currentDate = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        //从jobDetail中获取业务数据
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println(Sample02Job.class.getSimpleName()+",currentDate = "+currentDate+",name = "+jobDataMap.get("name"));
    }
}
