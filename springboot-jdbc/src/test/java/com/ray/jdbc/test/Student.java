package com.ray.jdbc.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @JSONField(name = "name2")
    private String name;

    @JSONField(name = "AGE",serialize=false)
    private String age;

    // @JSONField也可以直接作用在get或set方法上
//    @JSONField(name = "name")
//    public String getName() {
//        return name;
//    }
//
//    @JSONField(name = "name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @JSONField(name = "AGE")
//    public String getAge() {
//        return age;
//    }
//
//    @JSONField(name = "AGE")
//    public void setAge(String age) {
//        this.age = age;
//    }

    public static void main(String[] args) {
        Student student = Student.builder().name("gyl").age("20").build();
        String jsonString = JSONObject.toJSONString(student);
        System.out.println("bean to json: " + jsonString);
        student = JSONObject.toJavaObject(JSONObject.parseObject(jsonString), Student.class);
        System.out.println("json to bean: " + student.getName());
    }
}
