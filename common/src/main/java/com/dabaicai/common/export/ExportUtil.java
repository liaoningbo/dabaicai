package com.dabaicai.common.export;

import com.dabaicai.common.head.Head;
import com.dabaicai.common.head.HeaderCell;
import com.dabaicai.common.head.HeaderGroup;
import com.dabaicai.common.head.HeaderUtil;
import com.dabaicai.common.response.ResponseData;
import com.dabaicai.common.util.CollectionUtil;
import com.dabaicai.common.util.NumberUtil;
import com.dabaicai.common.util.ResultUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导出
 * @author lnb
 * @date created in 11:12 2019/9/10
 */
@Component
@Log4j2
public class ExportUtil {

    /**
     * 导出方法
     * @param fileName  文件名
     * @param sheetName  sheet名称
     */
    public Workbook fillExcel(String fileName, String sheetName, ResponseData responseData) throws Exception {

        List data = responseData.getDataSource();

        long t1 = System.currentTimeMillis();
        //导出EXCEL
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        Sheet sheet = sxssfWorkbook.createSheet(sheetName);
        int size = (data == null ? 0 : data.size());
        fillExcelData(fileName, sheet, responseData, sxssfWorkbook);
        long t2 = System.currentTimeMillis();
        log.info("ExportUtil.fillExcel：数据量:{}，耗时：{}ms", size, t2 - t1);
        return sxssfWorkbook;
    }

    /**
     * 多sheet导出
     * @author lnb
     * @date created in 17:12 2019/9/20
     */
    public void fillExcelMoreSheet(SXSSFWorkbook sxssfWorkbook, String sheetName, String excelTitle, int sheetNum, ResponseData responseData) throws Exception {
        long t1 = System.currentTimeMillis();

        List data = responseData.getDataSource();

        //导出EXCEL
        Sheet sheet = sxssfWorkbook.createSheet();
        sxssfWorkbook.setSheetName(sheetNum, sheetName);
        int size = (data == null ? 0 : data.size());
        fillExcelData(excelTitle, sheet, responseData, sxssfWorkbook);
        long t2 = System.currentTimeMillis();
        log.info("ExportUtil.fillExcelMoreSheet：数据量:{}，耗时：{}ms", size, t2 - t1);
    }

    /**
     * 获取单元格格式
     * @author lnb
     * @date created in 12:12 2019/10/22
     */
    public Map<String, XSSFCellStyle> getCellStyle(Workbook workbook){
        Long t1 = System.currentTimeMillis();
        Map<String, XSSFCellStyle> map = new HashMap<>();

        XSSFDataFormat format = (XSSFDataFormat)workbook.createDataFormat();

        /****奇数行样式***/
        //NUM
        XSSFCellStyle cellStyle1_NUM = getDataStyle1(workbook);
        cellStyle1_NUM.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle1_NUM.setDataFormat(format.getFormat("0.00"));
        map.put("cellStyle1_NUM", cellStyle1_NUM);

        //LONG
        XSSFCellStyle cellStyle1_LONG = getDataStyle1(workbook);
        cellStyle1_LONG.setDataFormat(format.getFormat("0"));
        map.put("cellStyle1_LONG", cellStyle1_LONG);

        //NUM_RATE
        XSSFCellStyle cellStyle1_NUM_RATE = getDataStyle1(workbook);
        cellStyle1_NUM_RATE.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle1_NUM_RATE.setDataFormat(format.getFormat("0.00%"));
        map.put("cellStyle1_NUM_RATE", cellStyle1_NUM_RATE);

        //CENTER
        XSSFCellStyle cellStyle1_CENTER = getDataStyle1(workbook);
        cellStyle1_CENTER.setDataFormat(format.getFormat("@"));
        cellStyle1_CENTER.setAlignment(HorizontalAlignment.CENTER);
        map.put("cellStyle1_CENTER", cellStyle1_CENTER);

        //STR
        XSSFCellStyle cellStyle1_STR = getDataStyle1(workbook);
        cellStyle1_STR.setDataFormat(format.getFormat("@"));
        map.put("cellStyle1_STR", cellStyle1_STR);

        //COLUMN_DATE
        XSSFCellStyle cellStyle1_COLUMN_DATE = getDataStyle1(workbook);
        cellStyle1_COLUMN_DATE.setDataFormat(format.getFormat("yyyy/m/d"));
        map.put("cellStyle1_COLUMN_DATE", cellStyle1_COLUMN_DATE);

        //COLUMN_DATE_TIME
        XSSFCellStyle cellStyle1_COLUMN_DATE_TIME = getDataStyle1(workbook);
        cellStyle1_COLUMN_DATE_TIME.setDataFormat(format.getFormat("yyyy/m/d h:mm"));
        map.put("cellStyle1_COLUMN_DATE_TIME", cellStyle1_COLUMN_DATE_TIME);

        /****偶数行样式***/
        //NUM
        XSSFCellStyle cellStyle2_NUM = getDataStyle2(workbook);
        cellStyle2_NUM.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle2_NUM.setDataFormat(format.getFormat("0.00"));
        map.put("cellStyle2_NUM", cellStyle2_NUM);

        //LONG
        XSSFCellStyle cellStyle2_LONG = getDataStyle2(workbook);
        cellStyle2_LONG.setDataFormat(format.getFormat("0"));
        map.put("cellStyle2_LONG", cellStyle2_LONG);

        //NUM_RATE
        XSSFCellStyle cellStyle2_NUM_RATE = getDataStyle2(workbook);
        cellStyle2_NUM_RATE.setAlignment(HorizontalAlignment.RIGHT);
        cellStyle2_NUM_RATE.setDataFormat(format.getFormat("0.00%"));
        map.put("cellStyle2_NUM_RATE", cellStyle2_NUM_RATE);

        //CENTER
        XSSFCellStyle cellStyle2_CENTER = getDataStyle2(workbook);
        cellStyle2_CENTER.setDataFormat(format.getFormat("@"));
        cellStyle2_CENTER.setAlignment(HorizontalAlignment.CENTER);
        map.put("cellStyle2_CENTER", cellStyle2_CENTER);

        //STR
        XSSFCellStyle cellStyle2_STR = getDataStyle2(workbook);
        cellStyle2_STR.setDataFormat(format.getFormat("@"));
        map.put("cellStyle2_STR", cellStyle2_STR);

        //COLUMN_DATE
        XSSFCellStyle cellStyle2_COLUMN_DATE = getDataStyle2(workbook);
        cellStyle2_COLUMN_DATE.setDataFormat(format.getFormat("yyyy/m/d"));
        map.put("cellStyle2_COLUMN_DATE", cellStyle2_COLUMN_DATE);

        //COLUMN_DATE_TIME
        XSSFCellStyle cellStyle2_COLUMN_DATE_TIME = getDataStyle2(workbook);
        cellStyle2_COLUMN_DATE_TIME.setDataFormat(format.getFormat("yyyy/m/d h:mm"));
        map.put("cellStyle2_COLUMN_DATE_TIME", cellStyle2_COLUMN_DATE_TIME);

        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.getCellStyle 创建所有单元格格式耗时：{}ms", t2 - t1);
        return map;
    }

    /**
     * 导出方法
     */
    public void fillExcelData(String excelTitle, Sheet sheet, ResponseData responseData, Workbook workbook) throws Exception {
        List<Head> columns = responseData.getColumns();
        List list = responseData.getDataSource();
        Object sumObj = responseData.getSum();

        //数据list中的对象如果是Map，直接使用，不是Map的话，需要转换成Map
        List<Map<String, Object>> data = new ArrayList<>();
        if(list.get(0) instanceof HashMap || list.get(0) instanceof LinkedHashMap){
            data.addAll(list);
        }else{
            list.forEach(bean -> {
                Map<String, Object> map = ResultUtils.beanToMap(bean);
                data.add(map);
            });
        }

        //合计如果是map，直接使用，如果不是，需要转成map
        Map<String, Object> sum;
        if(sumObj instanceof HashMap || sumObj instanceof LinkedHashMap){
            sum = (Map<String, Object>)sumObj;
        }else{
            sum = ResultUtils.beanToMap(sumObj);
        }

        //获取所有的列
        List<HeaderCell> cellList = new ArrayList<>();
        getHeaderCellList(cellList, columns);

        Map<String, XSSFCellStyle> cellStyleMap = getCellStyle(workbook);

        //获取表头中的最大行数
        Integer count = getTitleCount(1, columns);
        //大标题
        writeBigTitle(sheet, cellList, excelTitle, workbook);
        //查询条件行
        writeQueryConditionRow(sheet, cellList, workbook);
        //写表头
        writeHeader(columns, sheet, workbook, cellList, count);
        int size = data.size();
        //写数据
        writeData(data, sheet, count, cellList, cellStyleMap);
        data.clear();
        //写合计
        writeSum(cellList, sum, sheet, size, count, cellStyleMap);
    }

    /**
     * 写合计
     * @author lnb
     * @date created in 11:56 2019/9/10
     */
    private void writeSum(
            List<HeaderCell> cellList, Map<String, Object> sum, Sheet sheet, int size, Integer count, Map<String, XSSFCellStyle> cellStyleMap) throws Exception {
        Long t1 = System.currentTimeMillis();
        //遍历数据集合
        if(CollectionUtil.notNull(sum)){

            sum.put("key", "合计");

            Row rowLine = sheet.createRow(size + 2 + count);
            int row = 0;
            for(HeaderCell headerCell : cellList){
                Cell cell = rowLine.createCell(row);
                setCellValue(cell, headerCell, sum, cellStyleMap, 0);
                row++;
            }
        }
        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.writeSum 耗时：{}ms", t2 - t1);
    }

    /**
     * 写查询条件行
     * @author lnb
     * @date created in 11:19 2019/9/10
     * @param sheet
     * @param cellList
     * @param workbook
     */
    private void writeQueryConditionRow(Sheet sheet, List<HeaderCell> cellList, Workbook workbook) {

        Long t1 = System.currentTimeMillis();
        if(cellList.size() > 1){
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, cellList.size() - 1));
        }
        Row rowTitleBig = sheet.createRow(1);
        Cell cellBig = rowTitleBig.createCell(0);

        XSSFCellStyle cellStyle = (XSSFCellStyle)workbook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setTopBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setLocked(false);
        rowTitleBig.setHeightInPoints(10);
        cellBig.setCellStyle(cellStyle);

        int size = cellList.size();
        for (int i = 1; i< size; i++) {
            Cell cell = rowTitleBig.createCell(i);
            cell.setCellStyle(cellStyle);
        }
        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.writeQueryConditionRow 耗时：{}ms", t2 - t1);
    }

    /**
     * 写大标题
     * @author lnb
     * @date created in 11:18 2019/9/10
     */
    private void writeBigTitle(Sheet sheet, List<HeaderCell> columns, String excelTitle, Workbook workbook) {
        Long t1 = System.currentTimeMillis();
        Row rowTitleBig = sheet.createRow(0);
        rowTitleBig.setHeightInPoints(22);
        Cell cellBig = rowTitleBig.createCell(0);
        if(columns.size() > 1){
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns.size() - 1));
        }
        cellBig.setCellValue(" " + excelTitle);
        Font font2 = workbook.createFont();
        font2.setFontName("SansSerif");
        font2.setFontHeightInPoints((short) 14);
        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle cellStyle2 = workbook.createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_LEFT);
        cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle2.setLocked(false);
        cellStyle2.setFont(font2);
        cellBig.setCellStyle(cellStyle2);
        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.writeBigTitle 耗时：{}ms", t2 - t1);

    }

    /**
     * 写入数据
     * @author lnb
     * @date created in 16:24 2019/9/9
     */
    private void writeData(List<Map<String, Object>> dataList, Sheet sheet, Integer count, List<HeaderCell> cellList, Map<String, XSSFCellStyle> cellStyleMap) throws Exception {
        Long t1 = System.currentTimeMillis();
        if(CollectionUtil.notNull(dataList)){
            //遍历数据集合
            int size = dataList.size();
            for(int i = 0; i < size; i++){
                //遍历表头，根据表头获取数据
                Row rowLine = sheet.createRow(i + count + 2);
                int ros = 0;
                //设置序号
                Map<String, Object> data = dataList.get(i);
                data.put("key", i + 1);
                for(HeaderCell headerCell : cellList){
                    Cell cell = rowLine.createCell(ros);
                    setCellValue(cell, headerCell, data, cellStyleMap, i);
                    ros++;
                }
            }
        }
        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.writeData 耗时：{}ms", t2 - t1);
    }

    /**
     * 获取HeaderGroup中的HearderCell
     * @author lnb
     * @date created in 14:36 2019/9/9
     */
    public void getHeaderCellList(List<HeaderCell> resultList, List<Head> list){
        for(Head head : list){
            if(head instanceof HeaderCell){
                resultList.add((HeaderCell)head);
            }else if(head instanceof HeaderGroup){
                HeaderGroup group = (HeaderGroup)head;
                List<Head> subList = group.getChildren();
                if(subList.size() >= 0 && subList != null){
                    getHeaderCellList(resultList, subList);
                }else{
                    continue;
                }
            }else{

            }
        }
    }

    /**
     * 写入表头
     * @author lnb
     * @date created in 16:03 2019/9/9
     */
    private void writeHeader(List<Head> columns, Sheet sheet, Workbook workbook, List<HeaderCell> cellList, Integer count) {
        Long t1 = System.currentTimeMillis();
        CellStyle cellStyle = getColumnsStyle(workbook);
        if(count == 1){
            Row titleRow = sheet.createRow(2);
            int size = columns.size();
            for (int i = 0; i< size; i++) {
                Head head = columns.get(i);
                if(head instanceof HeaderCell){
                    Cell cell = titleRow.createCell(i);
                    cell.setCellStyle(cellStyle);

                    HeaderCell headerCell = (HeaderCell)head;
                    cell.setCellValue(headerCell.getTitle());
                    //列宽
                    sheet.setColumnWidth(i, headerCell.getWidth() * 35);
                }
            }
        }else{
            //根据最大行数，创建表头，设置每列的宽度
            for(int k=0; k<count; k++){
                sheet.createRow(k + 2);
                Row row = sheet.getRow(k + 2);
                int size= cellList.size();
                for(int n=0; n<size; n++){
                    Cell cell = row.createCell(n);
                    cell.setCellStyle(cellStyle);
                    //列宽
                    sheet.setColumnWidth(n, cellList.get(n).getWidth() * 35);
                }
            }
            //单元格合并
            mergeCell(columns, sheet, count);

        }
        Long t2 = System.currentTimeMillis();
        log.info("ExportUtil.writeHeader 耗时：{}ms", t2 - t1);
    }

    /**
     * 多级表头、一级表头第一行单元格合并
     * @author lnb
     * @date created in 17:36 2019/9/17
     */
    public void mergeCell(List<Head> columns, Sheet sheet, Integer count){
        int num = 0;
        int size = columns.size();
        int count1 = 0;
        for (int i = 0; i < size; i++) {
            Head head = columns.get(i);
            if(head instanceof HeaderCell){
                sheet.addMergedRegion(new CellRangeAddress(2, count + 1, count1, count1));
                HeaderCell headerCell = (HeaderCell)head;
                Row row = sheet.getRow(2);
                Cell cell = row.getCell(count1);
                cell.setCellValue(headerCell.getTitle());
                num ++;
                count1 ++;
            }else if(head instanceof HeaderGroup){
                HeaderGroup headerGroup = (HeaderGroup)head;
                //获取所有的列
                List<HeaderCell> cellList = new ArrayList<>();
                getHeaderCellList(cellList, headerGroup.getChildren());
                if(cellList.size() > 1){
                    sheet.addMergedRegion(new CellRangeAddress(2, 2, num,num+cellList.size()-1));
                }
                Row row = sheet.getRow(2);
                Cell cell = row.getCell(num);
                cell.setCellValue(headerGroup.getTitle());

                count1 = count1 + cellList.size();

                List<Head> subList = headerGroup.getChildren();
                num = mergeSubCell(subList, sheet, num, 3);

            }
        }
    }

    /**
     * 多级表头二级以上部分单元格合并
     * @author lnb
     * @date created in 17:37 2019/9/17
     */
    public Integer mergeSubCell(List<Head> subList, Sheet sheet, Integer num, Integer count){
        Row titleRow = sheet.getRow(count);
        int size = subList.size();
        for (int m = 0; m< size; m++) {
            Head head = subList.get(m);
            Cell cell1 = titleRow.getCell(num);
            if(head instanceof HeaderCell){
                HeaderCell headerCell = (HeaderCell)head;
                cell1.setCellValue(headerCell.getTitle());
                num ++;
            }else if(head instanceof HeaderGroup){
                HeaderGroup headerGroup = (HeaderGroup)head;
                List<HeaderCell> cellList = new ArrayList<>();
                getHeaderCellList(cellList, headerGroup.getChildren());

                if (cellList.size() > 1) {
                    sheet.addMergedRegion(new CellRangeAddress(count, count, num,num+cellList.size()-1));
                }
                Row row = sheet.getRow(count);
                Cell cell = row.getCell(num);
                cell.setCellValue(headerGroup.getTitle());
                List<Head> subList1 = headerGroup.getChildren();
                num = mergeSubCell(subList1, sheet, num, count + 1);
            }
        }
        return num;
    }

    /**
     * 获取表头中的最大行数
     * @author lnb
     * @date created in 15:05 2019/9/9
     */
    private Integer getTitleCount(Integer count, List<Head> columns) {
        Integer max = new Integer(count);
        for(Head head : columns){
            Integer newCount = new Integer(count);
            if(head instanceof HeaderCell){
                continue;
            }else if(head instanceof HeaderGroup){
                HeaderGroup headerGroup = (HeaderGroup)head;
                newCount ++;
                List<Head> subList = headerGroup.getChildren();
                newCount = getTitleCount(newCount, subList);
            }
            if (newCount > max) {
                max = newCount;
            }
        }
        return max;
    }

    /**
     * 表头样式
     * @author lnb
     * @date created in 16:01 2019/9/9
     */
    private CellStyle getColumnsStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("SansSerif");
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        XSSFCellStyle cellStyle = (XSSFCellStyle)workbook.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setFont(font);
        cellStyle.setBottomBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setRightBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setTopBorderColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setLocked(false);
        // 指定当单元格内容显示不下时自动换行
        cellStyle.setWrapText(true);
        return cellStyle;
    }

    /**
     * 数据样式
     * @author lnb
     * @date created in 16:20 2019/9/9
     */
    private XSSFCellStyle getDataStyle1(Workbook workbook) {
        XSSFColor color = new XSSFColor(new java.awt.Color(240, 240, 240));
        Font font = workbook.createFont();
        font.setFontName("SansSerif");
        font.setFontHeightInPoints((short) 10);

        XSSFCellStyle cellStyle = (XSSFCellStyle)workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBottomBorderColor(color);
        cellStyle.setLeftBorderColor(color);
        cellStyle.setRightBorderColor(color);
        cellStyle.setTopBorderColor(color);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        cellStyle.setWrapText(true);// 指定当单元格内容显示不下时自动换行
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //填充单元格
        cellStyle.setFillForegroundColor(color);//设置单元格的背景颜色
        return cellStyle;
    }

    /**
     * 数据样式
     * @author lnb
     * @date created in 16:20 2019/9/9
     */
    private XSSFCellStyle getDataStyle2(Workbook workbook) {
        XSSFColor color1 = new XSSFColor(new java.awt.Color(240, 240, 240));
        XSSFColor color2 = new XSSFColor(new java.awt.Color(212, 212, 212));
        Font font = workbook.createFont();
        font.setFontName("SansSerif");
        font.setFontHeightInPoints((short) 10);

        XSSFCellStyle cellStyle = (XSSFCellStyle)workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBottomBorderColor(color1);
        cellStyle.setLeftBorderColor(color2);
        cellStyle.setRightBorderColor(color2);
        cellStyle.setTopBorderColor(color1);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  //填充单元格
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 255)));//设置单元格的背景颜色
        cellStyle.setWrapText(true);// 指定当单元格内容显示不下时自动换行
        return cellStyle;
    }

    /**
     * 设置style
     * @author lnb
     * @date created in 10:58 2019/10/22
     */
    private void setStyle(int i, boolean isCompile, XSSFCellStyle cellStyle1, XSSFCellStyle cellStyle2, Cell cell){
        if(i%2 == 1){
            cell.setCellStyle(cellStyle1);
        }else {
            cell.setCellStyle(cellStyle2);
        }
    }


    /**
     * 设置单元格的值
     */
    public void setCellValue(Cell cell, HeaderCell headerCell, Map<String, Object> dataMap, Map<String, XSSFCellStyle> cellStyleMap, int i) throws Exception {
        //根据表头获取数据
        Object data = dataMap.get(headerCell.getDataIndex());

        if(data != null && !data.equals("")){
            //设置单元格的值
            switch (headerCell.getClassName()){
                //数值型,保留两位小数
                case HeaderUtil.NUM:
                    cell.setCellValue(Double.parseDouble(data.toString()));
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_NUM"), cellStyleMap.get("cellStyle2_NUM"), cell);
                    break;
                //整型，居右，选中可以自动求和
                case HeaderUtil.LONG:
                    cell.setCellValue(Long.valueOf(data.toString()));
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_LONG"), cellStyleMap.get("cellStyle2_LONG"), cell);
                    break;
                //百分比，居右，选中可以自动求和
                case HeaderUtil.NUM_RATE:
                    if(data.toString().contains("%")){
                        String numStr = data.toString().replace("%", "");
                        Double num = NumberUtil.div(numStr, 100);
                        cell.setCellValue(num);
                    }else{
                        Double num = NumberUtil.div(data.toString(), 100);
                        cell.setCellValue(num);
                    }
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_NUM_RATE"), cellStyleMap.get("cellStyle2_NUM_RATE"), cell);
                    break;
                //文字居中，文本格式
                case HeaderUtil.CENTER:
                    cell.setCellValue(data.toString());
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_CENTER"), cellStyleMap.get("cellStyle2_CENTER"), cell);
                    break;
                //文字居左，文本格式
                case HeaderUtil.STR:
                    cell.setCellValue(data.toString());
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_STR"), cellStyleMap.get("cellStyle2_STR"), cell);
                    break;
                //时间格式
                case HeaderUtil.COLUMN_DATE:
                    try {
                        String date = data.toString();
                        if(date.contains("-")){
                            date.replace("-", "");
                        }else if(date.contains(".")){
                            date.replace(".", "");
                        }else if(date.contains("/")){
                            date.replace("/", "");
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        long time_long = sdf.parse(date).getTime();
                        cell.setCellValue(new Date(Long.valueOf(time_long)));
                        setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_COLUMN_DATE"), cellStyleMap.get("cellStyle2_COLUMN_DATE"), cell);
                    }catch (Exception e){
                        log.error("导出时间格式设置异常", e.getStackTrace());
                        throw new Exception("导出时间格式设置异常");
                    }

                    break;
                //日期格式
                case HeaderUtil.COLUMN_DATE_TIME:
                    try {
                        String date = data.toString();
                        if (date.contains("-")) {
                            date.replace("-", "");
                        } else if (date.contains("/")) {
                            date.replace("/", "");
                        }else if (date.contains(".")) {
                            date.replace(".", "");
                        }else if (date.contains(" ")) {
                            date.replace(" ", "");
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                        long time_long = sdf.parse(date).getTime();
                        cell.setCellValue(new Date(Long.valueOf(time_long)));
                        setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_COLUMN_DATE_TIME"), cellStyleMap.get("cellStyle2_COLUMN_DATE_TIME"), cell);
                    }catch (Exception e){
                        log.error("导出时间格式设置异常", e.getStackTrace());
                        throw new Exception("导出时间格式设置异常");
                    }
                    break;
                //其他情况，均当做文本格式处理
                default:
                    cell.setCellValue(data.toString());
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_STR"), cellStyleMap.get("cellStyle2_STR"), cell);
            }
        }else{
            //设置单元格的值
            switch (headerCell.getClassName()){
                //数值型,保留两位小数
                case HeaderUtil.NUM:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_NUM"), cellStyleMap.get("cellStyle2_NUM"), cell);
                    break;
                //整型，居右，选中可以自动求和
                case HeaderUtil.LONG:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_LONG"), cellStyleMap.get("cellStyle2_LONG"), cell);
                    break;
                //百分比，居右，选中可以自动求和
                case HeaderUtil.NUM_RATE:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_NUM_RATE"), cellStyleMap.get("cellStyle2_NUM_RATE"), cell);
                    break;
                //文字居中，文本格式
                case HeaderUtil.CENTER:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_CENTER"), cellStyleMap.get("cellStyle2_CENTER"), cell);
                    break;
                //文字居左，文本格式
                case HeaderUtil.STR:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_STR"), cellStyleMap.get("cellStyle2_STR"), cell);
                    break;
                //时间格式
                case HeaderUtil.COLUMN_DATE:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_COLUMN_DATE"), cellStyleMap.get("cellStyle2_COLUMN_DATE"), cell);
                    break;
                //日期格式
                case HeaderUtil.COLUMN_DATE_TIME:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_COLUMN_DATE_TIME"), cellStyleMap.get("cellStyle2_COLUMN_DATE_TIME"), cell);
                    break;
                //其他情况，均当做文本格式处理
                default:
                    setStyle(i, headerCell.isCompile(), cellStyleMap.get("cellStyle1_STR"), cellStyleMap.get("cellStyle2_STR"), cell);
            }
        }
    }


}