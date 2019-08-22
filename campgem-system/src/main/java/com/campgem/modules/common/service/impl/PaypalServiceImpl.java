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
import java.text.DecimalFormat;
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
	
	private static DecimalFormat df = new DecimalFormat("#.00");
	
	@Override
	public Payment createPayment(
			Double total,
			Details details,
			String currency,
			PaypalPaymentMethod method,
			PaypalPaymentIntent intent,
			String description,
			String cancelUrl,
			String processUrl) throws PayPalRESTException {
		Amount amount = new Amount();
		amount.setCurrency(currency);
		amount.setTotal(String.format("%.2f", total));
		amount.setDetails(details);
		
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
	public Payment createPayment(List<Orders> ordersList) throws PayPalRESTException {
		// 总运费
		double freight = 0;
		// 总税金
		double taxes = 0;
		// 总商品金额
		double subTotal = 0;
		for (Orders orders : ordersList) {
			if (orders.getFreightAmount() != null) {
				freight += orders.getFreightAmount().doubleValue();
			}
			taxes += orders.getTaxesAmount().doubleValue();
			subTotal += orders.getAmount().doubleValue();
		}
		
		Details details = new Details();
		details.setSubtotal(df.format(subTotal));
		details.setShipping(df.format(freight));
		details.setTax(df.format(taxes));
		
		double total = freight + taxes + subTotal;
		return createPayment(
				total,
				details,
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
