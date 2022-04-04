package com.xiaoya.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * DemoJob
 *
 * @author luo
 * @date 2022/03/27 23:49
 **/
@Slf4j
public class DemoJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //这里边写你的任务所要干的任何事情
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        log.info("------任务名：" + jobDetail.getKey().getName() + ",组名：" +
                jobDetail.getKey().getGroup() + "------我是要执行的定时任务工作内容！");
    }
}