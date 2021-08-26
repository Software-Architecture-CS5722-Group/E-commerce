package com.groupjn.orderservice.common;

import com.groupjn.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Order order;
    private Payment payment;
}
