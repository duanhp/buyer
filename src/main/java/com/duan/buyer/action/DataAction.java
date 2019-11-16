package com.duan.buyer.action;


import com.duan.buyer.comm.CommReq;
import com.duan.buyer.comm.CommResp;
import com.duan.buyer.entity.UserLogin;
import com.duan.buyer.entity.UserMaterial;
import com.duan.buyer.entity.UserPurchaseOrder;
import com.duan.buyer.enums.ErrorCodeEnum;
import com.duan.buyer.enums.UserTypeEnum;
import com.duan.buyer.service.DataService;
import com.duan.buyer.service.WxService;
import com.duan.buyer.wxdef.WxLoginReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class DataAction {

   private static Logger log=LoggerFactory.getLogger(DataAction.class);


   @Autowired
   private DataService dataService;


   @Autowired
   private WxService wxService;


    @RequestMapping("/login")
    @ResponseBody
    public CommResp login(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            UserLogin userLogin=null;

            String userCode=(String)paramMap.get("userCode");

            if(userCode!=null&&!userCode.isEmpty()) {
                userLogin = dataService.queryUserInfo(userCode);
            }else {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            if (userLogin == null) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
                resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
                log.error(resp.toString());
                return resp;
            }
            List<UserLogin> userLogins=new ArrayList<>();
            userLogins.add(userLogin);
            resp.setResult(userLogins);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/requestOpenId")
    @ResponseBody
    public CommResp requestOpenId(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            UserLogin userLogin=null;

            String code=(String)paramMap.get("code");
            WxLoginReturn wxLoginReturn=wxService.wxLogin(code);
            List<WxLoginReturn> ls=new ArrayList<>();
            ls.add(wxLoginReturn);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }

    @RequestMapping("/reg")
    @ResponseBody
    public CommResp reg(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            UserLogin userLogin = dataService.userLogin((String) paramMap.get("userCode"),
                    (String) paramMap.get("nickname"),
                    (String) paramMap.get("companyName"),
                    UserTypeEnum.findTypeCode((String) paramMap.get("typeCode")));

            if (userLogin == null) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
                resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
                log.error(resp.toString());
                return resp;
            }
            List<UserLogin> userLogins=new ArrayList<>();
            userLogins.add(userLogin);
            resp.setResult(userLogins);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/userinfo")
    @ResponseBody
    public CommResp getUserInfo(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);

            String userCode=(String) paramMap.get("userCode");

            UserLogin userLogin = dataService.queryUserInfo(userCode);

            if (userLogin == null) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
                resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
                log.error(resp.toString());
                return resp;
            }
            List<UserLogin> userLogins=new ArrayList<>();
            userLogins.add(userLogin);
            resp.setResult(userLogins);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/list")
    @ResponseBody
    public CommResp materialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);

            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyName=(String) paramMap.get("companyName");

            List<UserMaterial> userMaterials=null;
            if(companyName!=null&&!orderDate.isEmpty()) {
                UserPurchaseOrder userPurchaseOrder = dataService.queryMaterialOrder(userCode, companyName);
                orderDate=userPurchaseOrder.getPurchaseDate();
            }
                userMaterials = dataService.queryUserMaterial(userCode, orderDate);

                if (userMaterials == null || userMaterials.size() <= 0) {
                    if (companyName != null && !companyName.isEmpty()) {
                        userMaterials = dataService.queryDefMaterial(companyName);
                    }
                }

            resp.setResult(userMaterials);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/start")
    @ResponseBody
    public CommResp startMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            orderDate=Long.toString(System.currentTimeMillis());


            UserPurchaseOrder userPurchaseOrder=dataService.startUserMaterialOrder(userCode,companyCode,orderDate);

            List<UserPurchaseOrder> ls=new ArrayList<>();
            ls.add(userPurchaseOrder);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/submit")
    @ResponseBody
    public CommResp submitMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            dataService.submitUserMaterialOrder(userCode,companyCode,orderDate);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/cancel")
    @ResponseBody
    public CommResp cancleMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            dataService.cancelUserMaterialOrder(userCode,companyCode,orderDate);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/close")
    @ResponseBody
    public CommResp closeMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            dataService.closeUserMaterialOrder(userCode,companyCode,orderDate);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }



    @RequestMapping("/material/purchase/order")
    @ResponseBody
    public CommResp queryPurchaseMaterialOrder(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String purchaseCode=(String) paramMap.get("purchaseCode");
            String companyCode=(String) paramMap.get("companyCode");

            UserPurchaseOrder userPurchaseOrder=dataService.queryPurchaseMaterialOrder(purchaseCode,companyCode);

            List<UserPurchaseOrder> ls=new ArrayList<>();
            ls.add(userPurchaseOrder);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }



    @RequestMapping("/material/purchase/start")
    @ResponseBody
    public CommResp startPurchaseMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String purchaseCode=(String) paramMap.get("purchaseCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            UserPurchaseOrder userPurchaseOrder=dataService.startPurchaseMaterialOrder(purchaseCode,companyCode,orderDate);

            List<UserPurchaseOrder> ls=new ArrayList<>();
            ls.add(userPurchaseOrder);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/purchase/submit")
    @ResponseBody
    public CommResp submitPurchaseMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String purchaseCode=(String) paramMap.get("purchaseCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            dataService.submitPurchaseMaterialOrder(purchaseCode,companyCode,orderDate);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/purchase/cancel")
    @ResponseBody
    public CommResp canclePurchaseMaterialList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }
            Map<String, Object> paramMap = paramList.get(0);
            String purchaseCode=(String) paramMap.get("purchaseCode");
            String orderDate=(String) paramMap.get("orderDate");
            String companyCode=(String) paramMap.get("companyCode");

            dataService.cancelPurchaseMaterialOrder(purchaseCode,companyCode,orderDate);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }




    @RequestMapping("/material/save")
    @ResponseBody
    public CommResp saveMaterial(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);

            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String materialName=(String) paramMap.get("materialName");
            String materialUnit=(String) paramMap.get("materialUnit");
            String sMaterialAmount=(String) paramMap.get("materialAmount");
            BigDecimal materialAmount=new BigDecimal(sMaterialAmount);
            dataService.updateUserMaterial(userCode,orderDate,materialName,materialUnit,materialAmount,null);
            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/purchase/save")
    @ResponseBody
    public CommResp savePurchaseMaterial(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);

            String userCode=(String) paramMap.get("userCode");
            String sOrderId=(String) paramMap.get("orderId");
            Integer orderId=Integer.parseInt(sOrderId);
            String materialName=(String) paramMap.get("materialName");
            String sMaterialAmount=(String) paramMap.get("materialAmount");
            String sMaterialPrice=(String) paramMap.get("materialPrice");

            BigDecimal materialAmount=new BigDecimal(sMaterialAmount);
            BigDecimal materialPrice=new BigDecimal(sMaterialPrice);
            dataService.updateUserMaterial(userCode,orderId,materialName,materialAmount,materialPrice);
            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/delete")
    @ResponseBody
    public CommResp deleteMaterial(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);

            String userCode=(String) paramMap.get("userCode");
            String orderDate=(String) paramMap.get("orderDate");
            String materialName=(String) paramMap.get("materialName");
            dataService.deleteUserMaterial(userCode,orderDate,materialName);

            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/order")
    @ResponseBody
    public CommResp queryMaterialOrder(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String companyCode=(String) paramMap.get("companyCode");
            UserPurchaseOrder userPurchaseOrder=dataService.queryMaterialOrder(userCode,companyCode);
            List<UserPurchaseOrder> ls=new ArrayList<>();
            ls.add(userPurchaseOrder);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/orderlist")
    @ResponseBody
    public CommResp queryMaterialOrderList(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            String companyCode=(String) paramMap.get("companyCode");
            List<UserPurchaseOrder> ls=dataService.queryMaterialOrderList(companyCode);
            resp.setResult(ls);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }


    @RequestMapping("/material/pick")
    @ResponseBody
    public CommResp pickMaterial(@RequestBody CommReq commReq){
        CommResp resp=new CommResp();
        try {
            log.info(commReq.toString());

            List<Map<String, Object>> paramList = commReq.getParams();
            if (paramList == null || paramList.size() <= 0) {
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.PARAMS_ERROR.getCode());
                resp.setErrorMessage(ErrorCodeEnum.PARAMS_ERROR.getMessage());
                log.error(resp.toString());
                return resp;
            }

            Map<String, Object> paramMap = paramList.get(0);
            String userCode=(String) paramMap.get("userCode");
            String orderId=(String)paramMap.get("orderId");
            String purchaseCode=(String) paramMap.get("purchaseCode");
            boolean isRet=dataService.pickMaterial(Integer.parseInt(orderId),purchaseCode,userCode);
            if(isRet==false){
                resp.setSuccess(false);
                resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
                resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            }
            resp.setResult(null);
        }catch (Exception e){
            resp.setSuccess(false);
            resp.setErrorCode(ErrorCodeEnum.FAILURE.getCode());
            resp.setErrorMessage(ErrorCodeEnum.FAILURE.getMessage());
            log.error(e.getMessage(),e);
        }
        return resp;
    }












}
