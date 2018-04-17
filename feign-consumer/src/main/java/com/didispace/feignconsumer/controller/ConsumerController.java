package com.didispace.feignconsumer.controller;

import com.didispace.feignconsumer.po.User;
import com.didispace.feignconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liupeihua
 * @date 2018/3/22 下午3:30
 */
@RestController
public class ConsumerController {

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/feign-consumer", method = RequestMethod.GET)
    public String helloComsumer() {
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2", method = RequestMethod.GET)
    public String helloConsumer() {
        StringBuilder sb = new StringBuilder();
        sb.append(helloService.hello()).append("\r\n");
        sb.append(helloService.hello("DIDI")).append("\r\n");
        sb.append(helloService.hello("DIDI", 30)).append("\r\n");
        sb.append(helloService.hello(new User("DIDI", 30))).append("\r\n");

        return sb.toString();
    }
}
