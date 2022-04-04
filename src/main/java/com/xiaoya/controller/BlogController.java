package com.xiaoya.controller;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.blog.entity.Blog;
import com.xiaoya.domain.blog.vo.BlogInsertVo;
import com.xiaoya.domain.blog.vo.BlogListVo;
import com.xiaoya.domain.blog.vo.BlogUpdateVo;
import com.xiaoya.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Blob;
import java.util.Map;


/**
 *
 * 博客控制器类
 * @author luo
 * @date 2022/4/4 15:22
 */
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;


    /**
     * 新增博客
     *
     * @param req 前端参数
     * @return com.xiaoya.common.RespBean
     * @author luo
     * @date 2022/4/4 15:48
     */
    @RequestMapping(value = "/insertBlog", method = RequestMethod.POST)
    public RespBean insertBlog(@RequestBody BlogInsertVo req) {
        Blog blog = Blog.builder()
                .id(null)
                .title(req.getTitle())
                .tags(req.getTags())
                .content(req.getContent())
                .userId(req.getUserId())
                .build();
        return blogService.insertBlog(blog);
    }


    /**
     * 获取博客列表
     *
     * @param req 前端参数
     * @return java.util.Map<java.lang.String   ,   java.lang.Object>
     * @author luo
     * @date 2022/4/4 15:59
     */
    @RequestMapping(value = "/listBlog", method = RequestMethod.POST)
    public Map<String, Object> listBlog(@RequestBody BlogListVo req) {
        return blogService.listBlog(req);
    }

    /**
     * 更新博客内容
     *
     * @param req 前端参数
     * @return com.xiaoya.common.RespBean
     * @author luo
     * @date 2022/4/4 15:16
     */
    @RequestMapping(value = "/updateBlog", method = RequestMethod.POST)
    public RespBean updateBlog(@RequestBody BlogUpdateVo req) {
        return blogService.updateBlog(req);
    }

    /**
     * 获取单个博客详情
     *
     * @param id 博客ID
     * @return com.xiaoya.common.RespBean
     * @author luo
     * @date 2022/4/4 15:38
     */
    @RequestMapping(value = "/getBlogInfoById", method = RequestMethod.GET)
    public RespBean getBlogDetailById(@RequestParam Integer id) {
        return blogService.getBlogDetailById(id);
    }

    /**
     * 删除一条博客
     *
     * @param id 博客ID
     * @return com.xiaoya.common.RespBean
     * @author luo
     * @date 2022/4/4 15:20
     */
    @RequestMapping(value = "/delBlogById", method = RequestMethod.DELETE)
    public RespBean delBlogById(@RequestParam Integer id) {
        return blogService.delBlogById(id);
    }

}
