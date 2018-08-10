package com.didispace.hello.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/**
 * 平滑限制某个接口的请求数
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmoothBurstyTests {

	/**
	 *平滑突发限流
	 */
	@Test
	public void smoothBursty() {
		RateLimiter limiter = RateLimiter.create(5);
		System.out.println(limiter.acquire(5));
		System.out.println(limiter.acquire());
		System.out.println(limiter.acquire());
	}

	/**
	 * 平滑预热限流
	 */
	@Test
	public void SmoothWarmingUp() throws InterruptedException{
		RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);

		for(int i = 1; i < 5; i++) {
			System.out.println(limiter.acquire());
		}

		Thread.sleep(1000L);

		for(int i = 1; i < 5; i++) {
			System.out.println(limiter.acquire());
		}
	}


}
