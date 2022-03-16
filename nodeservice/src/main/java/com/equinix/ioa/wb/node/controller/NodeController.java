package com.equinix.ioa.wb.node.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equinix.ioa.wb.node.model.Node;


@RestController
@RequestMapping("/node")
public class NodeController {

	private List<Node> nodelist = new ArrayList<>();


	//@Autowired
	//RabbitTemplate rabbitTemplate;


	public NodeController() {
		nodelist.add(new Node(1, "Node1"));
		nodelist.add(new Node(2, "Node2"));
	}

	/**
	 * This is a demo API
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json" )
	public Node getNode(@PathVariable int id) {
		//from node-service to account-service : Send Message Queue
		//rabbitTemplate.convertAndSend("spring-boot-exchange", "sample.queue", "RabbitMQ + Springboot = Success!");

		Optional<Node> node = nodelist.stream().filter(node1 -> node1.getId() == id).findFirst();
		return node.get();
	}


	@RequestMapping(value = "/nodeServiceTran", method = RequestMethod.GET, produces = "application/json")
	public String nodeServiceTran() throws Exception {
		//tran;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://nipton.theragenetex.localhost:3306/JP22?useSSL=false";
		String user = "root";
		String pwd = "1111";

		Class.forName(driver);

		try(Connection con = DriverManager.getConnection(url, user, pwd)){
			String sql = "UPDATE node_service SET node = node+1 where id = 1 ";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		return "SUCCESS NODE";
	}
}
