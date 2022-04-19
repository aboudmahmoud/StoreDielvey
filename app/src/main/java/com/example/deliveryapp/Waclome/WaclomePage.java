package com.example.deliveryapp.Waclome;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.deliveryapp.Login.LoginPage;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.Signin.SignIN;
import com.example.deliveryapp.databinding.ActivityWaclomePageBinding;

public class WaclomePage extends AppCompatActivity {
    ActivityWaclomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityWaclomePageBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.btnSg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WaclomePage.this, SignIN.class);
                    startActivity(intent);
                }
            });
            binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(WaclomePage.this, LoginPage.class);
                    startActivity(intent);

                }
            });
            binding.btnShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WaclomePage.this, ProducteViewr.class);
                    startActivity(intent);
                }
            });
            binding.btnAboutme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(WaclomePage.this, AboutMe.class);
                    startActivity(intent);
                }
            });
        } catch (Exception ex) {
            System.out.println("Error is " + ex.getMessage());
        }
    }
}