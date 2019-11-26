package com.dabaicai.common.util;

import java.math.BigDecimal;

/**
 * 数字计算工具类
 * @author lnb
 * @date created in 14:59 2019/5/28
 */
public class NumberUtil {

    private static final int DEF_DIV_SCALE = 8;

    /**
     * 保留两位小数，得到double类型
     * @author lnb
     * @date created in 15:01 2019/5/28
     */
    public static double formatDouble(Object obj) {
        if (obj == null) {
            return 0.00D;
        }
        try {
            BigDecimal bd = new BigDecimal(obj.toString());
            return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } catch (NumberFormatException e) {
            return 0.00D;
        }
    }
    
    /**
     * 保留两位小数，得到String类型
     * @author lnb
     * @date created in 15:00 2019/5/28
     */
    public static String formatDouble2(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            BigDecimal bd = new BigDecimal(obj.toString());
            return bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } catch (NumberFormatException e) {
            return "0.00";
        }
    }

    /**
     * 两数相除，保留两位小数
     * @author lnb
     * @date created in 15:01 2019/5/28
     */
    public static double div(Object v1, Object v2) {
        return div(v1, v2, 2);
    }

    /**
     * 两处相除，得到n为小数
     * @author lnb
     * @date created in 15:02 2019/5/28
     */
    public static double div(Object v1, Object v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1.toString());
        BigDecimal b2 = new BigDecimal(v2.toString());
        if (b2.doubleValue() == 0) {
            return 0.00;
        }
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 数字转换为百分比
     * @author lnb
     * @date created in 15:02 2019/5/28 
     */
    public static String formatBaifen(Double obj) {
        if (obj == null) {
            return null;
        }
        obj = obj * 100;
        BigDecimal bd = new BigDecimal(obj.toString());

        return bd.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%";
    }

    /**
     * 两数相除，得到百分比
     * @author lnb
     * @date created in 15:02 2019/5/28
     */
    public static String formatBaifen(Double obj1, Double obj2) {
        return formatBaifen(div(obj1, obj2));
    }

    /**
     * 百分比转小数
     * @author lnb
     * @date created in 15:03 2019/5/28
     */
    public static Double baifenToDouble(String str){
        String newStr = str.replace("%", "");
        Double d = Double.parseDouble(newStr);
        return div(d, 100D);
    }

    /**
     * 求差
     * @author lnb
     * @date created in 15:03 2019/5/28
     */
    public static Double sub(Double obj1, Double obj2){
        if(obj1 == null){
            if(obj2 == null){
                return 0D;
            }else{
                return obj2;
            }
        }else{
            if(obj2 == null){
                return obj1;
            }else{
                BigDecimal p1 = new BigDecimal(Double.toString(obj1));
                BigDecimal p2 = new BigDecimal(Double.toString(obj2));
                return p1.subtract(p2).doubleValue();
            }
        }
    }

    /**
     * 求和
     * @author lnb
     * @date created in 15:03 2019/5/28
     */
    public static Double add(Double obj1, Double obj2){
        if(obj1 == null){
            if(obj2 == null){
                return 0D;
            }else{
                return obj2;
            }
        }else{
            if(obj2 == null){
                return obj1;
            }else{
                BigDecimal p1 = new BigDecimal(formatDouble2(obj1));
                BigDecimal p2 = new BigDecimal(formatDouble2(obj2));
                return p1.add(p2).doubleValue();
            }
        }
    }

    /**
     * 求积
     * @author lnb
     * @date created in 15:04 2019/5/28
     */
    public static double mul(Double obj1, Double obj2) {
        if(obj1 == null || obj2 == null){
            return 0D;
        }else{
            BigDecimal p1 = new BigDecimal(Double.toString(obj1));
            BigDecimal p2 = new BigDecimal(Double.toString(obj2));
            return p1.multiply(p2).doubleValue();
        }
    }
}
