package com.example.springbootrabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description：
 * @Author 陈长生
 * @Date 2021/9/29 10:18
 * @Version
 */
@Configuration
public class SecondConfig {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostConstruct
    public void setConBack() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    System.out.println("应答成功-----" + (correlationData == null ? "" : correlationData.getId()));
                }else {
                    System.out.println("应答失败----原因："+cause);
                }
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returned) {
                System.out.println("这是returns触发了");
            }
        });
    }

//    @PostConstruct
//    public void sst() {
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                System.out.println("未投入合适队列的消息信息为："+new String(message.getBody()));
//            }
//        });
//    }

}
