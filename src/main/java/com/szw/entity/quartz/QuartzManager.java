package com.szw.entity.quartz;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * job管理类
 *
 * @author luo
 * @date 2022/03/27 19:55
 **/
@Component
@Slf4j
public class QuartzManager {

    @Autowired
    private Scheduler sched;

    /**
     * 创建或者更新任务，存在则更新，不存在新建
     *
     * @param jobClass     任务类
     * @param jobName      任务名称
     * @param jobGroupName 任务组名称
     * @param jobCron      cron表达式
     * @return void
     * @author luo
     * @date 2022/3/27 21:35
     */
    public void addOrUpdateJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobCron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        try {
            CronTrigger cronTrigger = (CronTrigger) sched.getTrigger(triggerKey);
            if (cronTrigger == null) {
                addJob(jobClass, jobName, jobGroupName, jobCron);
            } else {
                updateJob(jobClass, jobGroupName, jobCron);
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager addOrUpdateJob occurred SchedulerException:param:jobClass:{}," +
                    "jobName:{}" +
                    "jobGroupName:{}" +
                    "jobCron:{}" +
                    "Exception:{}", jobClass, jobName, jobGroupName, jobCron, e.getMessage());
        }

    }

    /**
     * 更新job
     *
     * @param jobClass     任务实现类
     * @param jobGroupName 任务组名称
     * @param jobCron      任务表达式
     * @return void
     * @author luo
     * @date 2022/3/27 21:14
     */
    private void updateJob(Class<? extends QuartzJobBean> jobClass, String jobGroupName, String jobCron) {


    }

    /**
     * 新增job
     *
     * @param jobClass     任务实现类
     * @param jobName      任务名
     * @param jobGroupName 任务组名称
     * @param jobCron      任务表达式
     * @return void
     * @author luo
     * @date 2022/3/27 21:31
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobCron) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobCron)).startNow().build();
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager addJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 新增job
     *
     * @param jobClass
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     * @return void
     * @author luo
     * @date 2022/3/27 21:06
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, int jobTime) {
        addJob(jobClass, jobName, jobGroupName, jobTime, -1);
    }

    /**
     * 新增job
     *
     * @param jobClass
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     * @param jobTimes
     * @return void
     * @author luo
     * @date 2022/3/27 21:18
     */
    public void addJob(Class<? extends Job> jobClass, String jobName, String jobGroupName, int jobTime, int jobTimes) {
        try {
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(jobName, jobGroupName)
                    .build();
            // 使用simpleTrigger规则
            Trigger trigger;

            if (jobTimes < 0) {
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime))
                        .startNow().build();
            } else {
                trigger = TriggerBuilder
                        .newTrigger().withIdentity(jobName, jobGroupName)
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(jobTime).withRepeatCount(jobTimes))
                        .startNow().build();
            }
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager addJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 更新任务
     *
     * @param jobName      任务名
     * @param jobGroupName 任务组名
     * @param jobTime      任务时间
     * @return void
     * @author luo
     * @date 2022/3/27 21:41
     */
    public void updateJob(String jobName, String jobGroupName, String jobTime) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
            // 重启触发器
            sched.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("QuartzManager updateJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 删除一个job任务
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名称
     * @return void
     * @author luo
     * @date 2022/3/27 21:44
     */
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            sched.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
            sched.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
            sched.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("QuartzManager deleteJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 暂停一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名称
     * @return void
     * @author luo
     * @date 2022/3/27 21:01
     */
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
            sched.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("QuartzManager pauseJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 恢复一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名称
     * @return void
     * @author luo
     * @date 2022/3/27 21:34
     */
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            sched.resumeJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("QuartzManager resumeJob occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 立即执行一个job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务组名称
     * @return void
     * @author luo
     * @date 2022/3/27 21:33
     */
    public void runAJobNow(String jobName, String jobGroupName) {
        try {
            sched.triggerJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            log.error("QuartzManager runAJobNow occurred SchedulerException:{}", e.getMessage());
        }
    }

    /**
     * 查询所有job bean
     *
     * @param pageNum
     * @param pageSize
     * @return com.github.pagehelper.PageInfo<com.szw.entity.quartz.JobDetails>
     * @author luo
     * @date 2022/3/27 22:03
     */
    public PageInfo<JobDetails> queryAllJobBean(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobDetails> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyGroup();
            Set<JobKey> jobKeys = sched.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = sched.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    JobDetails jobDetails = new JobDetails();
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        jobDetails.setCornExpression(cronTrigger.getCronExpression());
                        jobDetails.setTimeZone(cronTrigger.getTimeZone().getDisplayName());
                    }
                    jobDetails.setTriggerGroupName(trigger.getKey().getName());
                    jobDetails.setTriggerName(trigger.getKey().getGroup());
                    jobDetails.setJobGroupName(jobKey.getName());
                    jobDetails.setJobName(jobKey.getName());
                    jobDetails.setStartTime(trigger.getStartTime());
                    jobDetails.setNextFireTime(String.valueOf(trigger.getNextFireTime()));
                    jobDetails.setJobClassName(sched.getJobDetail(jobKey).getJobClass().getName());
                    jobDetails.setPreviousFireTime(trigger.getPreviousFireTime());
                    jobDetails.setStatus(sched.getTriggerState(trigger.getKey()).name());
                    jobList.add(jobDetails);
                }
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager queryAllJobBean occurred SchedulerException:{}", e.getMessage());
        }
        return new PageInfo<>(jobList);
    }

    /**
     *
     * 获取所有计划中的任务列表
     * @author luo
     * @date 2022/3/27 22:19
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = sched.getJobKeys(matcher);
            jobList = new ArrayList<>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = sched.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    setJobInfoMap(jobList, map, jobKey, trigger);
                }
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager queryAllJob occurred SchedulerException:{}", e.getMessage());
        }
        return jobList;
    }

    /**
     *
     * 获取所有正在运行的job
     * @author luo
     * @date 2022/3/27 22:31
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    public  List<Map<String,Object>> queryRunJob() {
        List<Map<String,Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = sched.getCurrentlyExecutingJobs();
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                setJobInfoMap(jobList, map, jobKey, trigger);
            }
        } catch (SchedulerException e) {
            log.error("QuartzManager queryRunJob occurred SchedulerException:{}", e.getMessage());
        }
        return jobList;
    }

    private void setJobInfoMap(List<Map<String, Object>> jobList, Map<String, Object> map, JobKey jobKey, Trigger trigger) throws SchedulerException {
        map.put("jobName", jobKey.getName());
        map.put("jobGroupName", jobKey.getGroup());
        map.put("description", "trigger:" + trigger.getKey());
        Trigger.TriggerState triggerState = sched.getTriggerState(trigger.getKey());
        map.put("jobStatus", triggerState.name());
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String cronExpression = cronTrigger.getCronExpression();
            map.put("jobTime", cronExpression);
        }
        jobList.add(map);
    }
}
