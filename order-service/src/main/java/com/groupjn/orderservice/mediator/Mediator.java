package com.groupjn.orderservice.mediator;

public interface Mediator {
    void notify(Component sender, String message);

    void register(Component component);
}
