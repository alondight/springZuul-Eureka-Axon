package com.equinix.ioa.wb.account.interfaces;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "account-service")
public interface AccountServiceInterface {
	@GetMapping("/account/accountServiceTran")
	String getAccountTran();
}
