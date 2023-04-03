package com.example.demo.handle;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 接收用户传递的发布数据
 *
 * @author 搬砖的码农
 * @date 2023/03/25
 * @email
 **/
@Component
@Slf4j
public class BoxHandle {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 监听队列
     */
    @RabbitListener(queues = "box_queue")
    public void listenMessage(Message message, Channel channel) throws IOException,InterruptedException {

            String str = new String(message.getBody(), StandardCharsets.UTF_8);

             System.out.println("消费者1:"+str);
    }

    @RabbitListener(queues = "box_queue")
    @RabbitHandler
    public void listenMessage2(Message message, Channel channel) throws InterruptedException {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

        correlationData.getFuture().addCallback(result -> {
           if (result.isAck()){
               log.info("消息成功投递到交换机！消息ID: {}", correlationData.getId());
           }else{
               log.error("消息投递到交换机失败！消息ID：{}，原因：{}", correlationData.getId(), result.getReason());
           }
        },ex -> {
            log.error("消息发送异常, ID:{}, 原因{}", correlationData.getId(), ex.getMessage());
        });

        rabbitTemplate.convertAndSend("topicExchange", "topic.dog", "路由模式测试-dog", correlationData);
        // 程序休眠两秒等待回调
        Thread.sleep(2000);
    }


}
