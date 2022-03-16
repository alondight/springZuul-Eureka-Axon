package com.equinix.ioa.wb.bbb.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileComplaintCommand {
	@TargetAggregateIdentifier
	private String id;
	private String company;
	private String description;
}
