package com.duan.buyer.dao;

import com.duan.buyer.entity.UserPurchaseOrder;
import com.duan.buyer.entity.UserPurchaseOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPurchaseOrderMapper {
    int countByExample(UserPurchaseOrderExample example);

    int deleteByExample(UserPurchaseOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserPurchaseOrder record);

    int insertSelective(UserPurchaseOrder record);

    List<UserPurchaseOrder> selectByExample(UserPurchaseOrderExample example);

    UserPurchaseOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserPurchaseOrder record, @Param("example") UserPurchaseOrderExample example);

    int updateByExample(@Param("record") UserPurchaseOrder record, @Param("example") UserPurchaseOrderExample example);

    int updateByPrimaryKeySelective(UserPurchaseOrder record);

    int updateByPrimaryKey(UserPurchaseOrder record);
}