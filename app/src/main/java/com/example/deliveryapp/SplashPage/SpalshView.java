package com.example.deliveryapp.SplashPage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.deliveryapp.MainActivity;
import com.example.deliveryapp.Waclome.WaclomePage;
import com.example.deliveryapp.databinding.ActivitySpalshViewBinding;

public class SpalshView extends AppCompatActivity {
ActivitySpalshViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySpalshViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread thread = new Thread()
        {
            public void  run(){

                try{
                    sleep(5000);
                }
                catch (Exception ex)
                {
                    System.out.println("Error is"+ ex.getMessage());
                }
                finally {
                    Intent intent= new Intent(SpalshView.this, WaclomePage.class);
                    startActivity(intent);
                    finish();
                }
        }
        };thread.start();

    }
}