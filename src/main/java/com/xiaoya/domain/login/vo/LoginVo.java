package com.xiaoya.domain.login.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 登录请求参数封装实体类
 * @author luo
 * @date 2022/04/03 01:53
 **/
@Data
public class LoginVo {
    /**
     * 登录名
     */
    @NotEmpty
    private String name;
    /**
     * 登录密码
     */
    @NotEmpty
    private String password;
}
