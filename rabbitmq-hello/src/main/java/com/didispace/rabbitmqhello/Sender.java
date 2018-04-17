package com.didispace.rabbitmqhello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liupeihua
 * @date 2018/4/10 下午7:31
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "Hello " + new Date();
        System.out.println("Sender : " + context);

        // 发送到名为hello的队列中
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
