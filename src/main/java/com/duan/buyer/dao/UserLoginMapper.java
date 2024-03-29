package com.duan.buyer.dao;

import com.duan.buyer.entity.UserLogin;
import com.duan.buyer.entity.UserLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLoginMapper {
    int countByExample(UserLoginExample example);

    int deleteByExample(UserLoginExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    List<UserLogin> selectByExample(UserLoginExample example);

    UserLogin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

    int updateByExample(@Param("record") UserLogin record, @Param("example") UserLoginExample example);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}