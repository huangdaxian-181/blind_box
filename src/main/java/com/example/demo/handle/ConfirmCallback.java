package com.example.demo.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 搬砖的码农
 * @date 2023/03/27
 * @email
 **/
@Slf4j
@Component
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData,boolean ack,String cause){
        if(!ack){
            log.error("消息发送异常! correlationData={} ,ack={}, cause={}", correlationData.getId(), ack, cause);
        }else{
            log.info("消息发送成功");
        }
    }
}
