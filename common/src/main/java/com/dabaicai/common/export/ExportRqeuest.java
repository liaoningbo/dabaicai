package com.dabaicai.common.export;

import com.dabaicai.common.response.ResponseData;
import lombok.Data;

/**
 * export导出请求参数
 * @author lnb
 * @date created in 17:19 2019/9/20
 */
@Data
public class ExportRqeuest extends BasicRequest {
    /**sheet名称*/
    String sheetName;
    /**第一行的title名称*/
    String excelTitle;
    ResponseData responseData;
}
