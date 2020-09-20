package com.example.faithfully;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.List;

public class FirestoreRepository {
    public static final String TAG = FirestoreRepository.class.getSimpleName();
    public static final FirebaseFirestore firestoreRepository = FirebaseFirestore.getInstance();

    public static final String HOST_ID_FIELD = "host_ID";
    public static final String POST_TIME_FIELD = "post_time";
    public static final String BOOKMARK_TIME_FIELD = "bookmark_time";
    public static final String IS_BOOKMARKED_FIELD = "bookmarked";

    public static final CollectionReference UsersCollRef = firestoreRepository.collection("Users");
    public static final CollectionReference EventCollRef = firestoreRepository.collection("Events");

    public static void addUser(String userID){
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

    public static void createEvent(String title, String description, String meeting_link, String host_name, String host_ID, int max_guest_num, Date post_time, Date event_time, List<String> target_religions){
        Events event = new Events(title, description, meeting_link, host_name, host_ID, max_guest_num, null, event_time, target_religions);

        EventCollRef.add(event)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.i(TAG, "Event created");
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
