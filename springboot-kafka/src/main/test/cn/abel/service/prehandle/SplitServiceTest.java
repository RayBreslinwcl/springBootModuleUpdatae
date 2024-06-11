package cn.abel.service.prehandle;

import cn.abel.Application;
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
 * @Created: 2024/6/11 10:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SplitServiceTest extends TestCase {

    @Autowired
    private SplitService splitService;

    @Test
    public void testSaveAndSplitLog() {
//        SplitService splitService=new SplitService();
        String json="{\"message\": \"小明\",\"age\": 18}";
        splitService.saveAndSplitLog(json);
    }
}