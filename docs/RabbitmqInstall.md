# 安装

```sh
vim /etc/yum.repos.d/rabbitmq.repo

[rabbitmq]
name=rabbitmq
baseurl=https://packagecloud.io/rabbitmq/rabbitmq-server/el/7/\$basearch
enabled=1
gpgcheck=1
gpgkey=https://packagecloud.io/rabbitmq/rabbitmq-server/gpgkey
sslverify=1
sslcacert=/etc/pki/tls/certs/ca-bundle.crt
metadata_expire=300

:wq

yum update
yum info rabbitmq-server
yum install erlang
yum install rabbitmq-server
```

# start and stop

```sh
systemctl status rabbitmq-server.service
systemctl start rabbitmq-server.service
systemctl stop rabbitmq-server.service
```

# 访问

- [访问地址](http://localhost:15672/#/) guest guest 默认密码访问(只能localhost访问)

# 添加新用户

```sh
rabbitmqctl status
rabbitmqctl add_user admin 12345678
rabbitmqctl set_user_tags admin administrator
rabbitmqctl set_permissions -p / admin '.*' '.*' '.*'
```

# 常用插件

- 插件安装目录 `/usr/lib/rabbitmq/lib/rabbitmq_server-x.x.x/plugins`
- [rabbitmq 插件地址](https://www.rabbitmq.com/community-plugins.html)
- [rabbitmq_delayed_message_exchange 插件地址](https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases)

```
rabbitmq-plugins list

rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins enable rabbitmq_mqtt
rabbitmq-plugins enable rabbitmq_web_mqtt
rabbitmq-plugins enable rabbitmq_web_stomp
rabbitmq-plugins enable rabbitmq_delayed_message_exchange
```

# RabbitMQ 交换机 浅析

## Direct Exchange

消息发送到此交换机上时，
交换机会将此消息发送到和消息中RoutingKey完全匹配的的队列，  
如果匹配了多个队列，则每个队列都会收到相同的消息

## Fanout exchange

这个交换机机，会将收到的消息一股脑全部发送给和自己绑定的队列，  
可以理解成少了一步匹配RoutingKey的步骤，所以它是所有交换机速度最快的，毕竟少干活了。

## Topic Exchange

这个交换机我和 Direct exchange 比较像，但它的功能比较高级，它可以对RoutingKey进行模糊匹配

匹配规则如下:

1. 将 routingkey 通过 "." 来分为多个部分
2. "*" 代表一个部分
3. "#" 代表一个或多个部分
