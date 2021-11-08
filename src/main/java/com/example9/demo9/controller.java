package com.example9.demo9;

import java.util.Collections;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@RestController
public class controller {
    /*
    String key = "AIzaSyCHT7zgLMeuuoFjpVBfl4ooAIXNPyG_NN4";
    String firebase_login = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";
    String firebase_users = "https://firestore.googleapis.com/v1/projects/lab-9-30e21/databases/(default)/documents/users/";
    String email = "aydansheydayeva75@gmail.com";
    String password = "password12345!"*/

    String key = System.getenv("FIREBASE_API_KEY");
    String firebase_login = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=";
    String firebase_users = "https://firestore.googleapis.com/v1/projects/lab-9-30e21/databases/(default)/documents/users/";
    String email = System.getenv("FIREBASE_EMAIL");
    String password = System.getenv("FIREBASE_PASSWORD");

    @GetMapping("/")
    public boolean endToend(){
        // sign in
        System.out.println(key);
        System.out.println(firebase_login+key);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        JSONObject creds = new JSONObject();
        creds.put("returnSecureToken", true);
        creds.put("email",email);
        creds.put("password", password);
        HttpEntity<String> entity = new HttpEntity<String>(creds.toString(), headers);
        ResponseEntity<String> out = restTemplate.exchange(firebase_login+key, HttpMethod.POST, entity, String.class);
        
        // check detailes
        
        RestTemplate restTemplate2 = new RestTemplate();
        HttpHeaders headers2 = new HttpHeaders();
        JSONObject resBody = new JSONObject(out.getBody());
        String idToken = (String) resBody.get("idToken");
        String localId = (String) resBody.get("localId");
        headers2.put("Authorization", Collections.singletonList("Bearer ".concat(idToken)));
        HttpEntity<String> entity2 = new HttpEntity<String>(headers2);
        ResponseEntity<String> out2 = restTemplate2.exchange(firebase_users+localId, HttpMethod.GET, entity2, String.class);

        // for test
        String toTest = "{\"phone\":{\"stringValue\":\"5555555\"},\"surname\":{\"stringValue\":\"adminovich\"},\"name\":{\"stringValue\":\"admin\"}}";
        JSONObject resBody2 = new JSONObject(out2.getBody());
        String fields = (String) resBody2.get("fields").toString();

        return fields.equals(toTest);
    }
}
