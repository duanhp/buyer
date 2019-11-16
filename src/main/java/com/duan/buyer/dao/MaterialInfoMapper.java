package com.duan.buyer.dao;

import com.duan.buyer.entity.MaterialInfo;
import com.duan.buyer.entity.MaterialInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialInfoMapper {
    int countByExample(MaterialInfoExample example);

    int deleteByExample(MaterialInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialInfo record);

    int insertSelective(MaterialInfo record);

    List<MaterialInfo> selectByExample(MaterialInfoExample example);

    MaterialInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialInfo record, @Param("example") MaterialInfoExample example);

    int updateByExample(@Param("record") MaterialInfo record, @Param("example") MaterialInfoExample example);

    int updateByPrimaryKeySelective(MaterialInfo record);

    int updateByPrimaryKey(MaterialInfo record);
}