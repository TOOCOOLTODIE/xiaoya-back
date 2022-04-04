package com.xiaoya.domain.login.entity;

import lombok.*;

/**
 * 登录实体类
 * @author luo
 * @date 2022/04/03 01:58
 **/
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    private int id;
    private String name;
    private String password;
}
