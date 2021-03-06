package com.campgem.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaypalConfig {
	@Value("${jeecg.paypal.client.app}")
	private String clientId;
	@Value("${jeecg.paypal.client.secret}")
	private String clientSecret;
	@Value("${jeecg.paypal.mode}")
	private String mode;
	@Value("${jeecg.paypal.redirect.cancel}")
	private String cancelUrl;
	@Value("${jeecg.paypal.redirect.process}")
	private String processUrl;
	
	@Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}
	
	@Bean
	public OAuthTokenCredential authTokenCredential(){
		return new OAuthTokenCredential(clientId, clientSecret, paypalSdkConfig());
	}
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException{
		APIContext apiContext = new APIContext(clientId, clientSecret, mode);
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}
	
	public String getProcessUrl() {
		return processUrl;
	}
	
	public String getCancelUrl() {
		return cancelUrl;
	}
}
