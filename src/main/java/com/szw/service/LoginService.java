package com.szw.service;

import com.szw.common.RespBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface LoginService {
    public RespBean login(String name,String password);
}
