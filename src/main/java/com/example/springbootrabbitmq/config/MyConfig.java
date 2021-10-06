package com.example.springbootrabbitmq.config;

import com.example.springbootrabbitmq.utils.ProducerUtil;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Description：
 * @Author 陈长生
 * @Date 2021/9/27 15:16
 * @Version
 */
@Configuration
public class MyConfig {



    @Bean("x_exchange")
    public DirectExchange xDExchange() {
        return new DirectExchange("x_c");
    }

    @Bean("y_exchange")
    public DirectExchange yDExchange() {
        return new DirectExchange("y_c");
    }

    @Bean("QA_queue")
    public Queue qAQueue() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("x-max-length", 5);
//        stringObjectHashMap.put("x-message-ttl",100);
        stringObjectHashMap.put("x-dead-letter-exchange","y_c");
        stringObjectHashMap.put("x-dead-letter-routing-key", "y_c_key");
        return QueueBuilder
                .durable("qa_q")
                .withArguments(stringObjectHashMap).build();
    }

    @Bean("QB_queue")
    public Queue qBQueue() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("x-max-length", 5);
        stringObjectHashMap.put("x-message-ttl",200);

        stringObjectHashMap.put("x-dead-letter-exchange","y_c");
        stringObjectHashMap.put("x-dead-letter-routing-key", "y_c_key");
        return QueueBuilder
                .durable("qb_q")
                .withArguments(stringObjectHashMap).build();
    }


    @Bean("y_queue")
    public Queue yQueue() {
        return QueueBuilder
                .durable("y_q").build();
    }
    @Bean
    public Binding yBing(DirectExchange y_exchange,Queue y_queue) {
        return BindingBuilder.bind(y_queue).to(y_exchange).with("y_c_key");
    }

    @Bean
    public Binding qBBing(DirectExchange x_exchange,Queue QB_queue) {
        return BindingBuilder.bind(QB_queue).to(x_exchange).with("x_c_key2");
    }

    @Bean
    public Binding qABing(DirectExchange x_exchange, Queue QA_queue) {
        return BindingBuilder.bind(QA_queue).to(x_exchange).with("x_c_key1");
    }

    @Bean("cusEx")
    public CustomExchange getCustomEx() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("x-delayed-type","direct");
        return new CustomExchange("c_ex", "x-delayed-message",true,false,stringObjectHashMap);

    }

    @Bean
    public Binding cusBinding(CustomExchange cusEx,Queue y_queue) {

        return BindingBuilder.bind(y_queue).to(cusEx).with("cusKey").noargs();

    }




}
