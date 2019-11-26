package com.dabaicai.common.head;

/**
 * 列相关工具类
 * @author lnb
 * @date created in 17:55 2019/5/27
 */
public class HeaderUtil {

    /**
     * 列位置：
     * column-right：居右保留两位小数，
     * column-right-long：居右，整数,
     * column-right-rate：百分比居右，
     * column-center：居中，
     * column-left：居左
     * column-date： 日期
     * column-date-time： 时间
     * key: 序号列
     */
    public static final String NUM = "column-right";
    public static final String LONG = "column-right-long";
    public static final String NUM_RATE = "column-right-rate";
    public static final String CENTER = "column-center";
    public static final String STR = "column-left";
    public static final String COLUMN_DATE = "column-date";
    public static final String COLUMN_DATE_TIME = "column-date-time";
    public static final String FIRST_COL = "key";

    /**
     * 冻结样式
     */
    public static final String FIXED_LEFT = "left";
}
