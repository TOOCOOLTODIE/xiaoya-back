package com.szw.controller;

import com.szw.common.RespBean;
import com.szw.entity.Blog;
import com.szw.mapper.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @类名 BlogMngController
 * @描述 说说处理类
 * @作者 luo
 * @日期 2020/8/16
 * @版本 1.0
 **/
@RestController
@RequestMapping("/blog")
public class BlogMngController {
    @Autowired
    BlogDao blogDao;

    /**
     * @作者 luoxy
     * @描述 //更新博客
     * @时间 2020/8/16 15:52
     * @参数 [blog]
     * @返回值 com.szw.common.RespBean
     **/
    @RequestMapping(value = "/insertBlog", method = RequestMethod.POST)
    public RespBean insertBlog(Blog blog) {
        //校验博客标题是否存在

        RespBean respBean = RespBean.build();
        blog.setUpdateTime(new Date());
        blog.setPublicTime(new Date());
        try {
            int insert = blogDao.insertSelectiveBlog(blog);
            respBean.setObj(null);
            respBean.setMsg("更新成功");
            respBean.setStatus(200);
            return respBean;
        } catch (Exception e) {
            respBean.setObj(null);
            respBean.setMsg("更新异常");
            respBean.setStatus(500);
            return respBean;
        }
    }

    /**
     * @作者 luoxy
     * @描述 //查询博客
     * @时间 2020/8/16 15:52
     * @参数 [userId, page, size, keywords]
     * @返回值 java.util.Map<java.lang.String   ,   java.lang.Object>
     **/
    @RequestMapping(value = "/listBlog", method = RequestMethod.GET)
    public Map<String, Object> listBlog(@RequestParam String userId,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        @RequestParam(defaultValue = "") String keywords) {
        Integer start = (page - 1) * size;
        Map<String, Object> param = new HashMap<>();
        param.put("start", start);
        param.put("size", size);
        param.put("keywords", keywords);
        param.put("userId", userId);
        List<Blog> blogs = new ArrayList<>();
        blogs = blogDao.loadBlogsByUserId(param);
        Long totalCount = blogDao.loadBlogsByUserIdCount(param);

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("blogs", blogs);
        retMap.put("totalCount", totalCount);
        retMap.put("status", 200);
        retMap.put("msg", "查询成功");
        return retMap;
    }

    @RequestMapping(value = "/updateBlog", method = RequestMethod.POST)
    public RespBean updateBlog(Blog blog) {
        RespBean respBean = RespBean.build();
        blog.setUpdateTime(new Date());
        blogDao.updateByPrimaryKeySelective(blog);
        respBean.setObj(null);
        respBean.setMsg("更新成功！");
        respBean.setStatus(200);
        return respBean;
    }

    /**
     * @作者 luoxy
     * @描述 //根据Blog的id获取详情
     * @时间 2020/8/28 12:07
     * @参数 [id]
     * @返回值 com.szw.common.RespBean
     **/
    @RequestMapping(value = "/listDetail", method = RequestMethod.GET)
    public RespBean listBlogDetailById(@RequestParam Integer id) {
        Blog blog = blogDao.selectByPrimaryKey(id);
        RespBean respBean = RespBean.build();
        respBean.setStatus(200);
        respBean.setObj(blog);
        respBean.setMsg("查询成功！");
        return respBean;
    }

    @RequestMapping(value = "/deleteOne", method = RequestMethod.DELETE)
    public RespBean deleteOne(@RequestParam Integer id) {
        blogDao.deleteByPrimaryKey(id);
        return RespBean.ok("SUCCESS");
    }

}
