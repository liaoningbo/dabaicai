package com.dabaicai.common.util;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.*;

/**
 * @author js on 2017/11/9.
 */
@Log4j2
public class ResultUtils {

    private static String hostName;
    static {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        }catch (Exception e) {
            hostName = "unknown";
        }
    }
    /**
     * bean转Map
     * @author lnb
     * @date created in 12:00 2019/5/24
     */
    public static <K, V> Map<K, V> beanToMap(Object obj) {
        Map<K, V> ret = new HashMap<>(8);
        if (obj == null){
            return null;
        }
        List<Field> fields = new ArrayList<>();
        List<Field> childFields;
        List<String> fieldsName = new ArrayList<>();
        Class tempClass = obj.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (tempClass != null) {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            //得到父类,然后赋给自己
            tempClass = tempClass.getSuperclass();
        }
        childFields = Arrays.asList(obj.getClass().getDeclaredFields());
        for (Field field : childFields) {
            fieldsName.add(field.getName());
        }
        try {
            for (Field field : fields) {
                try {
                    if (fieldsName.contains(field.getName())) {
                        Field f = obj.getClass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        Object o;
                        String type = field.getGenericType().getTypeName();
                        if(type.equals("double") || type.equals("java.lang.Double") || type.equals("java.math.BigDecimal")){
                            o = NumberUtil.formatDouble2(f.get(obj));
                        }else{
                            o = f.get(obj);
                        }
                        ret.put((K)field.getName(), (V)o);
                    } else {
                        Field f = obj.getClass().getSuperclass().getDeclaredField(field.getName());
                        f.setAccessible(true);
                        Object o;
                        String type = field.getGenericType().toString();
                        if(type.equals("double") || type.equals("java.lang.Double") || type.equals("java.math.BigDecimal")){
                            o = NumberUtil.formatDouble2(f.get(obj));
                        }else{
                            o = f.get(obj);
                        }
                        ret.put((K)field.getName(), (V)o);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 转bean的list转map的list
     * @author lnb
     * @date created in 11:31 2019/5/28
     */
    public static <K, V> List<Map<K, V>> beanListToMapList(List<?> list) throws Exception {
        List<Map<K, V>> resultList = new ArrayList<>();
        for(Object obj:list){
            resultList.add(beanToMap(obj));
        }
        return resultList;
    }

    /**
     * 获取对象属性
     *
     * @param fieldList
     * @param clazz
     * @return
     */
    public static void getField(List<String> nameList, Class clazz, List<Field> fieldList, List<String> noFieldNameList) {
        List<String> newNameList = new ArrayList<>();
        for (String fieldName : nameList) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                fieldList.add(field);
            } catch (NoSuchFieldException ex) {
                newNameList.add(fieldName);
            }
        }
        if (newNameList.size() == 0) {
            return;
        }
        Class supperClass = clazz.getSuperclass();
        if (supperClass == null && newNameList.size() > 0) {
            noFieldNameList.addAll(newNameList);
            return;
        }
        getField(newNameList, supperClass, fieldList, noFieldNameList);
    }
}
