package com.example.deliveryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.deliveryapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

// the MainActivity does not contain anything important
// But I was just using  for an experiment, nothing more.
// I thought I would use that page for something more important,
// Bigger and better than logging in, but I didn't do that .
//If you asking about the start or where the App Start
//Go to SplshView if are not user than after is Waclomepage
// If ur user and ur data is saved u will go directly to ProducteView
public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ActionBar actionBar = getSupportActionBar();
//
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
     /*   binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.open();
            }
        });*/

        binding.tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  binding.drawerLayout.open();
                showPopup(view);
            }
        });


        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    public void showPopup(View anchorView) {

        View popupView = getLayoutInflater().inflate(R.layout.pupup_page, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                800, ActionBar.LayoutParams.WRAP_CONTENT);

        // Example: If you have a TextView inside `popup_layout.xml`
     //   TextView tv = (TextView) popupView.findViewById(R.id.UserName);



        // Initialize more widgets from `popup_layout.xml`

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        int color= 0xFFFFFFFF;
       popupWindow.setBackgroundDrawable(new ColorDrawable(color ));

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER,
                location[0], location[0] );
        int colore= 0x80C7C6C6;
        binding.drawerLayout.setBackgroundColor( R.color.rmady);

    }

}