package com.equinix.ioa.wb.account.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "node-service")
public interface NodeServiceInterface {
	@GetMapping("/node/{nodeId}")
	String getNode(@PathVariable("nodeId") int nodeId);

	@GetMapping("/node/nodeServiceTran")
	String getNodeTran();

}
