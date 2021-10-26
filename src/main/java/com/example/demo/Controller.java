package com.example.demo;

import java.lang.reflect.Array;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @GetMapping("/repos")
    public HttpStatus getrepos() throws JsonMappingException, JsonProcessingException{
        String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return out.getStatusCode();
    
    }

    @GetMapping("/branches")
    public HttpStatus getbranches() throws JsonMappingException, JsonProcessingException{
        String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return out.getStatusCode();
    
    }

    @GetMapping("/commits")
    public HttpStatus getcommits() throws JsonMappingException, JsonProcessingException{
        String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos/1/branches/1/commits";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return out.getStatusCode();
    
    }

    private HttpHeaders HttpHeaders() {
        return null;
    }
}
