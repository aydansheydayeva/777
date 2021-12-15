package com.example12.demo12;

import com.nimbusds.srp6.SRP6CryptoParams;
import com.nimbusds.srp6.SRP6Exception;
import com.nimbusds.srp6.SRP6ServerSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class Service {
    static String salt = "467567547654746745676710";
    static String verifier = "10196210648664296105080354052108408363451249495614489967630498601710725970463181286999104796125698822391337983180187663449469015581275512841630913422540136";
    public static SRP6CryptoParams config;
    public static SRP6ServerSession serverSession;

    @PostMapping("/new_session")
    public String new_session(@RequestBody String userID) {
        config = SRP6CryptoParams.getInstance();
        serverSession = new SRP6ServerSession(config);
        BigInteger saltS = new BigInteger(salt);
        BigInteger verifierV = new BigInteger(verifier);
        String serverPublicValue_B = serverSession.step1(userID, saltS, verifierV).toString();
        return serverPublicValue_B;
    }

    @PostMapping("/compute_values")
    public String compute_values(@RequestBody GetValue getValue) {
        try{
            return serverSession.step2(getValue.A, getValue.M1).toString();
        } catch (SRP6Exception e) {
            return "";
        }

    }
}