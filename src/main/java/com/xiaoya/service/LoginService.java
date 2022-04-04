package com.xiaoya.service;

import com.xiaoya.common.RespBean;
import com.xiaoya.domain.login.entity.Login;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface LoginService {
    RespBean login(Login login);
}
