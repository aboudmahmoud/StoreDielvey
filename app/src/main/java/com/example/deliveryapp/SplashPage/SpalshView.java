package com.example.deliveryapp.SplashPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.deliveryapp.MainActivity;
import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.RoomDatabase.CurrentUser;
import com.example.deliveryapp.RoomDatabase.ViewRoomModel;
import com.example.deliveryapp.Waclome.WaclomePage;
import com.example.deliveryapp.databinding.ActivitySpalshViewBinding;

public class SpalshView extends AppCompatActivity {
ActivitySpalshViewBinding binding;
    ViewRoomModel viewRoomModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySpalshViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewRoomModel= new ViewModelProvider(this).get(ViewRoomModel.class);
        Thread thread = new Thread()
        {
            public void  run(){

                try{
                    sleep(2000);
                }
                catch (Exception ex)
                {
                    System.out.println("Error is"+ ex.getMessage());
                }
                finally {

                   // viewRoomModel.
                   int current =viewRoomModel.getCurrenstatus();
                   if (current==1)
                   {
                       CurrentUser.CurrentSataus=true;
                   }
                   else {
                       CurrentUser.CurrentSataus=false;
                   }

                    if (!CurrentUser.CurrentSataus) {
                        Intent intent = new Intent(SpalshView.this, WaclomePage.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SpalshView.this, ProducteViewr.class);
                        String id=viewRoomModel.getCurrenUserId();
                       
                        runOnUiThread(new Runnable() {
                            public void run() {
                                // my code to update the UI thread here
                                viewRoomModel.Getlusar(id).observe(SpalshView.this, new Observer<userInfo>() {
                                    @Override
                                    public void onChanged(userInfo userInfo) {

                                intent.putExtra("userData",userInfo);
                                startActivity(intent);
                                finish();
                                    }
                                });
                            }
                        });


                    }
                }
        }
        };thread.start();

    }
}