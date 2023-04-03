package com.example.demo.controller;

import com.example.demo.config.RabbitmqConfig;
import com.example.demo.handle.ConfirmCallback;
import com.example.demo.handle.ReturnsCallback;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @author 搬砖的码农
 * @date 2023/02/27
 * @email
 **/
@Controller
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConfirmCallback confirmCallback;

    @Autowired
    private ReturnsCallback returnsCallback;

    @GetMapping("/test")
    @ResponseBody
    public void test() {
        // 全局唯一
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String message = "Hello world!";
        System.out.println(" [ 生产者 ] Sent ==> '" + message + "'");

        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnsCallback);
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "#.message.#", message, correlationData);

        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, "#.message.#", "你好", correlationData);
    }

}
