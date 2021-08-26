package com.groupjn.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {

//	@Autowired
//	private Environment env;

//	@Bean
//	@LoadBalanced
//	public RestTemplate collectCentRestTemplate(RestTemplateBuilder builder) {
//		return builder.rootUri(env.getProperty("Gate-way-url"))
//				.additionalInterceptors((ClientHttpRequestInterceptor) (request, body, execution) -> {
//					request.getHeaders().add("Authorization", "Bearer "+env.getProperty("bearer-token"));
//					return execution.execute(request, body);
//				}).build();
//	}


	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
