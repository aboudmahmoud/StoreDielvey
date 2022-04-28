package com.example.deliveryapp.FirebaseStore;

import static java.lang.Integer.parseInt;


import android.net.Uri;


import androidx.annotation.NonNull;


import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.Moudle.userInfo;

import com.google.android.gms.tasks.OnFailureListener;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import com.google.firebase.storage.FirebaseStorage;

import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

import java.util.TreeMap;

import javax.inject.Singleton;


public class StrogPage {
    @Singleton
   private FirebaseFirestore db;
    @Singleton
    private StorageReference storageRef;

    private ProducteInfo producte;
    TreeMap<String,Uri> tm = new TreeMap();
    public StrogPage()
    {
        db   = FirebaseFirestore.getInstance();
        storageRef = FirebaseStorage.getInstance().getReference().child("/images");
    }
    public void getImageSpeficImage(String Image,getingProducteInfo getingProducteInfo)
    {

    storageRef.child(Image).getDownloadUrl().addOnSuccessListener(uri -> {

        tm.put(Image,uri);
        getingProducteInfo.setImage(tm);
    }).addOnFailureListener(e -> getingProducteInfo.GetTheError(e.getMessage()));

}


 public void getTheProducte(getingProducteInfo getingProducteInfo)
 {
    ArrayList<ProducteInfo> producteInfos =new ArrayList<>();
    db.collection("ProducteInfo").get().addOnCompleteListener(task -> {
        if (task.isSuccessful()) {

            for (QueryDocumentSnapshot document : task.getResult()) {

                String ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention;
                ProducteName=""+document.get("producteName");
                ProductePrice=""+document.get("productePrice");
                ProudcteImageUri=""+document.get("proudcteImageUri");
                ProudcteCompanty=""+document.get("proudcteCompanty");
                ImageExtention=""+document.get("extentionImage");
                producteInfos.add(new ProducteInfo(ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention));
                getingProducteInfo.getingProducted(producteInfos);
                getImageSpeficImage(ProudcteImageUri,getingProducteInfo);
            }


        } else {

            // Toast.makeText(ProducteViewr.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
        }
    });
}


    public void getTheUserData(String Email,String Password,getTheData getData)
    {

        ArrayList<userInfo>  users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        int count =0;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = ""+document.get("fullName");
                            String email = ""+document.get("email");
                            String password = ""+document.get("password");
                            String phone = ""+document.get("phoneNumber");
                            String place = ""+document.get("place");
                            String ste= ""+ document.get("status");
                            int status=parseInt(ste);



                            if (Email.equals(document.get("email")))
                            {
                                if (Password.equals(document.get("password")))
                                {
                                    count=1;
                                    userInfo usera=new userInfo(name,email,password,place,phone,status);
                                    usera.setUIde(document.getId());
                                    users.add(usera);
                                    //   userdata.setValue(users);

                                    getData.useingData(usera,1);


                                }
                                else
                                {
                                    count=2;
                                    getData.useingData(null,2);
                                }

                            }


                        }
                        if(count==0)
                        {
                            getData.useingData(null,0);
                        }
                    } else {

                        getData.getError(task.getException().getMessage());
                    }
                });

    }

    public void CheckByEmail(String Email,EmailCheckInfrace emailCheckInfrace)
    {
        ArrayList<userInfo> users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        int count =0;
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            if (Email.equals(document.get("email")))
                            {
                                count=1;

                            }


                        }
                        if(count==0)
                        {

                            emailCheckInfrace.ChecKEmail(count);


                        }
                        else
                        {

                            emailCheckInfrace.ChecKEmail(count);
                        }
                    } else {

                        emailCheckInfrace.messgeError(task.getException().getMessage());

                    }
                });
    }

    public void InsertUserData(userInfo use,InsertStatus insertStatus)
    {
        db.collection("users")
                .add(use)
                .addOnSuccessListener(documentReference -> insertStatus.insetDataStatus(0))
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        insertStatus.getErrorMessge(e.getMessage());

                    }
                });

    }

    public void UploadImagee(String pn, String pp, String pc, String filename, String extention, Uri imageuri,AddingProducteStatus statusCondetion)
    { storageRef = FirebaseStorage.getInstance().getReference("images/" + filename + "." + extention);
        storageRef.putFile(imageuri).addOnSuccessListener(taskSnapshot -> {

            producte = new ProducteInfo(pn, pp, storageRef.getName(), pc, extention);
            statusCondetion.statusUplod(true);
            InsertProducteData(producte,statusCondetion);
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                statusCondetion.statusUplod(false);
                statusCondetion.ErroImageUplod(e.getMessage());

            }
        });
    }

    public void InsertProducteData( ProducteInfo producta,AddingProducteStatus stausAdd)
    {
        db.collection("ProducteInfo").add(producte).addOnSuccessListener(documentReference -> {

            //    Toast.makeText(AddPrdocte.this, R.string.succes, Toast.LENGTH_LONG).show();
            stausAdd.statusInsert(true);
        }).addOnFailureListener(e -> {
            stausAdd.statusInsert(false);
            stausAdd.ErroInsert(e.getMessage());

        });
    }


}
