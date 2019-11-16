package com.duan.buyer.utils;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;

public class ToolUtils {


    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)
            throws Exception {
        if (map == null)
            return null;
        Object obj = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        return obj;
    }

    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static String makeUrlPathParam(Map<String, Object> map) {

        String result = "";
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            result = result + "&" + entry.getKey() + "=" + entry.getValue();
        }
        if (result != null && !"".equals(result)) {
            return result.substring(1);
        }
        return result;
    }


    public static String randomString() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {

        System.out.println(randomString());

    }



}
