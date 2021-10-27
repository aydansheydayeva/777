package com.example2.demo2;

import java.net.http.HttpHeaders;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @GetMapping("/repos")
    public boolean getrepos() throws JsonMappingException, JsonProcessingException{
        String url = "https://60a21d3f745cd70017576092.mockapi.io/api/v1/repos";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);


        JSONArray objects = new JSONArray(out.getBody());

        Set<String> ids = new TreeSet<String>();
        Set<String> names = new TreeSet<String>();

        for (int i=0; i < objects.length(); i++) {
            JSONObject obj = (JSONObject) objects.get(i);
            ids.add(obj.getString("id"));
            names.add(obj.getString("name"));
        }

        return objects.length()==ids.size() && objects.length()==names.size();
    
    }

    private HttpHeaders HttpHeaders() {
        return null;
    }
}
