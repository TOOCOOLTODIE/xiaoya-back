package com.xiaoya.domain.blog.vo;

import com.xiaoya.domain.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新增博客前端参数封装
 * @author luo
 * @date 2022/04/04 15:18
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogInsertVo {
    private String title;
    private String content;
    private String tags;
    private int userId;
}
