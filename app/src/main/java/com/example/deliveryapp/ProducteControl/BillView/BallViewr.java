package com.example.deliveryapp.ProducteControl.BillView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.ProducteControl.Adapters.BillAdapter;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.ActivityBallViewrBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class BallViewr extends AppCompatActivity implements Serializable {
ActivityBallViewrBinding binding;
    ArrayList<Ball> balls;
    BillAdapter adapter;
    double TotalPrice=0;
   // boolean PlusOrMins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBallViewrBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        balls = (ArrayList<Ball>) getIntent().getSerializableExtra("Ball");
        setupSwipe();
       // PlusOrMins=true;
        setupthePage();
    }

    private void setupthePage() {
        if (balls.size()!=0) {
            binding.textView5.setText(getString(R.string.num)+" " + balls.size());


            for (int i = 0; i < balls.size(); i++) {
                TotalPrice = TotalPrice + balls.get(i).getTotalPrice();
            /*    if (staus==true) {
                    TotalPrice = TotalPrice + balls.get(i).getTotalPrice();
                }
                else
                {
                    TotalPrice = TotalPrice - balls.get(i).getTotalPrice();
                }*/


            }
            binding.totalpricer.setText(getString(R.string.total) + " " + TotalPrice + "$");
            adapter = new BillAdapter(balls,this);
            binding.RvBill.setAdapter(adapter);
           // binding.RvBill.setLayoutManager(new GridLayoutManager(this,1));
            binding.RvBill.setHasFixedSize(true);



        }
        else{
            binding.textView5.setVisibility(View.GONE);
            binding.totalpricer.setVisibility(View.GONE);
            binding.RvBill.setVisibility(View.GONE);
            binding.NoBill.setVisibility(View.VISIBLE);

        }
    }

    void deletTheItem(Ball ball)
    {
        if (balls.size()!=0) {
            binding.textView5.setText(getString(R.string.num)+" " + balls.size());

            TotalPrice = TotalPrice - ball.getTotalPrice();

            binding.totalpricer.setText(getString(R.string.total) + " " + TotalPrice + "$");
            adapter = new BillAdapter(balls,this);
            binding.RvBill.setAdapter(adapter);
            // binding.RvBill.setLayoutManager(new GridLayoutManager(this,1));
            binding.RvBill.setHasFixedSize(true);



        }
        else{
            binding.textView5.setVisibility(View.GONE);
            binding.totalpricer.setVisibility(View.GONE);
            binding.RvBill.setVisibility(View.GONE);
            binding.NoBill.setVisibility(View.VISIBLE);

        }
    }



    private void setupSwipe()
    {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int SwipedItem=viewHolder.getAdapterPosition();
                Ball ball= adapter.getSwitchedItem(SwipedItem);
                balls.remove(ball);
                adapter.setBalls(balls);
                deletTheItem(ball);
              //  PlusOrMins=false;
               // setupthePage(PlusOrMins);
                //adapter.notifyItemRemoved(SwipedItem);

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(binding.RvBill);
    }
}
