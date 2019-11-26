package com.dabaicai.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 集合相关工具类
 * @author lnb
 * @date created in 11:22 2019/5/28
 */
public class CollectionUtil {

    /**
     * 判断集合不为空
     * @author lnb
     * @date created in 11:24 2019/5/28
     */
    public static boolean notNull(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断集合为空
     * @author lnb
     * @date created in 11:24 2019/5/28
     */
    public static boolean isNull(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * map不为空
     * @author lnb
     * @date created in 11:24 2019/5/28
     */
    public static boolean notNull(Map map) {
        if (map == null || map.size() == 0) {
            return false;
        }
        return true;
    }

    /**
     * map为空
     * @author lnb
     * @date created in 11:24 2019/5/28
     */
    public static boolean isNull(Map map) {
        if (map == null || map.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断map中是否存在key
     * @author lnb
     * @date created in 11:25 2019/5/28
     */
    public static boolean keyIsExist(Map map, Object obj){
        if(map != null && map.size()>0 && map.containsKey(obj)){
            return true;
        }else{
            return false;
        }
    }
}
