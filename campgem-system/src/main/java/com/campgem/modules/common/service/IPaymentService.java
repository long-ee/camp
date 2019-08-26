package com.campgem.modules.common.service;

import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.campgem.modules.trade.entity.Orders;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

public interface IPaymentService {
	Payment createPayment(
			Double total,
			Details details,
			String currency,
			PaypalPaymentMethod method,
			PaypalPaymentIntent intent,
			String description,
			String cancelUrl,
			String processUrl) throws PayPalRESTException;
	
	Payment createPayment(List<Orders> ordersList) throws PayPalRESTException;
	
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
	
	String pay(List<Orders> orders);
	
	/**
	 * Braintree信用卡支付
	 */
	String payWithCreditCard(String nonce);
}
