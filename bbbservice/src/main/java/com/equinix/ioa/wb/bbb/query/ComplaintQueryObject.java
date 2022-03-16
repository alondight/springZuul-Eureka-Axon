package com.equinix.ioa.wb.bbb.query;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComplaintQueryObject {
	@Id
	private String complaintId;
	private String company;
	private String description;
}
