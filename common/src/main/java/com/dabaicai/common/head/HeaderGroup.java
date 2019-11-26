package com.dabaicai.common.head;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * 多级表头
 * @author lnb
 * @date created in 18:11 2019/5/27
 */
@Getter
@Setter
public class HeaderGroup extends Head {
    private String title;
    private String index;
    private String key;
    private String fixed;
    private List<Head> children;

    private HeaderGroup(String title, String key, String fixed, List<Head> children) {
        this.title = title;
        this.key = key;
        this.children = children;
        this.fixed = fixed;
    }

    private HeaderGroup(String title, String key, List<Head> children) {
        this.title = title;
        this.key = key;
        this.children = children;
    }

    private HeaderGroup(String title, List<Head> children, String index) {
        this.title = title;
        this.index = index;
        this.children = children;
    }

    public HeaderGroup(String title, List<Head> children) {
        this.title = title;
        this.children = children;
    }

    public static HeaderGroup build(String title, Head...components){
        List<Head> list = Arrays.asList(components);
        return new HeaderGroup(title, title, list);
    }

    public static HeaderGroup build(String title){
        return new HeaderGroup(title, title, null);
    }

    public static HeaderGroup build(String title, String key, Head...components){
        List<Head> list = Arrays.asList(components);
        return new HeaderGroup(title, key, list);
    }

    public static HeaderGroup build(String title, List<Head> heads){
        return new HeaderGroup(title, title, heads);
    }

    public static HeaderGroup buildFixed(String title, List<Head> heads){
        return new HeaderGroup(title, title, "left", heads);
    }

    public static HeaderGroup build(String title, String key, List<Head> heads){
        return new HeaderGroup(title, key, heads);
    }

    public static HeaderGroup build(String title, List<Head> heads, String index){
        return new HeaderGroup(title, heads, index);
    }
}
