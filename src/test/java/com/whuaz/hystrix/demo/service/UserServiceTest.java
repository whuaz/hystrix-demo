package com.whuaz.hystrix.demo.service;

import com.whuaz.hystrix.demo.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author grez
 * @since 19-4-25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUser() throws IOException {
        String user = userService.getUser(1L);
        System.out.println(user);
    }
}
