package com.didispace.ribbonconsumer.Controller;

import com.didispace.ribbonconsumer.service.HelloService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author liupeihua
 * @date 2018/2/27 下午5:56
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        //HystrixRequestContext context = HystrixRequestContext.initializeContext();

        //return restTemplate.getForEntity("http://HELLO-SERVICE/hello/index", String.class).getBody();
        return helloService.helloService();
    }
}
