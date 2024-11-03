package com.jay.web.controller.site.auth;

import com.jay.BaseControllerTest;
import com.jay.util.MD5;
import com.jay.web.controller.site.Views;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * Created by xiang.wei on 2018/10/15
 *
 * @author xiang.wei
 */
public class LoginControllerTest extends BaseControllerTest {

    @Test
    public void login() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .param("username", "admin")
                .param("password", "123456"))
                .andExpect(MockMvcResultMatchers.content().string(Views.REDIRECT_USER))
                .andReturn();
    }

}