package com.campgem.modules.order.service;

import com.campgem.modules.order.entity.Orders;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.util.List;

public interface IPaymentService {
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
	
	String pay(List<Orders> orders);
	
	/**
	 * Braintree信用卡支付
	 */
	String payWithCreditCard(List<Orders> ordersList, String nonce);
	
	String getToken();
}
