package com.xiaoya.controller;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.login.entity.Login;
import com.xiaoya.domain.login.vo.LoginVo;
import com.xiaoya.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器类
 * @author luo
 * @date 2022/04/03 01:46
 **/
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public RespBean login(@RequestBody LoginVo loginVo){
        Login login = Login.builder()
                .name(loginVo.getName())
                .password(loginVo.getPassword())
                .build();
        return loginService.login(login);
    }
}
