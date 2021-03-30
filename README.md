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

* docker  run \
--name nacos -d \
-p 8848:8848 \
--privileged=true \
--restart=always \
-e JVM_XMS=256m \
-e JVM_XMX=256m \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-v /Users/wangbin/dockerall/nacos/nacos_docker/init.d/custom.properties:/home/nacos/init.d/custom.properties \
-v /Users/wangbin/dockerall/nacos/nacos_docker/logs:/home/nacos/logs \
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
