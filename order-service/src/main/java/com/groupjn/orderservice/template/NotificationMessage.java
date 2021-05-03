package com.groupjn.orderservice.template;

import lombok.Data;

@Data
public abstract class NotificationMessage {

    private String name;
    private String message;

    public NotificationMessage(String name,String message){
        this.name = name;
        this.message = message;
    }

    public  abstract String header();
    public abstract String message();
    public abstract String footer();

    public final String buildNotification(){
        return header()+" "+message()+" "+footer();
    }
}
