package com.gaoxinfu.quartz.sample.standalone.sample01;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.gaoxinfu.quartz.sample.standalone.sample02.Sample02Job;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class Sample01Job implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String currentDate = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_MS_PATTERN);
        System.out.println(Sample02Job.class.getSimpleName()+",currentDate = "+currentDate);
    }
}
