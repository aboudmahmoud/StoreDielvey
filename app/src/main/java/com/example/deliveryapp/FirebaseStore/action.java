package com.example.deliveryapp.FirebaseStore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;

public interface action
{
        //this is for callbacks
        void onSuccessed(DocumentReference documentReference);
        void onStart();
        void onFailure();

}
