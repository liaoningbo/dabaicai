package com.dabaicai.common.util;

/**
 * 字符串相关工具类
 * @author lnb
 * created in 2019/11/26
 */
public class StringUtil {
    /**
     * 字符串不为空
     * @author lnb
     * @date created in 15:10 2019/5/28
     */
    public static boolean notNull(String str){
        if("".equals(str) || str == null ){
            return false;
        }
        return true;
    }

    /**
     * 字符串为空
     * @author lnb
     * @date created in 15:10 2019/5/28
     */
    public static boolean isNull(String str){
        if("".equals(str) || str == null ){
            return true;
        }
        return false;
    }

    /**
     * 判断两个字符串是否相等
     * @author lnb
     * @date created in 15:40 2019/7/16
     */
    public static boolean equals(String str1, String str2){
        if(str1 == null || str2 == null){
            return false;
        }else{
            if(str1.equals(str2)){
                return true;
            }else{
                return false;
            }
        }
    }
}
