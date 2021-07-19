package com.szw.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * blog
 * @author 
 */
@Data
public class Blog implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 博客的标题
     */
    private String title;
    /**
     * 博客内容
     */
    private String content;
    /**
     * 博客的标签
     */
    private String tags;
    /**
     * 发表人
     */
    private Integer userId;
    /**
     * 发表时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date publicTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;
}