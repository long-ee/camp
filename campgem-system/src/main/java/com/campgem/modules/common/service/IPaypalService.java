package com.campgem.modules.common.service;

import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.campgem.modules.trade.entity.Orders;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

public interface IPaypalService {
	Payment createPayment(
			Double total,
			String currency,
			PaypalPaymentMethod method,
			PaypalPaymentIntent intent,
			String description,
			String cancelUrl,
			String processUrl) throws PayPalRESTException;
	
	Payment createPayment(List<Orders> orders) throws PayPalRESTException;
	
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
