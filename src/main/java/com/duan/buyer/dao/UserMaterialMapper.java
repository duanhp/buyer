package com.duan.buyer.dao;

import com.duan.buyer.entity.UserMaterial;
import com.duan.buyer.entity.UserMaterialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMaterialMapper {
    int countByExample(UserMaterialExample example);

    int deleteByExample(UserMaterialExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMaterial record);

    int insertSelective(UserMaterial record);

    List<UserMaterial> selectByExample(UserMaterialExample example);

    UserMaterial selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMaterial record, @Param("example") UserMaterialExample example);

    int updateByExample(@Param("record") UserMaterial record, @Param("example") UserMaterialExample example);

    int updateByPrimaryKeySelective(UserMaterial record);

    int updateByPrimaryKey(UserMaterial record);
}