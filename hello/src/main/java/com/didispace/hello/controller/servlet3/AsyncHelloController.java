package com.didispace.hello.controller.servlet3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异步处理,未使用异步处理线程迟
 * @author liupeihua
 * @date 2018/6/15 上午10:12
 */
@RestController
@RequestMapping(value = "/asyncHello")
public class AsyncHelloController {

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread);

        // 开启异步处理
        AsyncContext asyncContext = request.startAsync();

        asyncContext.start(() -> {
            new LongRunningProcess().run();

            try {
                asyncContext.getResponse().getOutputStream().write("Hello World1!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }

            asyncContext.complete();
        });

        return "Hello World! async";
    }
}
