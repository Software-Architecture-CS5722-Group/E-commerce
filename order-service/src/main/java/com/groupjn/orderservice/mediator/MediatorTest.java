package com.groupjn.orderservice.mediator;

public class MediatorTest {

    public void testMediator(){
        Mediator mediator = new ConcreteMediator();

        Component comA = new ComponentA(mediator);
        Component comB = new ComponentB(mediator);

        mediator.register(comA);
        mediator.register(comB);

        comA.send();

        System.out.println();

        comB.send();
    }
}
