package com.xiaoya.service.impl;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.blog.entity.Blog;
import com.xiaoya.domain.blog.vo.BlogListVo;
import com.xiaoya.domain.blog.vo.BlogUpdateVo;
import com.xiaoya.mapper.BlogDao;
import com.xiaoya.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 博客实现类
 * @author luo
 * @date 2022/04/04 14:41
 **/
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Override
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

    @Override
    public Map<String, Object> listBlog(BlogListVo req) {
        Integer start = (req.getPage() - 1) * req.getSize();
        Map<String, Object> param = new HashMap<>();
        param.put("start", start);
        param.put("size", req.getSize());
        param.put("keywords", req.getKeywords());
        param.put("userId", req.getUserId());
        List<Blog> blogs = blogDao.loadBlogsByUserId(param);
        int totalCount = blogDao.loadBlogsByUserIdCount(param);

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("blogs", blogs);
        retMap.put("totalCount", totalCount);
        retMap.put("status", 200);
        retMap.put("msg", "查询成功");
        return retMap;
    }

    @Override
    public RespBean updateBlog(BlogUpdateVo blog) {
        RespBean respBean = RespBean.build();
        blog.setUpdateTime(new Date());
        blogDao.updateByPrimaryKeySelective(blog);
        respBean.setObj(null);
        respBean.setMsg("更新成功！");
        respBean.setStatus(200);
        return respBean;
    }

    @Override
    public RespBean getBlogDetailById(Integer id) {
        com.xiaoya.domain.blog.entity.Blog blog = blogDao.getBlogDetailById(id);
        RespBean respBean = RespBean.build();
        respBean.setStatus(200);
        respBean.setObj(blog);
        respBean.setMsg("查询成功！");
        return respBean;
    }

    @Override
    public RespBean delBlogById(Integer id) {
        blogDao.deleteByPrimaryKey(id);
        return RespBean.ok("success");
    }
}
