package com.dabaicai.common.response;

import com.dabaicai.common.head.Head;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 返回数据格式
 * @author: lnb
 * created in 2019/11/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    private List<Head> columns;
    private List dataSource;
    private Object sum;
    private Integer totalSize;
}
