package com.example.faithfully;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreRepository {
    public static final String TAG = FirestoreRepository.class.getSimpleName();
    public static final FirebaseFirestore firestoreRepository = FirebaseFirestore.getInstance();

    public static final CollectionReference UsersCollRef = firestoreRepository.collection("Users");

    public void addUser(String userID){
        Users user = new Users(userID);
        UsersCollRef.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "User added");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, e.toString());
                    }
                });
    }


}
