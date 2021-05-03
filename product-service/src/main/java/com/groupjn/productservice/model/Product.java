package com.groupjn.productservice.model;

import com.groupjn.productservice.api.command.AddProductCommand;
import com.groupjn.productservice.api.command.UpdateStockCommand;
import com.groupjn.productservice.api.event.ProductAddedEvent;
import com.groupjn.productservice.api.event.StockUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class Product {
    @AggregateIdentifier
    private String id;
    private Double price;
    private Integer stock;
    private String description;

    public Product() {
    }

    @CommandHandler
    public Product(AddProductCommand cmd){
        apply( new ProductAddedEvent(
                        cmd.getId(),
                        cmd.getPrice(),
                        cmd.getStock(),
                        cmd.getDescription()
                )
        );
    }

    @CommandHandler
    public void updateStock (UpdateStockCommand cmd){
        if(this.stock >= cmd.getStock()){
            apply(new StockUpdatedEvent(cmd.getId(), cmd.getStock()));
        }
        else {
            throw new RuntimeException("Out of Stock!");
        }
    }


    @EventSourcingHandler
    public void on(StockUpdatedEvent evt){
        id = evt.getId();
        stock = stock - evt.getStock();
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent evt){
        id = evt.getId();
        price = evt.getPrice();
        stock = evt.getStock();
        description = evt.getDescription();
    }
}
