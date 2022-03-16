package com.equinix.ioa.wb.bbb.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintFiledEvent {
	private String id;
	private String company;
	private String description;
}

