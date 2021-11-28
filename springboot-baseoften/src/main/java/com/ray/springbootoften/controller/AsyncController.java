package com.ray.springbootoften.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ray.springbootoften.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AsyncController {

   @Autowired
   AsyncService asyncService;
   @GetMapping("/hello")
   public String hello(){
       asyncService.hello();
       return "success";
  }

    /**
     * 目标json
     *
     *   {
     *       xData: ["data1", "data2", "data3", "data4", "data5", "data6"],
     *       seriesData: [
     *          { value: 30, name: "data1" },
     *           { value: 5, name: "data2" },
     *           { value: 15, name: "data3" },
     *           { value: 25, name: "data4" },
     *           { value: 20, name: "data5" },
     *           { value: 35, name: "data6" }
     *       ]
     *   }
     *
     * @return
     */
    @GetMapping("/api/user/list")
    public String list(){
        //map转json对象
        Map<String,Object> map = new HashMap<>();
        List xData= Arrays.asList("data1", "data2", "data3", "data4", "data5", "data6");
        JSONObject jo1=new JSONObject();
        jo1.put("value",31);
        jo1.put("name","data1");
        JSONObject jo2=new JSONObject();
        jo2.put("value",5);
        jo2.put("name","data2");
        JSONObject jo3=new JSONObject();
        jo3.put("value",15);
        jo3.put("name","data3");
        JSONObject jo4=new JSONObject();
        jo4.put("value",25);
        jo4.put("name","data4");
        JSONObject jo5=new JSONObject();
        jo5.put("value",20);
        jo5.put("name","data5");
        JSONObject jo6=new JSONObject();
        jo6.put("value",36);
        jo6.put("name","data6");
        JSONArray ja=new JSONArray();
        ja.add(jo1);
        ja.add(jo2);
        ja.add(jo3);
        ja.add(jo4);
        ja.add(jo5);
        ja.add(jo6);

//        List seriesData=Arrays.asList("{ value: 60, name: 'data1' }","{ value: 50, name: 'data2' }","{ value: 15, name: 'data3' }",
//                " { value: 25, name: 'data4' }","{ value: 20, name: 'data5' }","{ value: 35, name: 'data6'}");
        map.put("xData", xData);
        map.put("seriesData", ja);
        JSONObject json = new JSONObject(map);//json对象转Map 　　Map<String,Object> map = (Map<String,Object>)jsonObject;
        return json.toString();
    }


}