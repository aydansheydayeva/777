package com.example7.demo7.service;

import java.util.concurrent.ExecutionException;

import com.example7.demo7.entity.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.WriteResult;

import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private static final String COLLECTION_NAME = "users";

    public static String saveUser(User user) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(user.getUsername()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUser(String username) throws InterruptedException, ExecutionException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(username);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        User user = null;
        if(document.exists()){
            user = document.toObject(User.class);
            return user;
        }
        else{
            return null;
        }
    }
    
}