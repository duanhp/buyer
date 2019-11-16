package com.duan.buyer.service.Impl;

import com.duan.buyer.dao.*;
import com.duan.buyer.entity.*;
import com.duan.buyer.enums.ErrorCodeEnum;
import com.duan.buyer.enums.OrderStatusEnum;
import com.duan.buyer.enums.UserTypeEnum;
import com.duan.buyer.exception.BaseException;
import com.duan.buyer.service.DataService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("dataService")
public class DataServiceImpl implements DataService {



    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Autowired
    private UserMaterialMapper userMaterialMapper;

    @Autowired
    private MaterialInfoMapper materialInfoMapper;

    @Autowired
    private UserPurchaseOrderMapper userPurchaseOrderMapper;


    private CompanyInfo queryCompanyByCode(String companyCode){
        CompanyInfoExample example=new CompanyInfoExample();
        CompanyInfoExample.Criteria criteria=example.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        List<CompanyInfo> companyInfos=companyInfoMapper.selectByExample(example);
        if(companyInfos==null||companyInfos.size()<=0) return null;
        return companyInfos.get(0);
    }

    private CompanyInfo queryCompanyByName(String companyName){
        CompanyInfoExample example=new CompanyInfoExample();
        CompanyInfoExample.Criteria criteria=example.createCriteria();
        criteria.andCompanyNameEqualTo(companyName);
        List<CompanyInfo> companyInfos=companyInfoMapper.selectByExample(example);
        if(companyInfos==null||companyInfos.size()<=0) return null;
        return companyInfos.get(0);
    }

    private CompanyInfo updateCompany(String companyName){
        CompanyInfo companyInfo=queryCompanyByName(companyName);
        if(companyInfo==null){
            companyInfo=new CompanyInfo();
            companyInfo.setCompanyName(companyName);
            companyInfo.setCompanyCode(UUID.randomUUID().toString());
            companyInfo.setGmtCreate(new Date());
            companyInfo.setGmtModify(new Date());
            int iret=companyInfoMapper.insertSelective(companyInfo);
            if(iret<=0) return null;
        }
        return companyInfo;
    }

    private UserLogin queryUserLogin(String userCode){
        UserLoginExample example=new UserLoginExample();
        UserLoginExample.Criteria criteria=example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        List<UserLogin> userloginLs=userLoginMapper.selectByExample(example);
        if(userloginLs==null||userloginLs.size()<=0) return null;
        return userloginLs.get(0);
    }

    @Override
    public UserLogin userLogin(String userCode,String nickname, String companyName, UserTypeEnum type) throws BaseException {
        UserLoginExample example=new UserLoginExample();
        UserLoginExample.Criteria criteria=example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        List<UserLogin> userloginLs=userLoginMapper.selectByExample(example);

        //判断已经存在的用户
        if(userloginLs!=null&&userloginLs.size()>0){
            if(companyName==null||companyName.isEmpty()){
                UserLogin userLogin=userloginLs.get(0);
                CompanyInfo companyInfo=queryCompanyByCode(userLogin.getCompanyCode());
                if(companyInfo!=null)
                    userLogin.setCompanyName(companyInfo.getCompanyName());
                return userloginLs.get(0);
            }
            UserLogin temp=null;
            boolean isFind=false;
            for(int i=0;i<userloginLs.size();i++){
                temp=userloginLs.get(i);
                if(temp==null) continue;
                CompanyInfo companyInfo=queryCompanyByCode(temp.getCompanyCode());
                if(companyInfo==null) continue;
                if(companyName.equals(companyInfo.getCompanyName())){
                    isFind=true;
                    temp.setCompanyName(companyInfo.getCompanyName());
                    return temp;
                }
            }
        }else{
            if(companyName==null||companyName.isEmpty()){
                return null;
            }
        }


        if(UserTypeEnum.MASTER.equals(type)){
            CompanyInfo companyInfo=updateCompany(companyName);
            UserLogin userLogin=new UserLogin();
            userLogin.setCompanyCode(companyInfo.getCompanyCode());
            userLogin.setGmtCreate(new Date());
            userLogin.setGmtLogin(new Date());
            userLogin.setRoleName(UserTypeEnum.MASTER.getTypeCode());
            userLogin.setUserCode(userCode);
            userLogin.setUserNickname(nickname);
            userLogin.setCompanyName(companyName);
            int iret=userLoginMapper.insert(userLogin);
            if(iret>0) return userLogin;
            return null;

        }else if(UserTypeEnum.PURCHASE.equals(type)){
            CompanyInfo companyInfo=queryCompanyByName(companyName);
            if(companyInfo==null){
                throw new BaseException(ErrorCodeEnum.FAILURE.getCode(),"填写公司或单位错误");
            }
            UserLogin userLogin=new UserLogin();
            userLogin.setCompanyCode(companyInfo.getCompanyCode());
            userLogin.setGmtCreate(new Date());
            userLogin.setGmtLogin(new Date());
            userLogin.setRoleName(UserTypeEnum.PURCHASE.getTypeCode());
            userLogin.setUserCode(userCode);
            userLogin.setUserNickname(nickname);
            userLogin.setCompanyName(companyName);
            int iret=userLoginMapper.insert(userLogin);
            if(iret>0) return userLogin;
            return null;
        }else{
            return null;
        }
    }

    @Override
    public List<UserMaterial> queryUserMaterial(String userCode,String orderDate) {
        UserLogin userLogin=queryUserLogin(userCode);
        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andPurchaseDateEqualTo(orderDate);
        if(userLogin.getRoleName().equals(UserTypeEnum.MASTER.getTypeCode())){
            criteria.andUserCodeEqualTo(userCode);
        }else if(userLogin.getRoleName().equals(UserTypeEnum.MASTER.getTypeCode())){
            criteria.andUserCodeEqualTo(userCode);
        }else{
            return null;
        }
        List<UserMaterial> userMaterials=userMaterialMapper.selectByExample(example);
        return userMaterials;
    }


    public UserMaterial queryUserMaterialOne(String userCode, String orderDate,String materialName) {
        UserLogin userLogin=queryUserLogin(userCode);
        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andPurchaseDateEqualTo(orderDate);
        criteria.andUserCodeEqualTo(userCode);
        criteria.andMaterialNameEqualTo(materialName);
        List<UserMaterial> userMaterials=userMaterialMapper.selectByExample(example);
        if(userMaterials==null||userMaterials.size()<=0) return null;
        return userMaterials.get(0);
    }

    @Override
    public void updateUserMaterial(String userCode, String orderDate, String materialName, String materialUnit, BigDecimal materialAmount,BigDecimal materialPrice) {

        UserMaterial userMaterialTemp=queryUserMaterialOne(userCode,orderDate,materialName);
        if(userMaterialTemp==null){
            UserMaterial userMaterial=new UserMaterial();
            userMaterial.setMaterialAmount(materialAmount);
            userMaterial.setGmtModify(new Date());
            userMaterial.setGmtCreate(new Date());
            userMaterial.setMaterialName(materialName);
            userMaterial.setMaterialRealAmount(null);
            userMaterial.setMaterialUnit(materialUnit);
            userMaterial.setPurchaseDate(orderDate);
            userMaterial.setUserCode(userCode);
            userMaterial.setMaterialPrice(materialPrice);

            userMaterialMapper.insert(userMaterial);
            return;
        }

        UserMaterial userMaterial=new UserMaterial();
        userMaterial.setGmtModify(new Date());
        userMaterial.setMaterialAmount(materialAmount);
        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andPurchaseDateEqualTo(orderDate);
        criteria.andUserCodeEqualTo(userCode);
        criteria.andMaterialNameEqualTo(materialName);
        userMaterialMapper.updateByExampleSelective(userMaterial,example);
    }

    @Override
    public void updateUserMaterial(String userCode, int orderId, String materialName, BigDecimal purchaseMaterialAmount,BigDecimal materialPrice) {
        UserMaterial userMaterial=new UserMaterial();
        userMaterial.setGmtModify(new Date());
        userMaterial.setMaterialRealAmount(purchaseMaterialAmount);
        userMaterial.setMaterialPrice(materialPrice);

        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(Long.parseLong(Integer.toString(orderId)));
        criteria.andUserCodeEqualTo(userCode);
        criteria.andMaterialNameEqualTo(materialName);
        userMaterialMapper.updateByExampleSelective(userMaterial,example);
    }

    @Override
    public List<UserMaterial> queryDefMaterial(String companyName) {


        CompanyInfo companyInfo=queryCompanyByName(companyName);
        if(companyInfo==null) return null;

        MaterialInfoExample example=new MaterialInfoExample();
        MaterialInfoExample.Criteria criteria=example.createCriteria();
        criteria.andCompanyCodeEqualTo(companyInfo.getCompanyCode());
        List<MaterialInfo> materialInfos=materialInfoMapper.selectByExample(example);
        if(materialInfos==null||materialInfos.size()<=0) return null;

        List<UserMaterial> userMaterials=new ArrayList<>();
        MaterialInfo materialInfo=null;
        for(int i=0;i<materialInfos.size();i++){
            materialInfo=materialInfos.get(i);
            if(materialInfo==null) continue;
            UserMaterial userMaterial=new UserMaterial();
            userMaterial.setMaterialName(materialInfo.getMaterialName());
            userMaterial.setMaterialUnit(materialInfo.getMaterialUnit());
            userMaterials.add(userMaterial);
        }

        return userMaterials;
    }


    @Override
    public void deleteUserMaterial(String userCode, String orderDate, String materialName) {
        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andUserCodeEqualTo(userCode);
        criteria.andPurchaseDateEqualTo(orderDate);
        criteria.andMaterialNameEqualTo(materialName);
        userMaterialMapper.deleteByExample(example);
    }

    private UserPurchaseOrder queryUserPurchaseOrderByPurchaseCode(String purchaseCode,String companyCode, String orderDate,int status){
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        if(purchaseCode!=null)
          criteria.andPurchaseCodeEqualTo(purchaseCode);
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andPurchaseDateEqualTo(orderDate);
        criteria.andStatusEqualTo(status);
        List<UserPurchaseOrder> userPurchaseOrders=userPurchaseOrderMapper.selectByExample(example);
        if(userPurchaseOrders==null||userPurchaseOrders.size()<=0) return null;
        return userPurchaseOrders.get(0);
    }


    private UserPurchaseOrder queryUserPurchaseOrder(String userCode,String companyCode, String orderDate,Integer status){
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andShopOwnerCodeEqualTo(userCode);
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andPurchaseDateEqualTo(orderDate);
        if(status!=null)
            criteria.andStatusEqualTo(status);
        List<UserPurchaseOrder> userPurchaseOrders=userPurchaseOrderMapper.selectByExample(example);
        if(userPurchaseOrders==null||userPurchaseOrders.size()<=0) return null;
        return userPurchaseOrders.get(0);
    }

    private UserPurchaseOrder queryUserPurchaseOrder(String userCode,String companyCode){
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andShopOwnerCodeEqualTo(userCode);
        if(companyCode!=null)
            criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andStatusNotEqualTo(OrderStatusEnum.CANCELED.getCode());
        List<UserPurchaseOrder> userPurchaseOrders=userPurchaseOrderMapper.selectByExample(example);
        if(userPurchaseOrders==null||userPurchaseOrders.size()<=0) return null;
        return userPurchaseOrders.get(0);
    }

    private UserPurchaseOrder queryUserPurchaseOrder(String companyCode){
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andStatusGreaterThanOrEqualTo(OrderStatusEnum.DEFINATED.getCode());
        criteria.andStatusLessThan(OrderStatusEnum.CANCELED.getCode());
        List<UserPurchaseOrder> userPurchaseOrders=userPurchaseOrderMapper.selectByExample(example);
        if(userPurchaseOrders==null||userPurchaseOrders.size()<=0) return null;
        return userPurchaseOrders.get(0);
    }


    private void updateUserPurchaseOrder(UserPurchaseOrder userPurchaseOrder,String purchaseCode,int status) throws BaseException{
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(userPurchaseOrder.getId());
        criteria.andStatusEqualTo(userPurchaseOrder.getStatus());

        UserPurchaseOrder record=new UserPurchaseOrder();
        record.setStatus(status);
        record.setPurchaseCode(purchaseCode);
        record.setGmtModify(new Date());
        int iret=userPurchaseOrderMapper.updateByExampleSelective(record,example);
        if(iret<=0){
            throw new BaseException(ErrorCodeEnum.FAILURE.getCode(),"update error");
        }
    }


    private void updateUserPurchaseOrder(Long id,int originStatus,int status) throws BaseException{
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        criteria.andStatusEqualTo(originStatus);

        UserPurchaseOrder record=new UserPurchaseOrder();
        record.setStatus(status);
        record.setGmtModify(new Date());
        int iret=userPurchaseOrderMapper.updateByExampleSelective(record,example);
        if(iret<=0){
            throw new BaseException(ErrorCodeEnum.FAILURE.getCode(),"update error");
        }
    }


    @Override
    public List<UserMaterial> queryPurchaseMaterial(String purchaseCode,String companyCode, String orderDate) {
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrderByPurchaseCode(purchaseCode,companyCode,orderDate, OrderStatusEnum.DEFINATED.getCode());
        List<UserMaterial> ls=queryUserMaterial(userPurchaseOrder.getShopOwnerCode(),userPurchaseOrder.getPurchaseDate());
        return ls;
    }


    @Override
    public void submitUserMaterialOrder(String userCode, String companyCode, String orderDate) throws BaseException {
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrder(userCode,companyCode,orderDate, OrderStatusEnum.NOT_DEFINATED.getCode());
        if(userPurchaseOrder==null) {
            throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder.getId(),userPurchaseOrder.getStatus(),OrderStatusEnum.DEFINATED.getCode());
    }

    @Override
    public void cancelUserMaterialOrder(String userCode, String companyCode, String orderDate) throws BaseException{
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrder(userCode,companyCode,orderDate, OrderStatusEnum.DEFINATED.getCode());
        if(userPurchaseOrder==null){
            throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder.getId(),userPurchaseOrder.getStatus(),OrderStatusEnum.NOT_DEFINATED.getCode());
    }

    @Override
    public void submitPurchaseMaterialOrder(String purchaseCode, String companyCode, String orderDate) throws BaseException{
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrderByPurchaseCode(purchaseCode,companyCode,orderDate, OrderStatusEnum.NOT_PURCHASED.getCode());
        if(userPurchaseOrder==null){
            throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder.getId(),userPurchaseOrder.getStatus(),OrderStatusEnum.PURCHASED.getCode());
    }

    @Override
    public void cancelPurchaseMaterialOrder(String purchaseCode, String companyCode, String orderDate) throws BaseException{
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrderByPurchaseCode(purchaseCode,companyCode,orderDate, OrderStatusEnum.PURCHASED.getCode());
        if(userPurchaseOrder==null){
            throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder.getId(),userPurchaseOrder.getStatus(),OrderStatusEnum.NOT_PURCHASED.getCode());
    }


    @Override
    public UserPurchaseOrder startPurchaseMaterialOrder(String purchaseCode, String companyCode, String orderDate) throws BaseException {
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrderByPurchaseCode(null,companyCode,orderDate, OrderStatusEnum.DEFINATED.getCode());
        if(userPurchaseOrder==null){
             throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder,purchaseCode,OrderStatusEnum.NOT_PURCHASED.getCode());
        userPurchaseOrder.setStatus(OrderStatusEnum.NOT_PURCHASED.getCode());
        userPurchaseOrder.setPurchaseCode(purchaseCode);
        return userPurchaseOrder;
    }

    @Override
    public UserPurchaseOrder startUserMaterialOrder(String userCode, String companyCode, String orderDate) throws BaseException{

        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrder(userCode,companyCode,orderDate, null);
        if(userPurchaseOrder!=null){
            return userPurchaseOrder;
        }

        UserPurchaseOrder record=new UserPurchaseOrder();
        record.setStatus(OrderStatusEnum.NOT_DEFINATED.getCode());
        record.setGmtModify(new Date());
        record.setCompanyCode(companyCode);
        record.setPurchaseDate(orderDate);
        record.setShopOwnerCode(userCode);
        record.setGmtCreate(new Date());
        int iret=userPurchaseOrderMapper.insert(record);
        if(iret<=0){
            throw new BaseException(ErrorCodeEnum.FAILURE.getCode(),"insert error");
        }
        return record;
    }


    @Override
    public void closeUserMaterialOrder(String userCode, String companyCode, String orderDate) throws BaseException{
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrder(userCode,companyCode,orderDate,null);
        if(userPurchaseOrder==null){
            throw new BaseException(ErrorCodeEnum.OPT_INVALID.getCode(),ErrorCodeEnum.OPT_INVALID.getMessage());
        }
        updateUserPurchaseOrder(userPurchaseOrder.getId(),userPurchaseOrder.getStatus(),OrderStatusEnum.CANCELED.getCode());
    }


    @Override
    public UserPurchaseOrder queryPurchaseMaterialOrder(String purchaseCode, String companyCode) {
        UserPurchaseOrder userPurchaseOrder=queryUserPurchaseOrder(companyCode);
        return userPurchaseOrder;
    }


    @Override
    public UserPurchaseOrder queryMaterialOrder(String userCode, String companyCode) {
        return queryUserPurchaseOrder(userCode,companyCode);
    }

    @Override
    public UserPurchaseOrder queryMaterialOrder(String userCode, String orderDate, String companyCode) {
        return null;
    }

    @Override
    public UserLogin queryUserInfo(String userCode) throws BaseException {
        UserLogin userLogin=queryUserLogin(userCode);
        CompanyInfo companyInfo=queryCompanyByCode(userLogin.getCompanyCode());
        if(companyInfo!=null&&userLogin!=null){
            userLogin.setCompanyName(companyInfo.getCompanyName());
        }
        return userLogin;
    }

    @Override
    public List<UserPurchaseOrder> queryMaterialOrderList(String companyCode) {
        UserPurchaseOrderExample example=new UserPurchaseOrderExample();
        UserPurchaseOrderExample.Criteria criteria=example.createCriteria();
        criteria.andCompanyCodeEqualTo(companyCode);
        criteria.andStatusNotEqualTo(OrderStatusEnum.CANCELED.getCode());
        List<UserPurchaseOrder> userPurchaseOrders=userPurchaseOrderMapper.selectByExample(example);
        if(userPurchaseOrders==null||userPurchaseOrders.size()<=0) return null;
        return userPurchaseOrders;
    }


    @Override
    public boolean pickMaterial(int orderId, String purchaseCode, String userCode) {

        UserMaterial userMaterial=new UserMaterial();
        userMaterial.setGmtModify(new Date());
        userMaterial.setPurchaseCode(purchaseCode);

        UserMaterialExample example=new UserMaterialExample();
        UserMaterialExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(Long.parseLong(Integer.toString(orderId)));
        criteria.andUserCodeEqualTo(userCode);
        int iret=userMaterialMapper.updateByExampleSelective(userMaterial,example);
        return iret>0;
    }
}
