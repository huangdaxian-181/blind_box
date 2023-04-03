package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq配置类
 *
 * @author 搬砖的码农
 * @date 2023/03/25
 * @email
 **/
@Configuration
@Slf4j
public class RabbitmqConfig implements ApplicationContextAware {
    /**
     * 交换机名称
     */
    public static final String EXCHANGE_NAME = "box_exchange";

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "box_queue";

    /**
     * 创建交换机
     */
    @Bean("bootExchange")
    public Exchange getExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_NAME)
                //是否持久化，true即存到磁盘,false只在内存上
                .durable(true)
                .build();
    }

    /**
     * 创建队列
     */
    @Bean("bootQueue")
    public Queue getQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    /**
     * 交换机绑定队列
     */
    @Bean
    public Binding bindMessageQueue(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("#.message.#")
                .noargs();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //RabbitTemplate
        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        rabbitTemplate.setReturnCallback(((message, replyCode, replyText, exchange, routingKey) -> {
             Integer receivedDelay = message.getMessageProperties().getReceivedDelay();

             if (receivedDelay != null && receivedDelay > 0){
                 return;
             }

            log.error("消息发送到队列失败，响应码：{}, 失败原因：{}, 交换机: {}, 路由key：{}, 消息: {}",
                    replyCode, replyText, exchange, routingKey, message.toString());
        }));
    }


}
