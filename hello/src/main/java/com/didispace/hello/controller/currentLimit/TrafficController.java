package com.didispace.hello.controller.currentLimit;

/**
 * @author liupeihua
 * @date 2018/6/14 下午8:20
 */

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liupeihua
 * @date 2018/2/26 上午10:05
 */
@RestController
@RequestMapping("/traffic")
public class TrafficController {

    /**
     * 计数器，起始：0
     */
    private static final AtomicLong atomic = new AtomicLong();

    /**
     * 总并发/请求数限制
     */
    private static final long TRAFFIC_LIMIT = 5;

    @Value("${server.port}")
    private int serverPort;

    /**
     * Guava的cache来存储计数器，过期时间为2秒（保证能记录1秒内的技数）
     */
    private static final LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder()
            .expireAfterWrite(2, TimeUnit.SECONDS)
            .build(new CacheLoader<Long, AtomicLong>() {
                @Override
                public AtomicLong load(Long seconds) throws Exception {
                    return new AtomicLong();
                }
            });

    /**
     * 限流某个接口的总并发/请求数
     * @param age
     * @return
     */
    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public String index(@RequestParam final Integer age) {
        final Optional<String> result;
        try {
            if (atomic.incrementAndGet() > TRAFFIC_LIMIT) {
                // 拒绝请求
                System.out.println("拒绝请求！" + atomic.get());
                result = Optional.empty();
            } else {
                result = Optional.of("Hello " + age);
            }
        } finally {
            atomic.decrementAndGet();
        }

        System.out.println(serverPort);

        return result.orElse("default");
    }

    /**
     * 限流某个接口的时间窗并发/请求数
     */
    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public String index() {
        long currentSeconds = System.currentTimeMillis() / 1000;

        try {
            if (counter.get(currentSeconds).incrementAndGet() > TRAFFIC_LIMIT) {
                System.out.println("限流了：" + currentSeconds);
                return "限流了";
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {

        }

        return "正常";
    }
}
