package com.example.deliveryapp.ProducteControl;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;

import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.ProducteControl.Adapters.ProduteAdapter;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class ProducteViewr extends AppCompatActivity {


    RecyclerView recyclerView;
    ProduteAdapter produteAdapter;
    FloatingActionButton floatingActionButton;
    GetViewModle mymodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producte_viewr);

        recyclerView=findViewById(R.id.RV);
        floatingActionButton=findViewById(R.id.floatingactionbutton);
        mymodel=new ViewModelProvider(this).get(GetViewModle.class);
        mymodel.getProducte();
        mymodel.prdouctInfi.observe(this, new Observer<ArrayList<ProducteInfo>>() {
            @Override
            public void onChanged(ArrayList<ProducteInfo> producteInfos) {

                produteAdapter = new ProduteAdapter(producteInfos,ProducteViewr.this,floatingActionButton);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new GridLayoutManager(ProducteViewr.this,2));
                recyclerView.setAdapter(produteAdapter);
               // mymodel.getImage();

            }
        });


    }




}