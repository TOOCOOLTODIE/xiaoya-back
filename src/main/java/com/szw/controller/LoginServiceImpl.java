package com.szw.controller;

import com.szw.common.RespBean;
import com.szw.entity.User;
import com.szw.mapper.UserDao;
import com.szw.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类名 LoginServiceImpl
 * @描述 登录控制类
 * @作者 szw
 * @日期 2020/7/4
 * @版本 1.0
 **/
@RestController
public class LoginServiceImpl implements LoginService{

    @Autowired
    UserDao userDao;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public RespBean login(@RequestParam String name,@RequestParam String password) {
        RespBean respBean = RespBean.build();

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        User user1 = userDao.selectUserExist(user);
        if(user1 != null){
            respBean.setMsg("登录成功！");
            respBean.setStatus(200);
            respBean.setObj(user1);
            return respBean;
        }else{
            respBean.setMsg("账户名或者密码错误！");
            respBean.setStatus(401);
            respBean.setObj(user1);
            return respBean;
        }
    }


}
