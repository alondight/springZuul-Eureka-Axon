package com.equinix.ioa.wb.account.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equinix.ioa.wb.account.interfaces.AccountServiceInterface;
import com.equinix.ioa.wb.account.interfaces.NodeServiceInterface;

@Component
public class RabbitMQClient {


	@Autowired
	NodeServiceInterface nodeService;

	@Autowired
	AccountServiceInterface accountService;

	@RabbitListener(queues = "sample.queue")
    public void receiveMessage(Message  message ) throws Exception{
        System.out.println(message.toString());
        System.out.println("--비동기--Start--");
        nodeService.getNodeTran();
        accountService.getAccountTran();
        System.out.println("--비동기--End--");
    }

}
