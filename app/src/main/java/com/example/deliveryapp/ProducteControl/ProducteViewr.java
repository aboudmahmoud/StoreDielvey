package com.example.deliveryapp.ProducteControl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.Adapters.ProduteAdapter;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class ProducteViewr extends AppCompatActivity {
    FirebaseFirestore db;
    ArrayList<ProducteInfo> producteInfos;
    RecyclerView recyclerView;
    ProduteAdapter produteAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producte_viewr);
        db= FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.RV);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
      getThedata();
        //StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/"+"");
    }

    void getThedata( )
    {
        producteInfos= new ArrayList<>();
        db.collection("ProducteInfo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        String ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention;
                        ProducteName=""+document.get("producteName");
                        ProductePrice=""+document.get("productePrice");
                        ProudcteImageUri=""+document.get("proudcteImageUri");
                        //StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/"+ProudcteImageUri);
                        ProudcteCompanty=""+document.get("proudcteCompanty");
                        //int ProducteQuantity = Integer.parseInt(""+document.get("producteQuantity"));
                        ImageExtention=""+document.get("extentionImage");
                        producteInfos.add(new ProducteInfo(ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ImageExtention));
                    }

                    produteAdapter = new ProduteAdapter(producteInfos,ProducteViewr.this,floatingActionButton);
                  recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(ProducteViewr.this,2));
                    recyclerView.setAdapter(produteAdapter);
                   // Toast.makeText(ProducteViewr.this,"Successful"+producteInfos.size(),Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(ProducteViewr.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}