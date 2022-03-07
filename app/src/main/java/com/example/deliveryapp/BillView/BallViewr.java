package com.example.deliveryapp.BillView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.deliveryapp.databinding.ActivityBallViewrBinding;

public class BallViewr extends AppCompatActivity {
ActivityBallViewrBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityBallViewrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}