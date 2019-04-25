package com.whuaz.hystrix.demo.service;

import com.whuaz.hystrix.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author grez
 * @since 19-4-25
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getUser(Long id) {
        String user = null;
        try {
            user = userDao.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
