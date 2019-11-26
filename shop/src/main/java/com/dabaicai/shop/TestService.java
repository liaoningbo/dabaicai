package com.dabaicai.shop;


import com.dabaicai.common.head.Head;
import com.dabaicai.common.head.HeaderCell;
import com.dabaicai.common.head.HeaderUtil;
import com.dabaicai.common.response.BasicResponse;
import com.dabaicai.common.response.ResponseData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    public BasicResponse test() {
        List<Head> columns = getColumns();
        List<Map<String, Object>> list = getList();
        Map<String, Object> sum = getSum();


        ResponseData response = new ResponseData(columns, list, sum, list.size());

        return BasicResponse.success(response);
    }

    private Map<String,Object> getSum() {
        Map<String, Object> map = new HashMap<>();
        map.put("index1", 100.00);
        map.put("index2", 100);
        map.put("index3", "10%");
        map.put("index4", "测试");
        map.put("index5", "测试");
        map.put("index6", "20191112");
        map.put("index7", "20191112120100");
        return map;
    }

    private List<Map<String,Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();

        for(int i=1; i<10; i++){
            Map<String, Object> map = new HashMap<>();
            map.put("key", i);
            map.put("index1", 100.00 * i);
            map.put("index2", 100 * i);
            map.put("index3", "10%");
            map.put("index4", "测试");
            map.put("index5", "测试");
            map.put("index6", "20191112");
            map.put("index7", "20191112120100");
            list.add(map);
        }
        return list;
    }

    private List<Head> getColumns() {
        List<Head> list = new ArrayList<>();
        list.add(HeaderCell.buildFirstCol());
        list.add(HeaderCell.buildCell("第一列", "index1", HeaderUtil.NUM, 100));
        list.add(HeaderCell.buildCell("第二列", "index2", HeaderUtil.LONG, 100));
        list.add(HeaderCell.buildCell("第三列", "index3", HeaderUtil.NUM_RATE, 100));
        list.add(HeaderCell.buildCell("第四列", "index4", HeaderUtil.CENTER, 100));
        list.add(HeaderCell.buildCell("第五列", "index5", HeaderUtil.STR, 100));
        list.add(HeaderCell.buildCell("第六列", "index6", HeaderUtil.COLUMN_DATE, 100));
        list.add(HeaderCell.buildCell("第七列", "index7", HeaderUtil.COLUMN_DATE_TIME, 100));
        return list;
    }
}
