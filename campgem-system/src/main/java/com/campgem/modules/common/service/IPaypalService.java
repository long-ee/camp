package com.campgem.modules.common.service;

import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

public interface IPaypalService {
	public Payment createPayment(
			Double total,
			String currency,
			PaypalPaymentMethod method,
			PaypalPaymentIntent intent,
			String description,
			String cancelUrl,
			String returnUrl) throws PayPalRESTException;
	
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
