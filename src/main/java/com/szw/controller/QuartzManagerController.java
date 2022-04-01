package com.szw.controller;

import com.github.pagehelper.PageInfo;
import com.szw.common.RespBean;
import com.szw.entity.quartz.GetAllByPaginationAndKeywordVO;
import com.szw.entity.quartz.JobDetails;
import com.szw.entity.quartz.QuartzManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定时任务管理控制器类
 *
 * @author luo
 * @date 2022/03/27 17:28
 **/
@RestController
@RequestMapping(value = "/job")
public class QuartzManagerController {
    @Autowired
    private QuartzManager qtzManager;

    @SuppressWarnings("unchecked")
    private static Class<? extends QuartzJobBean> getClass(String name) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(name);
        return (Class<? extends QuartzJobBean>) aClass;
    }

    @PostMapping("/test")
    public String test(@RequestBody GetAllByPaginationAndKeywordVO req) {
        return "hello";
    }

    /**
     * 新增或者更新job
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @return void
     * @author luo
     * @date 2022/3/27 22:24
     */
    @PostMapping(value = "/addJob")
    public void addJob(@RequestParam(value = "jobClassName") String jobClassName,
                       @RequestParam(value = "jobGroupName") String jobGroupName,
                       @RequestParam(value = "cronExpression") String cronExpression) throws ClassNotFoundException {
        qtzManager.addOrUpdateJob(getClass(jobClassName), jobClassName, jobGroupName, cronExpression);
    }

    /**
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/pausejob")
    public void pausejob(@RequestParam(value = "jobClassName") String jobClassName,
                         @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        qtzManager.pauseJob(jobClassName, jobGroupName);
    }

    /**
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/resumejob")
    public void resumejob(@RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        qtzManager.resumeJob(jobClassName, jobGroupName);
    }

    /**
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob(@RequestParam(value = "jobClassName") String jobClassName,
                              @RequestParam(value = "jobGroupName") String jobGroupName,
                              @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        qtzManager.addOrUpdateJob(getClass(jobClassName), jobClassName, jobGroupName, cronExpression);
    }

    /**
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    @PostMapping(value = "/deletejob")
    public void deletejob(@RequestParam(value = "jobClassName") String jobClassName,
                          @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        qtzManager.deleteJob(jobClassName, jobGroupName);
    }

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value = "pageNum") Integer pageNum,
                                        @RequestParam(value = "pageSize") Integer pageSize) {
        PageInfo<JobDetails> jobAndTrigger = qtzManager.queryAllJobBean(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }

    @PostMapping(value = "/getAllByPaginationAndKeyword")
    public RespBean getAllByPaginationAndKeyWord(GetAllByPaginationAndKeywordVO vo) {
        System.out.println(vo);
        return RespBean.ok("SUCCESS");

    }
}
