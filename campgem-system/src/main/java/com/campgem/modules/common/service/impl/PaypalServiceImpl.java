package com.campgem.modules.common.service.impl;

import com.campgem.common.exception.JeecgBootException;
import com.campgem.config.PaypalConfig;
import com.campgem.config.paypal.PaypalPaymentIntent;
import com.campgem.config.paypal.PaypalPaymentMethod;
import com.campgem.modules.common.service.IPaypalService;
import com.campgem.modules.trade.entity.Orders;
import com.campgem.modules.trade.service.IOrderService;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalServiceImpl implements IPaypalService {
	@Resource
	private APIContext apiContext;
	@Resource
	private PaypalConfig paypalConfig;
	@Resource
	private IOrderService orderService;
	
	@Override
	public Payment createPayment(
			Double total,
			String currency,
			PaypalPaymentMethod method,
			PaypalPaymentIntent intent,
			String description,
			String cancelUrl,
			String processUrl) throws PayPalRESTException {
		Amount amount = new Amount();
		amount.setCurrency(currency);
		amount.setTotal(String.format("%.2f", total));
		
		Transaction transaction = new Transaction();
		transaction.setDescription(description);
		transaction.setAmount(amount);
		
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		
		Payer payer = new Payer();
		payer.setPaymentMethod(method.toString());
		
		Payment payment = new Payment();
		payment.setIntent(intent.toString());
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(processUrl);
		payment.setRedirectUrls(redirectUrls);
		
		return payment.create(apiContext);
	}
	
	@Override
	public Payment createPayment(List<Orders> orders) throws PayPalRESTException {
		return createPayment(
				12.34,
				"USD",
				PaypalPaymentMethod.paypal,
				PaypalPaymentIntent.sale,
				"this is description",
				paypalConfig.getCancelUrl(),
				paypalConfig.getProcessUrl()
		);
	}
	
	@Override
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecute);
	}
	
	@Override
	public String pay(List<Orders> orders) {
		Payment payment = null;
		try {
			payment = createPayment(orders);
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if (payment == null) {
			throw new JeecgBootException("订单支付失败");
		}
		
		// 更新payID
		List<String> orderIds = new ArrayList<>();
		for (Orders o : orders) {
			orderIds.add(o.getId());
		}
		orderService.updatePayId(payment.getId(), orderIds);
		
		String url = null;
		for(Links links : payment.getLinks()){
			if(links.getRel().equals("approval_url")){
				url = links.getHref();
				break;
			}
		}
		
		if (url == null) {
			throw new JeecgBootException("订单支付失败");
		}
		return url;
	}
}
