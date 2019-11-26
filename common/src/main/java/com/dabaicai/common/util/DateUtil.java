package com.dabaicai.common.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * @description: 时间格式转换 20161012 --> 2016/10/12
     * @author: lnb
     * @date: created in 15:27 2018/5/21
     * @version: 1.0.
     */
    public static String getFormatDate(String str){
        if(!StringUtil.notNull(str)){
            return "";
        }
        String year = "";
        if(str.length()>=4){
            year = str.substring(0,4);
        }
        String month = "";
        if(str.length()>=6){
            month = str.substring(4,6);
        }
        String day = "";
        if(str.length()>=8){
            day = str.substring(6,8);
        }
        if ("".equals(year) && "".equals(month) && "".equals(day)) {
            return "";
        }
        return year+"/"+month+"/"+day;
    }

    /**
     * 20160718121200 => 2016/07/18 12:12
     *
     * @param hllDT
     * @return
     */
    public static String getDigitalDateTimeFromHllDT(Long hllDT) {
        return String.format(
                "%04d/%02d/%02d %02d:%02d",
                hllDT / 10000000000L,
                hllDT % 10000000000L / 100000000,
                hllDT % 100000000 / 1000000,
                hllDT % 1000000 / 10000,
                hllDT % 10000 / 100
        );
    }

    public static Date StringToDate(String data, String dataFomat) throws Exception {
        if (StringUtils.isEmpty(data)) {
            throw new Exception("开始时间应该在结束时间之后");
        }
        //小写的mm表示的是分钟
        SimpleDateFormat sdf = new SimpleDateFormat(dataFomat);
        String dstr = data;
        Date date = sdf.parse(dstr);
        return date;
    }
}
