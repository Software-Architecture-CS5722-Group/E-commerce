package com.softwarearchitecture.user.service;

import com.softwarearchitecture.user.service.entity.User;
import com.softwarearchitecture.user.service.factory.UserType;
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

import java.sql.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port+"/user/";
	}


	@Test

	void contextLoads() {
	}


	@Test
	@Order(1)
	void createUserTest(){
		// @Todo Add Create user test
		User user = new User();
		user.setUserType(UserType.ADMIN);
		user.setAddress("Test Address");
		user.setFirstName("test First name");
		user.setLastName("Test Last Name");
		user.setPhoneNumber("084637733");
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/createUser/", user, User.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	@Order(2)
	void viewUserTest() {
		// @Todo View user test
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "allUser/",
				HttpMethod.GET, entity, String.class);
		assertNotNull(response.getBody());
	}

}
