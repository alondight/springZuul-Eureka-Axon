package com.equinix.ioa.wb.account.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equinix.ioa.wb.account.interfaces.NodeServiceInterface;
import com.equinix.ioa.wb.account.listener.RabbitMQServer;
import com.equinix.ioa.wb.account.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	NodeServiceInterface nodeService;

	//@Autowired protected RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMQServer queueSender;


	User user = null;

	public AccountController() {
		user = new User(100, "test@equinix.com", "Test");
	}

	/**
	 * This is a demo API
	 * @return
	 */
    /*
    Hystrix Command 설정
    commandKey 별로 Circuit Breaker 생성
    fallbackMethod : 실패시 해당 메소드 실행
     */
	@HystrixCommand(fallbackMethod = "reliable")
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces ="application/json")
	public User login() {
		randomlySleep();
		user.setNode(nodeService.getNode(2));
		return user;
	}

    @RequestMapping(value = "/send" , method = RequestMethod.GET)
    public String send() throws Exception {
    	queueSender.sendMessage();
    	return "OK";
    }

	@RequestMapping(value = "/accountServiceTran", method = RequestMethod.GET, produces ="application/json")
	public String accountServiceTran() throws Exception {
		//tran;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://nipton.theragenetex.localhost:3306/JP22?useSSL=false";
		String user = "root";
		String pwd = "1111";
		//randomlySleep();
		Class.forName(driver);

		try(Connection con = DriverManager.getConnection(url, user, pwd)){
			String sql = "INSERT INTO account_service(account) values('AA') ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		return "SUCCESS ACCOUNT";
	}

	
    private void randomlySleep() {
        int random = (int) (Math.random() * 10);
        System.out.println(random);
        if(random % 2 == 0) {
            try {
                Thread.sleep(3000);
                throw new RuntimeException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public User reliable(){
        return new User(100, "Fallback", "Success Fallback");
    }    
}
