package com.rhwayfun.springboot.quickstart.pojo;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@WebMvcTest(Dog.class)
public class DogTest {
    @Autowired
    private Dog dog;

    @Test
    public void contextLoads(){
        System.out.println(dog);
    }

    @Test
    public void testonly(){
        final HashMap<String, String> objectObjectHashMap = Maps.newHashMapWithExpectedSize(1);
        objectObjectHashMap.put("key1","value1");
        objectObjectHashMap.clear();
        objectObjectHashMap.put("key2","value1");
        System.out.println(objectObjectHashMap);
    }
}
