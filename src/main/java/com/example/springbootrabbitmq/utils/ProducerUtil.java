package com.example.springbootrabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @Description：
 * @Author 陈长生
 * @Date 2021/9/25 16:31
 * @Version
 */
public class ProducerUtil {
    public static final  String ROUTING_KEY1 = "TestQueue1";
    public static final  String ROUTING_KEY2 = "TestQueue2";
    public static final String DEAD_QUEUE = "dead_queue";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DEAD_EXCHANGE = "dead_exchange";
    public static final String NORMAL_EXCHANGE ="normal_exchange";



    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.147.137");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("fengzhixin0");
        Connection connection = connectionFactory.newConnection();
        return connection.createChannel();
    }
}
