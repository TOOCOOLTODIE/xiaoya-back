package com.xiaoya.domain.rate.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @descriptions: 书籍分类实体类
 * @author: luoxy
 * @date: 2021/5/18
 * @version: 1.0
 */
@Data
public class Sort implements Serializable {
    private int id;
    private int pid;
    private String name;
    private String source;
}
