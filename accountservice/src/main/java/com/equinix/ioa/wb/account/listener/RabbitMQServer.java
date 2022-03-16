package com.equinix.ioa.wb.account.listener;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQServer {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
    public void sendMessage( ) throws Exception{
    	rabbitTemplate.convertAndSend("spring-boot-exchange", "sample.queue", "RabbitMQ + Springboot = Success!");
    }
}
