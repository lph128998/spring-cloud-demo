package com.didispace.feignconsumer.service;

import com.didispace.feignconsumer.DisableHystrixConfiguration;
import com.didispace.feignconsumer.po.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author liupeihua
 * @date 2018/3/22 下午3:18
 */
//@FeignClient(name = "hello-service", configuration = DisableHystrixConfiguration.class)
@FeignClient(name = "hello-service", fallback = HelloServiceFallback.class)
public interface HelloService {

    @RequestMapping("/hello/index")
    String hello();

    @RequestMapping(value = "/hello/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);
}
