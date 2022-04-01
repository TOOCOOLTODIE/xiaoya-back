package com.szw.entity.quartz;

import lombok.Data;

import java.util.Date;

/**
 * job实体类
 * @author luo
 * @date 2022/03/27 20:14
 **/
@Data
public class JobDetails {
    private String cornExpression;
    private String jobClassName;
    private String triggerGroupName;
    private String triggerName;
    private String jobGroupName;
    private String jobName;
    private String nextFireTime;
    private String timeZone;
    private String status;
    private Date startTime;
    private Date previousFireTime;
}
