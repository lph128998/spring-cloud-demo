package com.didispace.hello.controller.servlet3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 同步处理
 * @author liupeihua
 * @date 2018/6/15 上午10:12
 */
@RestController
@RequestMapping("/syncHello")
public class SyncHelloController {

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String currentThread = Thread.currentThread().getName();
        System.out.println(currentThread);

        new LongRunningProcess().run();

        response.getOutputStream().write("Hello World!".getBytes());

        return "Hello World! sync";
    }
}
