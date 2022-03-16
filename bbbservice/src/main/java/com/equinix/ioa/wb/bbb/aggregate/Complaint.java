package com.equinix.ioa.wb.bbb.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import com.equinix.ioa.wb.bbb.command.FileComplaintCommand;
import com.equinix.ioa.wb.bbb.events.ComplaintFiledEvent;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class Complaint{
	@AggregateIdentifier
	private String compalintId;
	
	@CommandHandler
	public Complaint(FileComplaintCommand cmd) {
		Assert.hasLength(cmd.getCompany(), "CompanyId must be present !");
		AggregateLifecycle.apply(new ComplaintFiledEvent(cmd.getId(), cmd.getCompany(), cmd.getDescription()));
	}
	
	@EventSourcingHandler
	public void on(ComplaintFiledEvent event) {
		this.compalintId = event.getId();
	}
}
