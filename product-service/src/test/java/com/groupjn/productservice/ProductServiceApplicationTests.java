package com.groupjn.productservice;

import com.groupjn.productservice.entity.ProductSummary;
import com.groupjn.productservice.model.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+"/product/";
	}

	@Test
	void contextLoads() {
	}

	@Test
	@Order(1)
	void createProductTest(){
		// @Todo create product test
		ProductSummary product = new ProductSummary();
		product.setDescription("Test Product Description");
		product.setPrice(2.3);
		product.setStock(23);
		ResponseEntity<ProductSummary> postResponse = restTemplate.postForEntity(getRootUrl() + "/addProduct/", product, ProductSummary.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	@Order(2)
	void viewProductTest(){
		// @Todo view product Test
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "products/",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}
}
