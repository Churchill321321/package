package com.lucheng.spring.cloud.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/18 17:43
 */
@Configuration
public class QuartzConfigration {
    //多任务时的Scheduler，动态设置Trigger。一个SchedulerFactoryBean可能会有多个Trigger
    @Bean(name = "multitaskScheduler")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        return schedulerFactoryBean;
    }
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //设置是否任意一个已定义的Job会覆盖现在的Job。默认为false，即已定义的Job不会覆盖现有的Job。
        bean.setOverwriteExistingJobs(true);
        return bean;
    }
}
