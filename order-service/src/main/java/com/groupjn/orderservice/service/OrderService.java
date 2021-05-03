package com.groupjn.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupjn.orderservice.common.Payment;
import com.groupjn.orderservice.common.TransactionRequest;
import com.groupjn.orderservice.common.TransactionResponse;
import com.groupjn.orderservice.entity.Order;
import com.groupjn.orderservice.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
@RefreshScope
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;
    private Logger log = LoggerFactory.getLogger(OrderService.class);


    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
        String response = "";
        Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        order= orderRepository.save(order);
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        log.info("OrderService request: {}",new ObjectMapper().writeValueAsString(transactionRequest));

        log.info(payment.toString());

        //rest call
        Payment paymentResponse = restTemplate.postForObject("http://API-GATEWAY-SERVICE/payment/doPayment",payment,Payment.class);
        log.info("PaymentService response from OrderService Rest call: {}",new ObjectMapper().writeValueAsString(paymentResponse));
        response = paymentResponse.getPaymentStatus().equals("success") ? "payment processing successful and order placed" : "There was a failure in payment api order added to cart";

        return new TransactionResponse(
                order,
                paymentResponse.getAmount(),
                paymentResponse.getTransactionId(),
                response
                );
        }
}
