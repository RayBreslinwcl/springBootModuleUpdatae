package com.rhwayfun.springboot.quickstart;

import com.rhwayfun.springboot.quickstart.pojo.Dog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    private Dog dog;

    @Test
    public void contextLoads(){
        System.out.println(dog);
    }
}
