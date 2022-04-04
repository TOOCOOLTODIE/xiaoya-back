package com.xiaoya.domain.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取分页博客列表前端参数封装
 *
 * @author luo
 * @date 2022/04/04 14:53
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogListVo {
    private String userId;
    private Integer page;
    private Integer size;
    private String keywords;
}
