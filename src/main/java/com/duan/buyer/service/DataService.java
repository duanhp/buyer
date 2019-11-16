package com.duan.buyer.service;

import com.duan.buyer.entity.UserLogin;
import com.duan.buyer.entity.UserMaterial;
import com.duan.buyer.entity.UserPurchaseOrder;
import com.duan.buyer.enums.UserTypeEnum;
import com.duan.buyer.exception.BaseException;

import java.math.BigDecimal;
import java.util.List;

public interface DataService {
    public UserLogin userLogin(String userCode,String nickname, String companyName, UserTypeEnum type) throws BaseException;

    public UserLogin queryUserInfo(String userCode) throws BaseException;

    public List<UserMaterial> queryUserMaterial(String userCode, String orderDate);

    public List<UserMaterial> queryPurchaseMaterial(String purchaseCode,String companyCode, String orderDate);

    public void updateUserMaterial(String userCode, String orderDate, String materialName, String materialUnit, BigDecimal materialAmount, BigDecimal materialPrice);

    public void updateUserMaterial(String userCode, int orderId, String materialName, BigDecimal purchaseMaterialAmount, BigDecimal materialPrice);

    public List<UserMaterial> queryDefMaterial(String companyName);

    public void deleteUserMaterial(String userCode, String orderDate, String materialName);

    public void submitUserMaterialOrder(String userCode, String companyCode,String orderDate) throws BaseException;

    public void cancelUserMaterialOrder(String userCode, String companyCode,String orderDate) throws BaseException;

    public UserPurchaseOrder startUserMaterialOrder(String userCode, String companyCode,String orderDate) throws BaseException;

    public void submitPurchaseMaterialOrder(String purchaseCode, String companyCode,String orderDate) throws BaseException;

    public void cancelPurchaseMaterialOrder(String purchaseCode, String companyCode,String orderDate) throws BaseException;

    public UserPurchaseOrder startPurchaseMaterialOrder(String purchaseCode, String companyCode, String orderDate) throws BaseException;


    public void closeUserMaterialOrder(String userCode, String companyCode,String orderDate) throws BaseException;


    public UserPurchaseOrder queryPurchaseMaterialOrder(String purchaseCode,String companyCode);

    public UserPurchaseOrder queryMaterialOrder(String userCode,String companyCode);

    public UserPurchaseOrder queryMaterialOrder(String userCode,String orderDate,String companyCode);

    public List<UserPurchaseOrder> queryMaterialOrderList(String companyCode);

    public boolean pickMaterial(int orderId,String purchaseCode,String userCode);



}

