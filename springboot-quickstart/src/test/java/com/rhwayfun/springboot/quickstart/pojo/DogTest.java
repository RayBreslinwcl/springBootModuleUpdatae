package com.rhwayfun.springboot.quickstart.pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(Dog.class)
public class DogTest {
    @Autowired
    private Dog dog;

    @Test
    public void contextLoads(){
        System.out.println(dog);
    }
}
