package com.xiaoya.domain.quartz.vo;

import lombok.Data;

/**
 * 通过分页关键词获取定时任务请求参数对象
 *
 * @author luo
 * @date 2022/03/27 17:34
 **/
@Data
public class QuartzQueryAllVo {
    private String page;
    private String size;
    private String keyword;
    private String userId;
}
