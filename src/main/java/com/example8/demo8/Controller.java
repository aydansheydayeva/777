package com.example8.demo8;

import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

    @GetMapping("/")
    public boolean getrepos() throws JsonMappingException, JsonProcessingException{
        String url = "https://api.nytimes.com/svc/books/v3/lists.json?list=Combined Print and E-Book Nonfiction&api-key=J6aCv4sITAgi9M3QWVAAxzmpI0s3yXcO";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> out = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(out.getBody());
        JSONArray jsonArray = jsonObject.getJSONArray("results");

        Integer num_Res = jsonObject.getInt("num_results");

        Set<Number> ranks = new TreeSet<Number>();
        Set<String> names = new TreeSet<String>();

        for(int i=0; i<jsonArray.length(); i++) {

            JSONObject obj = (JSONObject) jsonArray.get(i);
            System.out.println(obj.getInt("rank"));

            JSONArray obj2 = (JSONArray) obj.get("book_details");
            JSONObject bookd = (JSONObject) obj2.get(0);
            String booktitle = bookd.getString("title");
            
            if (booktitle != ""){
                names.add(booktitle);
            }
            ranks.add(obj.getInt("rank"));
        }

        System.out.println(ranks);
        System.out.println(names);
        return ranks.size()==num_Res && names.size()==num_Res;
    }

}