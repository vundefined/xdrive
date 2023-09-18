package com.wypaperplane.drivewxmini.config;

import com.wypaperplane.syscore.properties.RabbitmqProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class RabbitmqConfig {

    private final Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Bean
    public ConnectionFactory connectionFactory(RabbitmqProperties rabbitmqProperties) {
        com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setPort(rabbitmqProperties.getPort());
        connectionFactory.setVirtualHost(rabbitmqProperties.getVirtualHost());
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        ConnectionFactory factory = new CachingConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public Queue queueAppointmentDrive() {
        return new Queue("appointment.drive.queue", false, false, false, null);
    }

    @Bean
    public Exchange exchangeAppointmentDrive() {
        return new DirectExchange("appointment.drive.exchange", false, false);
    }

    @Bean
    public Binding bindingAppointmentDrive(Queue queueAppointmentDrive, Exchange exchangeAppointmentDrive) {
        return BindingBuilder.bind(queueAppointmentDrive).to(exchangeAppointmentDrive).with("appointment.drive.routingkey").noargs();
    }

    @Bean
    public Queue delayQueueProfitsharingWxpay() {
        return new Queue("profitsharing.wxpay.queue", true, false, false, null);
    }

    @Bean
    public CustomExchange delayExchangeProfitsharingWxpay() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("profitsharing.wxpay.exchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingDelayProfitsharingWxpay(Queue delayQueueProfitsharingWxpay, CustomExchange delayExchangeProfitsharingWxpay) {
        return BindingBuilder.bind(delayQueueProfitsharingWxpay).to(delayExchangeProfitsharingWxpay).with("profitsharing.wxpay.routingkey").noargs();
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // factory.setPrefetchCount(1); 设置每个消费者获取消息的数量
        // factory.setConcurrentConsumers(1); 设置消费者线程池大小
        return factory;
    }
}
