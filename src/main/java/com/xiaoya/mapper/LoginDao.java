package com.xiaoya.mapper;

import com.xiaoya.domain.login.entity.Login;
import com.xiaoya.domain.login.entity.LoginExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginDao {
    long countByExample(LoginExample example);

    int deleteByExample(LoginExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Login record);

    int insertSelective(Login record);

    List<Login> selectByExample(LoginExample example);

    Login selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Login record, @Param("example") LoginExample example);

    int updateByExample(@Param("record") Login record, @Param("example") LoginExample example);

    int updateByPrimaryKeySelective(Login record);

    int updateByPrimaryKey(Login record);

    Login getLoginByNameAndPassword(@Param("login") Login login);
}