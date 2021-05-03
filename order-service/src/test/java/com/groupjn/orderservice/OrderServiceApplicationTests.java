package com.groupjn.orderservice;

import com.groupjn.orderservice.common.Payment;
import com.groupjn.orderservice.common.TransactionRequest;
import com.groupjn.orderservice.entity.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+"/order/";
	}


	@Test
	void contextLoads() {
	}

	@Test
	@org.junit.jupiter.api.Order(1)
	void bookOrderTest(){
		TransactionRequest tranasactionDAO = new TransactionRequest();
		Order order = new Order();
		Payment payment = new Payment();
		payment.setOrderId(3);
		payment.setPaymentStatus("Success");
		order.setPrice(34);
		order.setQuantity(3);
		order.setName("Test Order");
		tranasactionDAO.setOrder(order);
		tranasactionDAO.setPayment(payment);
		ResponseEntity<TransactionRequest> postResponse = restTemplate.postForEntity(getRootUrl() + "/bookOrder/", tranasactionDAO, TransactionRequest.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}



}
