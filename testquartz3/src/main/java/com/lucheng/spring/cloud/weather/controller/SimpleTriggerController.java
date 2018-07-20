package com.lucheng.spring.cloud.weather.controller;


import com.lucheng.spring.cloud.weather.ScheduledJob;
import com.lucheng.spring.cloud.weather.ScheduledJob2;
import org.quartz.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/20 9:41
 */
@RestController
public class SimpleTriggerController {
    @Resource(name = "scheduler")
    private Scheduler scheduler;
    @GetMapping("/simpleTrigger")
    public String testSimpleTrigger() throws SchedulerException {

        //创建一个jobDetail
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob2.class).withIdentity("job2", "group2").build();

        //创建一个SimpleTrigger对象
        SimpleTrigger trigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger2", "group2")
                .startAt(futureDate(5, DateBuilder.IntervalUnit.SECOND)) // some Date
                .build();
        //使用Scheduler对象来调度任务
        scheduler.scheduleJob(jobDetail,trigger);
        return "testSimpleTrigger";
    }
}
