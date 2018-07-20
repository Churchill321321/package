package com.lucheng.spring.cloud.weather;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/18 17:39
 */
public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.err.println("这是第一个任务 is running…………………………………… ");

    }
}