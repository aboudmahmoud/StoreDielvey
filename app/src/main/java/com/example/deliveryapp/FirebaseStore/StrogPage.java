package com.example.deliveryapp.FirebaseStore;

import static java.lang.Integer.parseInt;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class StrogPage {
    FirebaseFirestore db;
    public static volatile   boolean st ;
ArrayList<userInfo> users;
Map<String, Object> List;


    public StrogPage(Context context) {
        db = FirebaseFirestore.getInstance();

    }


    public boolean IsInsrted(userInfo user)
    {
        boolean stau;

        insertuser(user);
        stau=st;
        return stau;
    }

    public  void insertuser(userInfo user) {
       // CountDownLatch done = new CountDownLatch(1);
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TGHP", "DocumentSnapshot added with ID: " + documentReference.getId());
                        System.out.println("Succses");

                     //   done.countDown();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TGHP", "Error adding document", e);
                        System.out.println("error " +e.getMessage());
                       st=false;
                    }
                });



    }

    public ArrayList<userInfo>getUsers( )
    {

        users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("", document.getId() + " => " + document.getData());
                               // List=  document.getData();
                         //      List.get( document.get("email"));

                                String name = ""+document.get("fullName");
                                String email = ""+document.get("email");
                                String password = ""+document.get("password");
                                String phone = ""+document.get("phoneNumber");
                                String place = ""+document.get("place");
                                String ste= ""+ document.get("status");
                                int status=parseInt(ste);


                                users.add(new userInfo(name,email,password,place,phone,status));
                                System.out.println( "email is "+users.get(0).getEmail());


                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        return users;

    }




    public int  GetSpecitUser(String Email,String Pass)
    {

        int statis=0;
        ArrayList<userInfo>userInfos = getUsers();

        for (int i=0 ; i<userInfos.size();i++)
        {
            if(Email.equals(userInfos.get(i).getEmail())) {

                if (Pass.equals(userInfos.get(i).getPassword()))
                {
                    statis=1;
                }
                else
                {
                    statis=2;
                }
            }

        }

return statis;
   }



}
