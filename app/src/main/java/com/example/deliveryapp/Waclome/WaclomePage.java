package com.example.deliveryapp.Waclome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.example.deliveryapp.Login.LoginPage;
import com.example.deliveryapp.ProducteControl.AddPrdocte;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.Signin.SignIN;
import com.example.deliveryapp.databinding.ActivityWaclomePageBinding;

import java.util.Locale;

public class WaclomePage extends AppCompatActivity {
    ActivityWaclomePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityWaclomePageBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            binding.btnSg.setOnClickListener(view -> {
                Intent intent = new Intent(WaclomePage.this, SignIN.class);
                startActivity(intent);
            });
            binding.btnLogin.setOnClickListener(view -> {

                Intent intent = new Intent(WaclomePage.this, LoginPage.class);
                startActivity(intent);

            });
            binding.btnShop.setOnClickListener(view -> {
                Intent intent = new Intent(WaclomePage.this, ProducteViewr.class);
                startActivity(intent);
            });
            binding.btnAboutme.setOnClickListener(view -> {
                Intent intent = new Intent(WaclomePage.this, AboutMe.class);
                startActivity(intent);
            });

            binding.ArbicLang.setOnClickListener(view -> setLocale(WaclomePage.this,"ar"));

            binding.Enlgilshlang.setOnClickListener(view -> setLocale(WaclomePage.this,"en"));
        } catch (Exception ex) {
            System.out.println("Error is " + ex.getMessage());
        }
    }
//this method to change language of the app
    public static void setLocale(Activity activity, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        Intent intent= new Intent(activity, WaclomePage.class);
        activity.startActivity(intent);
        activity.finish();
    }

}