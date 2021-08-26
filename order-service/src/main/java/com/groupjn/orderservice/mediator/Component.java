package com.groupjn.orderservice.mediator;

public abstract class Component {

    private String name;

    protected  Mediator mediator;

    public Component(String name, Mediator m){
        mediator = m;
        this.name =name;
    }

    public  abstract void send();

    public abstract void receive(String message);

    public  String getName(){
        return this.name;
    }
}
