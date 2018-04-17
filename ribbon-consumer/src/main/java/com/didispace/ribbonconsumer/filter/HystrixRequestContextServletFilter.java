package com.didispace.ribbonconsumer.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 通过servlet的Filter配置hystrix上下文
 * @author liupeihua
 * @date 2018/3/15 下午5:49
 */
@WebFilter(filterName = "hystrixRequestContextServletFilter",urlPatterns = "/*", asyncSupported = false)
public class HystrixRequestContextServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}
