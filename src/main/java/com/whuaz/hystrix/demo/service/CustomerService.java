package com.whuaz.hystrix.demo.service;

import com.whuaz.hystrix.demo.dao.AddressDao;
import com.whuaz.hystrix.demo.dao.AddressHystrixCommand;
import com.whuaz.hystrix.demo.dao.ContactDao;
import com.whuaz.hystrix.demo.dao.ContactHystrixCommand;
import com.whuaz.hystrix.demo.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author grez
 * @since 19-4-24
 **/
@Service
public class CustomerService {

    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ContactDao contactDao;

    public Customer getConsumer(String customerId) {
        logger.info("Get Customer {}", customerId);
        try {
            Customer customer = new Customer(customerId, "wandouxia");
            customer.setContact(new ContactHystrixCommand(customerId).execute());
            customer.setAddress(new AddressHystrixCommand(customerId).execute());
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerThroughDao(String customerId) {
        logger.info("Get Customer {}", customerId);
        try {
            Customer customer = new Customer(customerId, "wandouxia");
            customer.setContact(contactDao.getContact(customerId));
            customer.setAddress(addressDao.getAddress(customerId));
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
