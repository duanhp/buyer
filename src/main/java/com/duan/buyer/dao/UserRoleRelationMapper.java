package com.duan.buyer.dao;

import com.duan.buyer.entity.UserRoleRelation;
import com.duan.buyer.entity.UserRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleRelationMapper {
    int countByExample(UserRoleRelationExample example);

    int deleteByExample(UserRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserRoleRelation record);

    int insertSelective(UserRoleRelation record);

    List<UserRoleRelation> selectByExample(UserRoleRelationExample example);

    UserRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByExample(@Param("record") UserRoleRelation record, @Param("example") UserRoleRelationExample example);

    int updateByPrimaryKeySelective(UserRoleRelation record);

    int updateByPrimaryKey(UserRoleRelation record);
}