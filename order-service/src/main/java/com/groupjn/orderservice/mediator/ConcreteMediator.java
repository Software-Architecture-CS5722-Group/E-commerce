package com.groupjn.orderservice.mediator;

import java.util.HashMap;
import java.util.Map;

public class ConcreteMediator implements Mediator{

    private  final  String COMPONENT_A = "Component-A";
    private  final  String COMPONENT_B = "Component-B";
    private Map<String, Component> registerComponetMap = new HashMap<>();

    @Override
    public void notify(Component sender, String message) {
        String senderName = sender.getName();
        if(COMPONENT_A.equals(senderName)){
            reactOnA(message);
        } else if (COMPONENT_B.equals(senderName)){
            reactOnB(message);
        }
    }

    @Override
    public void register(Component component) {
        registerComponetMap.put(component.getName(),component);
    }

    private  void reactOnA(String message){
        System.out.println("Mediator is in action: ");
        registerComponetMap.get(COMPONENT_B).receive(message);
    }


    private  void reactOnB(String message){
        System.out.println("Mediator is in action: ");
        registerComponetMap.get(COMPONENT_A).receive(message);
    }
}
