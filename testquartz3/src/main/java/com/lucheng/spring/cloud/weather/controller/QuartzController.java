package com.lucheng.spring.cloud.weather.controller;

import com.lucheng.spring.cloud.weather.JsonResult.QuartzVo;
import com.lucheng.spring.cloud.weather.ScheduledJob;
import com.lucheng.spring.cloud.weather.ScheduledJob2;
import org.quartz.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

import static org.quartz.DateBuilder.futureDate;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/18 17:42
 */
@Controller
public class QuartzController {

    @Resource(name="multitaskScheduler")
    private Scheduler scheduler2;

    @Resource(name = "scheduler")
    private Scheduler scheduler;

    @ResponseBody
    @RequestMapping(value = "/multitask", method = RequestMethod.POST)
    public String multitask(@RequestBody QuartzVo quartzVo) throws SchedulerException {
       scheduleJob(scheduler2,quartzVo);
       return "多任务";

    }

    @ResponseBody
    @RequestMapping("/multitask2")
    public String multitask2() throws SchedulerException {
        scheduleJob2(scheduler2);

        return "这个也是多任务";

    }


    //具体的定时任务
    private void scheduleJob(Scheduler scheduler,QuartzVo quartzVo) throws SchedulerException{
        //配置jobdetail
        JobDetail jobDetail = scheduler.getJobDetail(new JobKey(quartzVo.getAction()+quartzVo.getSerialNumber()+"job",quartzVo.getAction()+quartzVo.getSerialNumber()+"group"));
        if(jobDetail==null){
            jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity(quartzVo.getAction()+quartzVo.getSerialNumber()+"job",quartzVo.getAction()+quartzVo.getSerialNumber()+"group").build();
        }
        // 每6s执行一次
        if(quartzVo.getCronTime()!=null) {
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzVo.getCronTime());
            //配置cronTrigger
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(new TriggerKey(quartzVo.getAction() + quartzVo.getSerialNumber() + "trigger", quartzVo.getAction() + quartzVo.getSerialNumber() + "group"));
            if (cronTrigger == null) {

                cronTrigger = newTrigger().withIdentity(quartzVo.getAction() + quartzVo.getSerialNumber() + "trigger", quartzVo.getAction() + quartzVo.getSerialNumber() + "group").withSchedule(scheduleBuilder).build();
                scheduler.scheduleJob(jobDetail, cronTrigger);
                System.out.println("conTriger is null");
            } else {
                System.out.println("conTriger is not null");
                cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                        .withSchedule(scheduleBuilder).build();
                scheduler.rescheduleJob(cronTrigger.getKey(), cronTrigger);
            }
        }
        if(quartzVo.getTime()!=null){
            System.out.println(quartzVo.getTime());
            ////配置simpleTrigger
            SimpleTrigger simpleTrigger = (SimpleTrigger) scheduler.getTrigger(new TriggerKey(quartzVo.getAction() + quartzVo.getSerialNumber() + "simpleTrigger", quartzVo.getAction() + quartzVo.getSerialNumber() + "group"));
            if(simpleTrigger==null){
              simpleTrigger = (SimpleTrigger) newTrigger()
                        .withIdentity(quartzVo.getAction() + quartzVo.getSerialNumber() + "simpleTrigger", quartzVo.getAction() + quartzVo.getSerialNumber() + "group")
                        .startAt(futureDate(5, DateBuilder.IntervalUnit.SECOND)) // some Date// identify job with name, group strings
                        .build();
              if(jobDetail!=null){
                  scheduler.scheduleJob(jobDetail,simpleTrigger);
              }else {
                  scheduler.rescheduleJob(simpleTrigger.getKey(),simpleTrigger);
              }

            }else {
                simpleTrigger.getTriggerBuilder().withIdentity(quartzVo.getAction() + quartzVo.getSerialNumber() + "simpleTrigger", quartzVo.getAction() + quartzVo.getSerialNumber() + "group")
                        .startAt(futureDate(5, DateBuilder.IntervalUnit.SECOND))
                        .build();
                scheduler.rescheduleJob(simpleTrigger.getKey(),simpleTrigger);
            }


        }

    }
    private void scheduleJob2(Scheduler scheduler) throws SchedulerException{
        //配置定时任务对应的Job，这里执行的是ScheduledJob类中定时的方法
      //  JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class) .withIdentity("job1", "group1").build();
   //     JobDetail jobDetail = scheduler.getJobDetail(new JobKey("job1","group1"));
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/2 * * * * ?");
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(new TriggerKey("trigger1","group1"));
        cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(cronTrigger.getKey())
                .withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(cronTrigger.getKey(),cronTrigger);
    }



}
