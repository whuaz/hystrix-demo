package com.whuaz.hystrix.demo.dao;

import com.whuaz.hystrix.demo.model.Address;
import org.apache.http.client.fluent.Request;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author grez
 * @since 19-4-24
 **/
@Component
public class AddressDao {

    private Logger logger = LoggerFactory.getLogger(AddressDao.class);

    public Address getAddress(String customerId) throws IOException {
        logger.info("Get address for customer {}", customerId);
        String response = Request.Get("http://localhost:9090/customer/" + customerId + "/address")
                .connectTimeout(1000)
                .socketTimeout(1000)
                .execute()
                .returnContent()
                .asString();
        return new ObjectMapper().readValue(response, Address.class);
    }
}
