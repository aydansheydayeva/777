package com.example7.demo7;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.example7.demo7.entity.User;
import com.google.api.client.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class Demo7ApplicationTests {

	@Test
    public void testPOST(){
        String url = "http://localhost:8080/api/users";
        RestTemplate restTemplate = new RestTemplate();

		User usr = new User("aydan", "aydan1234567!!!");
        HttpEntity<User> newInsertUser = new HttpEntity<User>(usr);

        restTemplate.postForObject(url, newInsertUser, Void.class);

		System.out.println("Request Successful");


		String url2 = "http://localhost:8080/api/users/aydan";
		HttpHeaders headers2 = HttpHeaders();
		HttpEntity<Object> entity2 = new HttpEntity<Object>(headers2);
		ResponseEntity<String> out2 = restTemplate.exchange(url2, HttpMethod.GET, entity2, String.class);
	
		assertEquals(out2.getStatusCode(), HttpStatus.OK);   
    }

	private HttpHeaders HttpHeaders() {
		return null;
	}

}
