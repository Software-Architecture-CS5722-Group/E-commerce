package com.groupjn.orderservice.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email implements IEmail{

    private String title;
    private String recipients;
    private String message;

    @Override
    public void send() {

    }
}
