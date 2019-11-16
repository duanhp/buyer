package com.duan.buyer.dao;

import com.duan.buyer.entity.UserRoleInfo;
import com.duan.buyer.entity.UserRoleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleInfoMapper {
    int countByExample(UserRoleInfoExample example);

    int deleteByExample(UserRoleInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    List<UserRoleInfo> selectByExample(UserRoleInfoExample example);

    UserRoleInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    int updateByExample(@Param("record") UserRoleInfo record, @Param("example") UserRoleInfoExample example);

    int updateByPrimaryKeySelective(UserRoleInfo record);

    int updateByPrimaryKey(UserRoleInfo record);
}