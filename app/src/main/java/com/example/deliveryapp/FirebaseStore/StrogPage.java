package com.example.deliveryapp.FirebaseStore;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.deliveryapp.Moudle.userInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;
import javax.inject.Singleton;


public class StrogPage {

    @Singleton
    StorageReference storageRef;

    public StrogPage()
    {
        storageRef = FirebaseStorage.getInstance().getReference().child("/images");
    }
    public void getImageSpeficImage(String Image, ImageListner imageListner)
    {

    storageRef.child(Image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
        @Override
        public void onSuccess(Uri uri) {

            imageListner.getImageUri(uri);
        }

    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            imageListner.errorMessge( e.getMessage());
        }
    });

}
}
