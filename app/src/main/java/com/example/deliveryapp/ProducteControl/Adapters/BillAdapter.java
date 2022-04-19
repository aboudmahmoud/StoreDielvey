package com.example.deliveryapp.ProducteControl.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.BillValuesBinding;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillHolder> {

    ArrayList<Ball> balls;
Context context;
    public BillAdapter(ArrayList<Ball> balls,Context context) {
        this.balls = balls;
        this.context=context;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_values, null, false);
        BillHolder billHolder = new BillHolder(view);
        return billHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, int position) {
        holder.bind(balls.get(position));
    }

    @Override
    public int getItemCount() {
        return balls.size();
    }

    public Ball getSwitchedItem(int postion)
    {
        return balls.get(postion);
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
        notifyDataSetChanged();
    }

    class BillHolder extends  RecyclerView.ViewHolder{
        private final BillValuesBinding binding;
        public BillHolder(@NonNull View itemView) {
            super(itemView);
            binding= BillValuesBinding.bind(itemView);
        }
        void bind(Ball bill)
        {
            binding.producteNameT.setText(bill.getProducteInfo().getProducteName());
            binding.Price.setText(bill.getProducteInfo().getProductePrice());
            binding.Quntaty.setText(String.valueOf(bill.getQunaty()));
            binding.totalprice.setText(String.valueOf(bill.getTotalPrice()));

        }


    }
}
