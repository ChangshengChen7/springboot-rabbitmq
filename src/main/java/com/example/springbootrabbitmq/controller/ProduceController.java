package com.example.springbootrabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author 陈长生
 * @Date 2021/9/27 17:11
 * @Version
 */
@RestController
public class ProduceController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sd/{msg}")
    public void getMsg(@PathVariable String msg) {
        System.out.println("111111111111111111111111");
        rabbitTemplate.convertAndSend("c_ex","cusKey111","这是第一条消息：+"+msg);
//        rabbitTemplate.convertAndSend("c_ex","cusKey","这是第一条消息：+"+msg,message -> {
//                    message.getMessageProperties().setDelay(5000);
//                    return message;
//                }
//        );

//        rabbitTemplate.convertAndSend("x_c","x_c_key2","这是第二条消息：+"+msg);
    }
}
