package com.whuaz.hystrix.demo.service;

import com.whuaz.hystrix.demo.App;
import com.whuaz.hystrix.demo.model.Address;
import com.whuaz.hystrix.demo.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
/**
 * @author grez
 * @since 19-4-24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void getCustomer() {
        Customer consumer = customerService.getConsumer("1234");
//        Customer consumer = customerService.getCustomerThroughDao("1234");
        assertThat(consumer.getId(), equalTo("1234"));
        Address address = consumer.getAddress();
        System.out.println(address.toString());
    }

}
