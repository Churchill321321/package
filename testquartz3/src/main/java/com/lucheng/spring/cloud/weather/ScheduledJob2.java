package com.lucheng.spring.cloud.weather;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/18 17:41
 */
public class ScheduledJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.err.println("第二个任务正在运行 is running…………………………………… ");

    }

}
