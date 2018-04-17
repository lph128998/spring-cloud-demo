package com.didispace.ribbonconsumer.command;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * @author liupeihua
 * @date 2018/3/13 下午8:10
 */
public class HelloCommand extends HystrixCommand<String> {
    private RestTemplate restTemplate;

    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        return restTemplate.getForObject("Http://HELLO-SERVICE/hello/index", String.class);
    }
}
