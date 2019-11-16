package com.duan.buyer.dao;

import com.duan.buyer.entity.Datadict;
import com.duan.buyer.entity.DatadictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatadictMapper {
    int countByExample(DatadictExample example);

    int deleteByExample(DatadictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Datadict record);

    int insertSelective(Datadict record);

    List<Datadict> selectByExample(DatadictExample example);

    Datadict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Datadict record, @Param("example") DatadictExample example);

    int updateByExample(@Param("record") Datadict record, @Param("example") DatadictExample example);

    int updateByPrimaryKeySelective(Datadict record);

    int updateByPrimaryKey(Datadict record);
}