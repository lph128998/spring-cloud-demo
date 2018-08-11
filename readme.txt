#开启eureka集群
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
访问方式-http://localhost:8888
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2
访问方式-http://localhost:8889

#启动服务发布者
java -jar target/hello-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
java -jar target/hello-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev

#启动服务消费者
java -jar target/ribbon-consumer-0.0.1-SNAPSHOT.jar

#启动hystrix dashboard
java -jar target/hystrix-dashboard-0.0.1-SNAPSHOT.jar

#启动turbine
java -jar target/turbine-0.0.1-SNAPSHOT.jar

#启动turbine-amqp

#启动feign-consumer
java -jar target/feign-consumer-0.0.1-SNAPSHOT.jar 
http://localhost:9001/feign-consumer
http://localhost:9001/feign-consumer2

#启动api-gateway
java -jar target/api-gateway-0.0.1-SNAPSHOT.jar
http://localhost:5555
http://localhost:5555/hello-service/hello/index
http://localhost:5555/feign-consumer/feign-consumer2

#分布式配置中心-服务端
java -jar target/config-server-0.0.1-SNAPSHOT.jar
http://localhost:7001/application/prod/config-label-test

#分布式配置中心-客户端
java -jar target/config-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=test
java -jar target/config-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
http://localhost:7002/from

#rabbitmq测试
java -jar target/rabbitmq-hello-0.0.1-SNAPSHOT.jar 
执行测试用例


# 使用hystrix cache
真正的web项目是在filter中进行初始化的。
首先实现filter重写其方法，在Filter上采用注解的方式@WebFilter，别忘了在启动类上添加@ServletComponentScan注解。


#启动rabbitmq
rabbitmq-server start
#rabbitmq通过web管理
rabbitmq-plugins enable rabbitmq_management
#账号/密码默认guest/guest
http://localhost:15672/

#sleuth
#启动trace1
java -jar target/trace1-0.0.1-SNAPSHOT.jar
#启动trace2
java -jar target/trace2-0.0.1-SNAPSHOT.jar 
#访问trace1
http://localhost:9101/trace1

#启动zipkin
java -jar target/zipkin-server-0.0.1-SNAPSHOT.jar 
#访问zipkin
http://localhost:9411/zipkin/


