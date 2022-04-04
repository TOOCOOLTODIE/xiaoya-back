package com.xiaoya.service;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.blog.entity.Blog;
import com.xiaoya.domain.blog.vo.BlogListVo;
import com.xiaoya.domain.blog.vo.BlogUpdateVo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Transactional
public interface BlogService {
    RespBean insertBlog(Blog blog);

    Map<String, Object> listBlog(BlogListVo reqParam);

    RespBean updateBlog(BlogUpdateVo blog);

    RespBean getBlogDetailById(@RequestParam Integer id);

    RespBean delBlogById(@RequestParam Integer id);
}
