package com.didispace.hello;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * RocketMq检测器
 * @author liupeihua
 * @date 2018/2/26 下午9:45
 */
@Component
public class RocketMqHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if(errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }

        return Health.up().build();
    }

    private int check() {
        // 对监控对象的检测操作
        return 1;
    }
}
