package com.example.deliveryapp.Maps;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.deliveryapp.ProducteControl.Adapters.ConfremationBuing;
import com.example.deliveryapp.ProducteControl.BillView.BallViewr;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.ActivityMapsBinding;


import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
    private GoogleMap map;
    private ActivityMapsBinding binding;
    static public ConfremationBuing confremationBuing;
    boolean statuesChoesd=false;
    boolean statuesPermion=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

        binding.ftbConfermied.setOnClickListener(view -> {
            if (statuesChoesd)
            {
                if (confremationBuing!=null)
                {

                    confremationBuing.SetConferMation();

                }
                Intent intent = new Intent(MapsActivity.this, MapBillConferimation.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(MapsActivity.this, R.string.marke, Toast.LENGTH_SHORT).show();
            }

        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        while(!statuesPermion)
        {
            try {

                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMapClickListener(latLng1 -> {
                    map.clear();
                    map.addMarker(new MarkerOptions().position(latLng1).title(" is my Postion"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng1));
                    statuesChoesd=true;
                });
                statuesPermion=true;

            }
            catch (SecurityException ex)
            {
                Log.d("Aboud", "onMapReady: error is "+ex);
            }
        }




    }



}