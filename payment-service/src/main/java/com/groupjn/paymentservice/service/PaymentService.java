package com.groupjn.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupjn.paymentservice.entity.Payment;
import com.groupjn.paymentservice.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    private Logger log = LoggerFactory.getLogger(PaymentService.class);

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("PaymentService request : {}", new ObjectMapper().writeValueAsString(payment));
        return paymentRepository.save(payment);
    }


    public String paymentProcessing(){
        //api should be 3rd party payment gateway
        return new Random().nextBoolean() ? "success" : "failed";
    }

    public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
        Payment payment = paymentRepository.findByOrderId(orderId);
        log.info("PaymentService findPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
