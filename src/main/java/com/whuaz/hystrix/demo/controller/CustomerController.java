package com.whuaz.hystrix.demo.controller;

import com.whuaz.hystrix.demo.model.Customer;
import com.whuaz.hystrix.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grez
 * @since 19-4-24
 **/
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String customerId) {
//        return customerService.getConsumer(customerId);
        return customerService.getCustomerThroughDao(customerId);
    }
}
