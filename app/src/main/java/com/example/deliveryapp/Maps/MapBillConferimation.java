package com.example.deliveryapp.Maps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.ActivityMapBillConferimationBinding;

public class MapBillConferimation extends AppCompatActivity {
    private ActivityMapBillConferimationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMapBillConferimationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}