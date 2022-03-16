package com.equinix.ioa.wb.bbb.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.equinix.ioa.wb.bbb.command.FileComplaintCommand;
import com.equinix.ioa.wb.bbb.entity.ComplaintQueryObjectRepository;
import com.equinix.ioa.wb.bbb.query.ComplaintQueryObject;


@RestController
public class ComplaintController {


	@Autowired
	private ComplaintQueryObjectRepository repository;
	
	// Sends the given command and returns a CompletableFuture immediately, without waiting for the command to execute. 
	// The caller will therefore not receive any immediate feedback on the command'sexecution. Instead hooks can be 
	// added to the returned CompletableFuture to react on success or failure of command execution. 
	@Autowired
	private CommandGateway commandGateway;


	@PostMapping
	public CompletableFuture<String> fileComplaint(@RequestBody Map<String, String> request) {
		String id = UUID.randomUUID().toString();
		//return commandGateway.send(new FileComplaintCommand(id, request.get("company"), request.get("description")));
		return commandGateway.sendAndWait(new FileComplaintCommand(id, request.get("company"), request.get("description")));
	}


	@GetMapping
	public List<ComplaintQueryObject> findAll() {
		return repository.findAll();
	}


	@GetMapping("/{id}")
	public ComplaintQueryObject findById(@PathVariable String id) {
		return repository.findById(id).orElse(null);
	}
}