package com.lucheng.spring.cloud.weather.JsonResult;

import java.util.Date;

/**
 * @auther lucheng@qq.com
 * @date 2018/7/19 11:20
 */
public class QuartzVo {
    private String cronTime;

    private Date time;

    private String action;

    private String serialNumber;

    public String getCronTime() {
        return cronTime;
    }

    public void setCronTime(String cronTime) {
        this.cronTime = cronTime;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
