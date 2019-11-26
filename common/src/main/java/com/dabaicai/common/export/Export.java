package com.dabaicai.common.export;

import com.dabaicai.common.response.ResponseData;
import com.dabaicai.common.util.StringUtil;
import io.netty.util.internal.ThrowableUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 导出
 * @author lnb
 * @date created in 18:38 2019/9/20
 */
@Log4j2
@Component
public class Export {
    @Autowired
    private ExportUtil exportUtil;

    /**
     * 多个sheet的导出
     * @author lnb
     * @date created in 17:41 2019/9/20
     */
    public void exportMoreSheet(ExportMoreSheetRequest exportMoreSheetRequest, HttpServletResponse httpResponse) throws Exception {
        long t1 = System.currentTimeMillis();

        //获取导出excel名称
        String fileName = exportMoreSheetRequest.getFileName();
        //获取每个sheet的请求参数
        List<ExportRqeuest> exportRqeuestList = exportMoreSheetRequest.getExportRqeuestList();
        //new一个workBook
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);

        //遍历sheet，处理每一个sheet的数据
        int size = exportRqeuestList.size();
        ServletOutputStream outputStream = null;
        try {
            for(int i = 0; i < size; i++){
                //获取sheet的请求参数
                ExportRqeuest exportRqeuest = exportRqeuestList.get(i);
                String sheetName = exportRqeuest.getSheetName();
                if(StringUtil.isNull(sheetName)){
                    sheetName = fileName;
                }
                //可以单独设置，没有单独设置的话和sheetName一致
                String excelTitle = exportRqeuest.getExcelTitle();
                if(StringUtil.isNull(excelTitle)){
                    excelTitle = exportRqeuest.getSheetName();
                }
                ResponseData responseData = exportRqeuest.getResponseData();
                //写excel
                exportUtil.fillExcelMoreSheet(sxssfWorkbook, sheetName, excelTitle, i, responseData);
            }

            //设置头
            setResponseHeader(httpResponse, fileName);
            //导出
            outputStream = httpResponse.getOutputStream();
            sxssfWorkbook.write(outputStream);
        } catch (Exception e) {
            String stackTrace = ThrowableUtil.stackTraceToString(e);
            log.error("ExportUtil.exportMoreSheet error :", e);
            log.error(stackTrace);
            throw new Exception(e);
        } finally {
            StreamTools.closeStream(outputStream);
        }

        long t2 = System.currentTimeMillis();
        log.info("ExportUtil.exportMoreSheet：耗时：{}ms", t2 - t1);
    }

    /**
     * 一个sheet的导出
     * @author lnb
     * @date created in 17:16 2019/9/20
     */
    public void export(ExportRqeuest exportRqeuest, HttpServletResponse httpResponse) throws Exception {
        long t1 = System.currentTimeMillis();

        //获取导出请求参数
        String fileName = exportRqeuest.getFileName();
        String sheetName = exportRqeuest.getSheetName();
        if(StringUtil.isNull(sheetName)){
            sheetName = fileName;
        }

        ServletOutputStream outputStream = null;
        try {
            //写excel
            Workbook workbook = exportUtil.fillExcel(fileName, sheetName, exportRqeuest.getResponseData());
            //设置头
            setResponseHeader(httpResponse, fileName);
            //导出
            outputStream = httpResponse.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            String stackTrace = ThrowableUtil.stackTraceToString(e);
            log.error("ExportUtil.export error :", e);
            log.error(stackTrace);
            throw new Exception(e);
        } finally {
            StreamTools.closeStream(outputStream);
        }

        long t2 = System.currentTimeMillis();
        log.info("ExportUtil.export：耗时：{}ms", t2 - t1);
    }

    /**
     * 设置导出头部
     *
     * @param httpResponse
     */
    public static void setResponseHeader(HttpServletResponse httpResponse, String excelName) throws Exception {
        //输出EXCEL
        excelName = excelName + ".xlsx";
        String downLoadName = new String(excelName.getBytes("gbk"), "iso8859-1");
        httpResponse.addHeader("Content-Type", "text/xlsx; charset=utf-8");
        httpResponse.setHeader("Content-Disposition", "attachment;fileName=" + downLoadName);
    }
}
