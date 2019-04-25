package com.whuaz.hystrix.demo.controller;

import com.whuaz.hystrix.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grez
 * @since 19-4-25
 **/
@RestController
@RequestMapping("/edg")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public String user(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
