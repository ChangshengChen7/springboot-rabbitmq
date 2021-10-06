package com.example.springbootrabbitmq.controller;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description：
 * @Author 陈长生
 * @Date 2021/9/27 16:42
 * @Version
 */
@Slf4j
@RestController
public class CousumersController {

    @RabbitListener(queues = "y_q")
    public void consume(Message ms, String msg,Channel ch) throws IOException {
        System.out.println("2222222222222222222222");
        log.info("当前信息是：{}--------------{}",msg);
        ch.basicAck(ms.getMessageProperties().getDeliveryTag(),false);

    }
}
