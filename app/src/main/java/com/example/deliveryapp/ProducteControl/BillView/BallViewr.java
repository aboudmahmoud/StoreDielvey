package com.example.deliveryapp.ProducteControl.BillView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.Login.LoginPage;
import com.example.deliveryapp.Maps.MapsActivity;
import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.ProducteControl.Adapters.BillAdapter;
import com.example.deliveryapp.ProducteControl.Adapters.ConfremationBuing;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.ActivityBallViewrBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class BallViewr extends AppCompatActivity implements Serializable, ConfremationBuing {
    ActivityBallViewrBinding binding;
    ArrayList<Ball> balls;
    BillAdapter adapter;
    double TotalPrice = 0;
    public static OnDeletItemBill onDeletItemBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBallViewrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MapsActivity.confremationBuing=this;
        balls = (ArrayList<Ball>) getIntent().getSerializableExtra("Ball");
        setupSwipe();
        setupthePage();
        binding.MapBtn.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
            {
                Intent intent = new Intent(BallViewr.this, MapsActivity.class);
                startActivity(intent);




            }
            else{
                Toast.makeText(this, R.string.supported, Toast.LENGTH_SHORT).show();
            }

        });
    }


    private void setupthePage() {
        if (balls.size() != 0) {
            binding.textView5.setText(getString(R.string.num) + " " + balls.size());


            for (int i = 0; i < balls.size(); i++) {
                TotalPrice = TotalPrice + balls.get(i).getTotalPrice();


            }
            binding.totalpricer.setText(getString(R.string.total) + " " + TotalPrice + "$");
            adapter = new BillAdapter(balls, this);
            binding.RvBill.setAdapter(adapter);
            // binding.RvBill.setLayoutManager(new GridLayoutManager(this,1));
            binding.RvBill.setHasFixedSize(true);


        } else {
            setTheApparnec();

        }
    }

    private void setTheApparnec() {
        binding.textView5.setVisibility(View.GONE);
        binding.totalpricer.setVisibility(View.GONE);
        binding.RvBill.setVisibility(View.GONE);
        binding.NoBill.setVisibility(View.VISIBLE);
        binding.MapBtn.setVisibility(View.GONE);
    }

    void deletTheItem(Ball ball) {
        if (balls.size() != 0) {
            binding.textView5.setText(getString(R.string.num) + " " + balls.size());

            TotalPrice = TotalPrice - ball.getTotalPrice();

            binding.totalpricer.setText(getString(R.string.total) + " " + TotalPrice + "$");
            adapter = new BillAdapter(balls, this);
            binding.RvBill.setAdapter(adapter);
            // binding.RvBill.setLayoutManager(new GridLayoutManager(this,1));
            binding.RvBill.setHasFixedSize(true);


        } else {
            setTheApparnec();

        }
    }


    private void setupSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int SwipedItem = viewHolder.getAdapterPosition();
                Ball ball = adapter.getSwitchedItem(SwipedItem);
                balls.remove(ball);
                deletandsetit();

                adapter.setBalls(balls);
                deletTheItem(ball);


            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.RvBill);
    }

    private void deletandsetit() {
        if (onDeletItemBill != null) {
            onDeletItemBill.DeletItem(balls);
        }
    }

    @Override
    public void SetConferMation() {
        setTheApparnec();
        balls.clear();
        deletandsetit();

    }

    public interface OnDeletItemBill {
        void DeletItem(ArrayList<Ball> ballis);

    }
}
