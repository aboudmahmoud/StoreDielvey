package com.example.deliveryapp.ProducteControl;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliveryapp.BuildConfig;
import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.Fragments.DilogFragment;
import com.example.deliveryapp.Login.LoginPage;

import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.Adapters.ProduteAdapter;
import com.example.deliveryapp.Moudle.ProducteInfo;

import com.example.deliveryapp.ProducteControl.Adapters.addToNotfactionFormAddCart;
import com.example.deliveryapp.R;

import com.example.deliveryapp.RoomDatabase.CurrentUser;
import com.example.deliveryapp.RoomDatabase.ViewRoomModel;

import com.example.deliveryapp.Waclome.AboutMe;
import com.example.deliveryapp.databinding.ActivityProducteViewrBinding;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.navigation.NavigationBarView;



import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ProducteViewr extends AppCompatActivity implements Serializable , DilogFragment.OnPostiveButton, DilogFragment.OnNegativeButton, addToNotfactionFormAddCart {
    ActivityProducteViewrBinding binding;

    ProduteAdapter produteAdapter;

    GetViewModle mymodel;

    ViewRoomModel viewRoomModel;

    userInfo usernfo;

    MenuItem addProudcteItem,ProfileItem,BillItem;
    BottomNavigationView  mbottomNavigationView ;
    //for Notfaction
    TextView tv;
    public BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProducteViewrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      
       // navigationView.se


        mymodel=new ViewModelProvider(this).get(GetViewModle.class);
        mymodel.getLiveProducte();
        mymodel.prdouctInfi.observe(this, new Observer<ArrayList<ProducteInfo>>() {
            @Override
            public void onChanged(ArrayList<ProducteInfo> producteInfos) {
                produteAdapter = new ProduteAdapter(producteInfos,ProducteViewr.this,binding.floatingactionbutton);
                binding.RV.setHasFixedSize(true);
                binding.RV.setLayoutManager(new GridLayoutManager(ProducteViewr.this,2));
                binding.RV.setAdapter(produteAdapter);

               // mymodel.getImage();
            }
        });
        usernfo=(userInfo) getIntent().getSerializableExtra("userData");
        viewRoomModel= new ViewModelProvider(this).get(ViewRoomModel.class);
        Log.d("Aboud", "onCreate: "+CurrentUser.CurrentSataus);
        if (usernfo!=null)
        {
            if (CurrentUser.CurrentSataus==false)
            {
                viewRoomModel.InsertUser(usernfo);
                viewRoomModel.InsertCurrentUser(new CurrentUser(usernfo.getUIde(),1));
            }



        }
        //setTextNotfatcion
        extracted();


        binding.btm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.addProudcte:
                    {
                        if (usernfo!=null)
                        {
                            Intent intent= new Intent(ProducteViewr.this, AddPrdocte.class);
                            startActivity(intent);
                        }
                        else
                        {
                            DilogFragment fragment = DilogFragment.newInstance(getString(R.string.attention)
                                    ,getString(R.string.singImp),R.drawable.ic_attention);
                            fragment.show(getSupportFragmentManager(),null);

                        }
                        //   binding.btm.setSelectedItemId(R.id.addProudcte);
                        item.setChecked(true);
                        break;
                    }
                    case R.id.Prof:
                    {

                        showPopup(new View(ProducteViewr.this));

                        item.setChecked(true);
                        break;
                    }
                    case R.id.billCoustmer:
                    {
                       // showMessage("hi");
                        produteAdapter.setTheBill();
                        //binding.btm.setSelectedItemId( R.id.billCoustmer);
                        item.setChecked(true);
                        produteAdapter.setCount(0);
                        tv.setVisibility(View.GONE);
                        break;

                    }

                }
                return false;
            }
        });





    }

    private void extracted() {
        mbottomNavigationView=binding.btm;
        BottomNavigationMenuView  mbottomNavigationMenuView = (BottomNavigationMenuView) mbottomNavigationView.getChildAt(0);
        View view = mbottomNavigationMenuView.getChildAt(2);
        BottomNavigationItemView itemView = (BottomNavigationItemView) view;

        View cart_badge = LayoutInflater.from(this)
                .inflate(R.layout.coustem_bill_notfaction,
                        mbottomNavigationMenuView, false);

        itemView.addView(cart_badge);
        tv = (TextView) cart_badge.findViewById(R.id.TextNotifications);


    }





    @Override
    public void onpostiveClicked() {
        Intent intent= new Intent(ProducteViewr.this, LoginPage.class);
        startActivity(intent);
    }

    @Override
    public void OnNegativeButtonClicked() {

    }




    @SuppressLint("ResourceAsColor")
    public void showPopup(View anchorView) {

        View popupView = getLayoutInflater().inflate(R.layout.pupup_page, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                800, ActionBar.LayoutParams.WRAP_CONTENT);

        // Example: If you have a TextView inside `popup_layout.xml`
           TextView tvUserName = (TextView) popupView.findViewById(R.id.UNTEXT);
        TextView tvEmail = (TextView) popupView.findViewById(R.id.EMText);
        LinearLayout LLAddPrdoucted= (LinearLayout)popupView.findViewById(R.id.addPrd);
        LinearLayout LLAboutApp= (LinearLayout)popupView.findViewById(R.id.AboutApp);
        LinearLayout LLshareApp = (LinearLayout)popupView.findViewById(R.id.shareApp);
        if (usernfo!=null)
        {
            tvUserName.setText(usernfo.getFullName());
            tvEmail.setText(usernfo.getEmail());
            tvEmail.setVisibility(View.VISIBLE);

        }

        LLAddPrdoucted.setOnClickListener(view -> {
            if (usernfo!=null)
            {
                Intent intent= new Intent(ProducteViewr.this, AddPrdocte.class);
                startActivity(intent);
            }
            else
            {
                DilogFragment fragment = DilogFragment.newInstance(getString(R.string.attention)
                        ,getString(R.string.singImp),R.drawable.ic_attention);
                fragment.show(getSupportFragmentManager(),null);

            }
        });

        LLAboutApp.setOnClickListener(view -> {
            Intent intent= new Intent(ProducteViewr.this, AboutMe.class);
            startActivity(intent);
        });

        LLshareApp.setOnClickListener(view -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,  getString(R.string.app_name));
                String shareMessage= getString(R.string.recmoned);
                shareMessage = shareMessage + "https://github.com/aboudmahmoud/StoreDielvey  "+"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, getString(R.string.choes)));
            } catch(Exception e) {
                //e.toString();
            }

        });
        // Initialize more widgets from `popup_layout.xml`

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when when touched outside
        int color= 0xFFFFFFFF;
        popupWindow.setBackgroundDrawable(new ColorDrawable(color ));
        binding.Relaout.setBackgroundColor(color);
        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.CENTER,
                location[0], location[0] );

        int colore= 0x80C7C6C6;
        binding.Relaout.setBackgroundColor(colore);


    }


    @Override
    public void NotfyBill(int count) {
        tv.setVisibility(View.VISIBLE);
        tv.setText(String.valueOf(count));
    }
}
