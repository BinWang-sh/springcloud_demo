# springcloud_demo

## (1)nacosdemo

#### 概要:使用Feign进行负载均衡

##### 使用docker搭建nacos

* docker run -p 3306:3306 --name mysql \
-v /Users/wangbin/dockerall/mysql/log:/var/log/mysql \
-v /Users/wangbin/dockerall/mysql/data:/var/lib/mysql \
-v /Users/wangbin/dockerall/mysql/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=root \
-d mysql:5.7

* docker exec -it mysql bash

* mysql -proot

* create database nacos_config;
* use nacos_config;

* SQL语句
https://github.com/alibaba/nacos/blob/master/config/src/main/resources/META-INF/nacos-db.sql

* 新建/xxxxx/nacos_docker/init.d/目录并生成custom.properties文件。文件内容如下

```
management.endpoints.web.exposure.include=*
server.contextPath=/nacos
server.servlet.contextPath=/nacos
server.port=8848
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=root
nacos.cmdb.dumpTaskInterval=3600
nacos.cmdb.eventTaskInterval=10
nacos.cmdb.labelTaskInterval=300
nacos.cmdb.loadDataAtStart=false
management.metrics.export.elastic.enabled=false
management.metrics.export.influx.enabled=false
server.tomcat.accesslog.enabled=true
server.tomcat.accesslog.pattern=%h %l %u %t "%r" %s %b %D %{User-Agent}i
nacos.security.ignore.urls=/,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/v1/auth/login,/v1/console/health/**,/v1/cs/**,/v1/ns/**,/v1/cmdb/**,/actuator/**,/v1/console/server/**
nacos.naming.distro.taskDispatchThreadCount=1
nacos.naming.distro.taskDispatchPeriod=200
nacos.naming.distro.batchSyncKeyCount=1000
nacos.naming.distro.initDataRatio=0.9
nacos.naming.distro.syncRetryDelay=5000
nacos.naming.data.warmup=true
nacos.naming.expireInstance=true
```

* 新建logs目录

* docker  run \
--name nacos -d \
-p 8848:8848 \
--privileged=true \
--restart=always \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v /xxxxx/nacos_docker/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /xxxxx/nacos_docker/logs:/home/nacos/logs \
nacos/nacos-server

#### 新建Maven工程，添加子module
common-module  //定义服务接口，返回数据类型
userserviceprovider //服务提供者
userserviceprovider2 //服务提供者
consumer //消费者

在consumer的bootstrap.yml中可以指定负载策略

```
user-provider:
  ribbon:
    #    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule #配置规则 随机
    #    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule #配置规则 轮询
    #    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RetryRule #配置规则 重试
    #    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule #配置规则 响应时间权重
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule
```

## (2)nacosconfigdemo

读取nacos配置的内容

* bootstrap.yml文件中指定配置信息
 spring:
  application:
    name: userservice
  cloud:
    nacos:
      discovery:
        # Nacos地址
        server-addr: http://127.0.0.1:8848
      config:
        # Nacos地址
        server-addr: http://127.0.0.1:8848

在nacos中根据spring.application.name-env.properties取得配置信息


## (3)sentineldemo

获取运行sentinel dashboard
* 从 https://codeload.github.com/alibaba/Sentinel/zip/1.7.2 下载

* mvn clean package

* java -Dserver.port=8888 -Dcsp.sentinel.dashboard.server=localhost:8888 -Dproject.name=sentinel-dashboard -jar ./sentinel-dashboard/target/sentinel-dashboard.jar

* 在nacos添加限流策略，具体内容如下
Data ID: sentineldemo-flow-rules
Group: DEFAULT_GROUP
Json

```[
  {
    "resource": "getName-resource",
    "controlBehavior": 0,
    "count": 1,
    "grade": 1,
    "limitApp": "default",
    "strategy": 0
  },
  {
    "resource": "sayHello-resource",
    "controlBehavior": 0,
    "count": 1,
    "grade": 1,
    "limitApp": "default",
    "strategy": 0
  }
]
```
快速访问 http://localhost:8400/hello/xxx 会出现 “请求被限流,触发限流规则=sayHello-resource” 提示。（规则设定1秒访问1次）
