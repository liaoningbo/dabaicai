package com.dabaicai.common.export;

import lombok.Data;

import java.util.List;

/**
 * 多sheet导出请求参数
 * @author lnb
 * @date created in 17:24 2019/9/20
 */
@Data
public class ExportMoreSheetRequest extends BasicRequest {
    /**每一个sheet的信息*/
    private List<ExportRqeuest> exportRqeuestList;
}
