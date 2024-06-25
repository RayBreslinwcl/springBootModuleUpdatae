package com.ray.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/17 18:45
 */
@SpringBootTest
class HikariHologServiceTest {

    @Autowired
    HikariHologService hikariHologService;


    @Test
    void getAllData() {
        List<Map<String, Object>> allData = hikariHologService.getAllData();
        if(allData.size()!=0){
            System.out.println(hikariHologService.getAllData());
            System.out.println("one_id："+allData.get(0).get("one_id"));

        }else {
            System.out.println("为空");
        }
        System.out.println(allData.size());

    }

    @Test
    void getAllData_orderdetail() {
        System.out.println(hikariHologService.getAllData_orderdetail().toString());
    }

    @Test
    void testonly(){
        //需要替换sql
        String sqlContent = "select * from table1";

        //构建map
        HashMap<String, String> tableReplaceMap=new HashMap<>();

        String oneid="9527";

        String labelTableItem = "table1,table2";
        if(labelTableItem!=null){
            String[] labelTableItems = labelTableItem.split(",");
            //替换构建mapping
            String originalString="(select * from %s where %s = '%s')t";
            for (String tableItem : labelTableItems) {
                //属性
                if(tableItem.contains("profile") || tableItem.contains("tag")){
                    tableReplaceMap.put(tableItem, String.format(originalString, tableItem,"oneid",oneid));
                }else{
                    tableReplaceMap.put(tableItem, String.format(originalString, tableItem,"cdmid",oneid));
                }
            }
            System.out.println(tableReplaceMap);
        }

        for (String key : tableReplaceMap.keySet()) {
            sqlContent=sqlContent.replace(key,tableReplaceMap.get(key));
        }

        System.out.println(sqlContent);

    }

    @Test
    void test2(){
        System.out.println(String.valueOf(System.currentTimeMillis()));
    }
}