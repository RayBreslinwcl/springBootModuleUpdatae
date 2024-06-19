package com.rhwayfun.springboot.quickstart.client;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/19 10:22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceFeignOfQuickStartClientTest extends TestCase {

    @Resource
    ServiceFeignOfQuickStartClient serviceFeignOfQuickStartClient;

    @Test
    public void testGetNow() {
        System.out.println(serviceFeignOfQuickStartClient);
        System.out.println(serviceFeignOfQuickStartClient.getNow());
    }
}