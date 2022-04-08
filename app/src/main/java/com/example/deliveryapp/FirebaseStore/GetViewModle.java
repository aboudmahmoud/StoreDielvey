package com.example.deliveryapp.FirebaseStore;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.deliveryapp.FirebaseStore.getTheData;
import com.example.deliveryapp.Login.LoginPage;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.Adapters.ProduteAdapter;
import com.example.deliveryapp.ProducteControl.AddPrdocte;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.R;
import com.example.deliveryapp.Repsotry.Respostry;
import com.example.deliveryapp.Signin.SignIN;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Observable;

import javax.inject.Inject;
import javax.inject.Singleton;

public class GetViewModle extends ViewModel {
    MutableLiveData<ArrayList<userInfo>> userdata = new MutableLiveData<>();
    Respostry respostry;
  public   MutableLiveData<ArrayList<ProducteInfo>> prdouctInfi= new MutableLiveData<>();
    ProducteInfo producte;
    public   MutableLiveData<ArrayList<Uri>> ListOfimages= new MutableLiveData<>();
    ArrayList<String> imges= new ArrayList<>();
@Singleton
    FirebaseFirestore db;
    @Singleton
    StorageReference storageRef;

    public GetViewModle()
    {
        db   = FirebaseFirestore.getInstance();


    }
    public void getTheUserData(String Email,String Password,getTheData getData)
    {

        ArrayList<userInfo>  users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                    }
                });

    }

    public void CheckByEmail(String Email,EmailCheckInfrace emailCheckInfrace)
    {
        ArrayList<userInfo> users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                    }
                });
    }

    public void InsertUserData(userInfo use,InsertStatus insertStatus)
    {
        db.collection("users")
                .add(use)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        insertStatus.insetDataStatus(0);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        insertStatus.getErrorMessge(e.getMessage());

                    }
                });

    }

    public void getProducte()
    {

        ArrayList<ProducteInfo> producteInfos =new ArrayList<>();
        db.collection("ProducteInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention;
                        ProducteName=""+document.get("producteName");
                        ProductePrice=""+document.get("productePrice");
                        ProudcteImageUri=""+document.get("proudcteImageUri");
                        imges.add(ProudcteImageUri);
                        //StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/"+ProudcteImageUri);
                        ProudcteCompanty=""+document.get("proudcteCompanty");
                        //int ProducteQuantity = Integer.parseInt(""+document.get("producteQuantity"));
                        ImageExtention=""+document.get("extentionImage");
                        producteInfos.add(new ProducteInfo(ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention));
                        prdouctInfi.setValue(producteInfos);


                    }

                  /*  produteAdapter = new ProduteAdapter(producteInfos,ProducteViewr.this,floatingActionButton);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(ProducteViewr.this,2));
                    recyclerView.setAdapter(produteAdapter);*/
                    // Toast.makeText(ProducteViewr.this,"Successful"+producteInfos.size(),Toast.LENGTH_LONG).show();
                } else {

                   // Toast.makeText(ProducteViewr.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        public void UploadImagee(String pn, String pp, String pc, String filename, String extention, Uri imageuri,AddingProducteStatus statusCondetion)
        { storageRef = FirebaseStorage.getInstance().getReference("images/" + filename + "." + extention);
            storageRef.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    producte = new ProducteInfo(pn, pp, storageRef.getName(), pc, extention);
                    statusCondetion.statusUplod(true);
                    InsertProducteData(producte,statusCondetion);
                }
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
            db.collection("ProducteInfo").add(producte).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {

                //    Toast.makeText(AddPrdocte.this, R.string.succes, Toast.LENGTH_LONG).show();
                    stausAdd.statusInsert(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    stausAdd.statusInsert(false);
                    stausAdd.ErroInsert(e.getMessage());

                }
            });
        }

    /*    public void getImage(String ImageName)
        {
            ArrayList<Uri> iamges = new ArrayList<>();
            StrogPage strogPage = new StrogPage();
            strogPage.getImageSpeficImage(ImageName, new ImageListner() {
                @Override
                public void getImageUri(Uri imageU) {
                    iamges.add(imageU);
                    ListOfimages.setValue(iamges);
                }

                @Override
                public void errorMessge(String Error) {

                }
            });
        }*/


}
