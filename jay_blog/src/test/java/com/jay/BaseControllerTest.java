package com.jay;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by xiang.wei on 2018/10/15
 *
 * @author xiang.wei
 */
@RunWith(SpringRunner.class)  //表示使用spring test组件进行单元测试
@SpringBootTest
@AutoConfigureMockMvc  //注入一个mockMvc实例
@WebAppConfiguration  //测试环境使用
public class BaseControllerTest {
    protected MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext context;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

}
