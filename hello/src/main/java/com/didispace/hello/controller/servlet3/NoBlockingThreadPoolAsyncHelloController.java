package com.didispace.hello.controller.servlet3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步处理,使用异步处理线程迟,非阻塞
 * @author liupeihua
 * @date 2018/6/15 上午10:12
 */
@RestController
@RequestMapping(value = "/noBlockingThreadPoolAsyncHello")
public class NoBlockingThreadPoolAsyncHelloController {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 50000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(100));

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread);

        // 开启异步处理
        AsyncContext asyncContext = request.startAsync();

        ServletInputStream inputStream = request.getInputStream();
        inputStream.setReadListener(new ReadListener() {
            @Override
            public void onDataAvailable() throws IOException {

            }

            @Override
            public void onAllDataRead() throws IOException {
                executor.execute(() -> {
                    new LongRunningProcess().run();

                    try {
                        asyncContext.getResponse().getOutputStream().write("Hello World1!".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                    }

                    asyncContext.complete();
                });
            }

            @Override
            public void onError(Throwable throwable) {
                asyncContext.complete();
            }
        });

        return "Hello World! async";
    }
}
