package com.equinix.ioa.wb.bbb.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equinix.ioa.wb.bbb.entity.ComplaintQueryObjectRepository;
import com.equinix.ioa.wb.bbb.events.ComplaintFiledEvent;


@Component
public class ComplaintQueryObjectUpdater {
	@Autowired
	private ComplaintQueryObjectRepository repository;


	@EventHandler
	public void on(ComplaintFiledEvent event) {
		repository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
	}
}
