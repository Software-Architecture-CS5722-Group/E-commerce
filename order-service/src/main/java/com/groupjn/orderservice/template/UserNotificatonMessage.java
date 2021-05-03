package com.groupjn.orderservice.template;

public class UserNotificatonMessage extends NotificationMessage{

    public UserNotificatonMessage(String name, String message){
        super(name,message);
    }

    @Override
    public String header() {
        return "Dear "+this.getName()+", \n";
    }

    @Override
    public String message() {
        return this.getMessage()+"\n";
    }

    @Override
    public String footer() {
        return "Thank you!!!";
    }
}
