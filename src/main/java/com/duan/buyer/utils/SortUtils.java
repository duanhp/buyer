package com.duan.buyer.utils;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class SortUtils {


    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<String, Object>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }


    public static void main(String[] args) {

        Map<String, Object> map = new TreeMap<String, Object>();

        map.put("alibaba", "alibaba");
        map.put("xiaomi", "xiaomi");
        map.put("geekthings", "geekthings");
        map.put("cainiao", "cainiao");

        Map<String, Object> resultMap = sortMapByKey(map);    //按Key进行排序

        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        String s = resultMap.toString();
        System.out.println(s);
        System.out.println(s.substring(1));
    }


}

class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}

