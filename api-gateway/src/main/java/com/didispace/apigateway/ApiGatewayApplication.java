package com.didispace.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy //开启Zuul的API网关服务
@SpringCloudApplication
public class ApiGatewayApplication {

//	/**
//	 * 自定义过滤器之后，它并不会直接生效，还需要创建具体的bean才能启动过滤器
//	 * @return
//     */
//	@Bean
//	public AccessFilter accessFilter() {
//		return new AccessFilter();
//	}

	/**
	 * 自定义路由映射规则
	 * @return
     */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper(
				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}"
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}
