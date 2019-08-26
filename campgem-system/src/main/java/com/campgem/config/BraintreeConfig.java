package com.campgem.config;

import com.braintreegateway.BraintreeGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BraintreeConfig {
	@Value("${jeecg.braintree.bt-environment}")
	private String environment;
	@Value("${jeecg.braintree.bt-merchant-id}")
	private String merchantId;
	@Value("${jeecg.braintree.bt-public-key}")
	private String publicKey;
	@Value("${jeecg.braintree.bt-private-key}")
	private String privateKey;
	
	private BraintreeGateway gateway;
	
	@PostConstruct
	public void init() {
		gateway = new BraintreeGateway(environment, merchantId, publicKey, privateKey);
	}
	
	public BraintreeGateway getGateway() {
		return gateway;
	}
}
