package com.groupjn.apigateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallBack")
    public Mono<String> orderServiceFallBack(){
        return Mono.just("Order Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/paymentFallBack")
    public Mono<String> paymentServiceFallBack(){
        return Mono.just("Payment Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/authenticationFallBack")
    public Mono<String> authenticationServiceFallBack(){
        return Mono.just("Authentication Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/cartFallBack")
    public Mono<String> cartServiceFallBack(){
        return Mono.just("Cart Service is taking too long to respond" +
                " or is down, Please try again later");
    }


    @RequestMapping("/productFallBack")
    public Mono<String> productServiceFallBack(){
        return Mono.just("Product Service is taking too long to respond" +
                " or is down, Please try again later");
    }



}
