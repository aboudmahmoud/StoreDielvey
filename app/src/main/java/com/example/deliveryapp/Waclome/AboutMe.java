package com.example.deliveryapp.Waclome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.deliveryapp.databinding.ActivityAboutMeBinding;

public class AboutMe extends AppCompatActivity {
ActivityAboutMeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutMeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.facbook.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/boy.good.1088/"))));

        binding.twiiter.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/aboudmahmoud2"))));

        binding.tallgram.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Aboud_Mahmoud"))));

        binding.githubRebo.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/aboudmahmoud/StoreDielvey"))));

        binding.github.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/aboudmahmoud"))));

        binding.linkedIn.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/aboudmahmoud/"))));

    }
}