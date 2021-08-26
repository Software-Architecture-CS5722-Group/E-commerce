package com.groupjn.productservice.controller;

import com.groupjn.productservice.api.command.AddProductCommand;
import com.groupjn.productservice.entity.ProductSummary;
import com.groupjn.productservice.query.GetProductQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.responsetypes.ResponseTypes;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("product")
public class ProductController {
    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    public ProductController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/addProduct")
    public void handle(@RequestBody ProductSummary summary){
        AddProductCommand cmd = new AddProductCommand(
                UUID.randomUUID().toString(),
                summary.getPrice(),
                summary.getStock(),
                summary.getDescription()
        );

        commandGateway.sendAndWait(cmd);
    }

    @GetMapping("/products")
    public CompletableFuture<List<ProductSummary>> getProducts(){
        return queryGateway.query(new GetProductQuery(), ResponseTypes.multipleInstancesOf(ProductSummary.class));
    }
}
