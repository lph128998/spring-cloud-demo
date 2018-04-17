package com.didispace.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.sun.media.jfxmedia.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author liupeihua
 * @date 2018/3/7 下午6:30
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(getClass());

    @CacheResult(cacheKeyMethod = "helloServiceCacheKey")
    @HystrixCommand(fallbackMethod = "helloFallback", commandKey = "helloKey")
    public String helloService() {
        long start = System.currentTimeMillis();

        String result= restTemplate.getForEntity("http://HELLO-SERVICE/hello/index", String.class).getBody();

        long end = System.currentTimeMillis();
        logger.info("Spend time:" + (end - start));

        return result;
    }

    public String helloFallback() {
        return "error";
    }

    private String helloServiceCacheKey() {
        return "hello";
    }


}
