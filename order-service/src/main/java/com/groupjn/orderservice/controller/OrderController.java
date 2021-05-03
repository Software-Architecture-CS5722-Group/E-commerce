package com.groupjn.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.groupjn.orderservice.builder.Email;
import com.groupjn.orderservice.builder.EmailBuilder;
import com.groupjn.orderservice.common.Payment;
import com.groupjn.orderservice.common.TransactionRequest;
import com.groupjn.orderservice.common.TransactionResponse;
import com.groupjn.orderservice.entity.Order;
import com.groupjn.orderservice.service.OrderService;
import com.groupjn.orderservice.template.UserNotificatonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) throws JsonProcessingException {

        String header = "User";
        String message = "Your order has been placed see below order details \n" +
                ""+transactionRequest.getOrder().toString();
        UserNotificatonMessage userNotificatonMessage = new UserNotificatonMessage(header,message);
        Email email = new EmailBuilder()
                .addRecipient("john@Doe.com")
                .setMainText(userNotificatonMessage.buildNotification())
                .setGreeting("Hi John!")
                .setClosing("Regards")
                .setTitle("Builder pattern resources")
                .build();
        email.send();
        return orderService.saveOrder(transactionRequest);
    }



}
