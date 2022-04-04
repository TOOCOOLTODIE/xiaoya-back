package com.xiaoya.service.impl;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.login.entity.Login;
import com.xiaoya.mapper.LoginDao;
import com.xiaoya.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类名 LoginServiceImpl
 * @描述 登录控制类
 * @作者 xiaoya
 * @日期 2020/7/4
 * @版本 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao loginDao;

    @Override
    public RespBean login(Login login){
        RespBean respBean = RespBean.build();
        Login user = loginDao.getLoginByNameAndPassword(login);
        if(user != null){
            respBean.setMsg("登录成功！");
            respBean.setStatus(200);
            respBean.setObj(user);
            return respBean;
        }else{
            respBean.setMsg("账户名或者密码错误！");
            respBean.setStatus(401);
            return respBean;
        }
    }
}
