package com.example.deliveryapp.ProducteControl.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryapp.ProducteControl.BillView.BallViewr;
import com.example.deliveryapp.FirebaseStore.ImageListner;
import com.example.deliveryapp.FirebaseStore.StrogPage;
import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.RproductspageBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class ProduteAdapter extends RecyclerView.Adapter<ProduteAdapter.ProduteViewHolder> implements Serializable {
    ArrayList<ProducteInfo> producteInfos;
    ArrayList<Ball> balls;
    Context context;
    FloatingActionButton floatingActionButton;
    addToNotfactionFormAddCart toNotfactionFormAddCart;
    static  int count=0;


    public ProduteAdapter(ArrayList<ProducteInfo> producteInfos,
                          Context context,
                          FloatingActionButton floatingActionButton) {
        this.producteInfos = producteInfos;
        this.context = context;
        this.floatingActionButton= floatingActionButton;
        ///  this.listener=listener;
        balls = new ArrayList<>();
        toNotfactionFormAddCart=(addToNotfactionFormAddCart)context;
    }


    @NonNull
    @Override
    public ProduteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rproductspage, null, false);
        ProduteViewHolder produteViewHolder = new ProduteViewHolder(view);
        return produteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProduteViewHolder holder, int position) {
        holder.bind(producteInfos.get(position));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, BallViewr.class);
                intent.putExtra("Ball",balls);
                context.startActivity(intent);

            }
        });


    }



    public  void setCount(int count) {
        ProduteAdapter.count = count;
    }

public void setTheBill()
{

    Intent intent= new Intent(context, BallViewr.class);
    intent.putExtra("Ball",balls);
    context.startActivity(intent);
    if(context instanceof Activity){
        ((Activity)context).finish(); }

}

    @Override
    public int getItemCount() {
        return producteInfos.size();
    }

    class ProduteViewHolder extends RecyclerView.ViewHolder {
       private RproductspageBinding binding;
        StrogPage strogPage;
        double Totalprice=0;

        public ProduteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= RproductspageBinding.bind(itemView);

        }

        void bind(ProducteInfo produ)
        {
            final int[] qunatnty = {0};
            binding.ProudteCampny.setText(produ.getProudcteCompanty());
            binding.ProduteNameR.setText(produ.getProducteName());
            binding.ProuductePrice.setText(produ.getProductePrice()+"$");
            strogPage = new StrogPage();
            strogPage.getImageSpeficImage(produ.getProudcteImageUri(), new ImageListner() {
                @Override
                public void getImageUri(Uri imageU) {
                    Glide.with(context).load(imageU).into(binding.ProduteImage);
                }

                @Override
                public void errorMessge(String Error) {

                }
            });
            double price = Double.parseDouble(produ.getProductePrice());


            binding.mins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     qunatnty[0] = Integer.parseInt(binding.ProdutQuanty.getText().toString());
                    if(qunatnty[0] <1)
                    {
                        Toast.makeText(context, R.string.lessthanOne, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        qunatnty[0]--;
                        binding.ProdutQuanty.setText( String.valueOf(qunatnty[0]));

                        Totalprice=price* qunatnty[0];
                        binding.ProuductePrice.setText(Totalprice+"$");

                    }
                }
            });
            binding.blus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     qunatnty[0] = Integer.parseInt(binding.ProdutQuanty.getText().toString());
                    qunatnty[0]++;
                    binding.ProdutQuanty.setText( String.valueOf(qunatnty[0]));
                    Totalprice=price* qunatnty[0];
                    binding.ProuductePrice.setText(Totalprice+"$");
                }
            });
            binding.AddToCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (qunatnty[0] ==0)
                    {
                        Toast.makeText(context, R.string.lessthanOne, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {

                        balls.add(new Ball(produ,Totalprice, qunatnty[0]));
                        count++;
                        toNotfactionFormAddCart.NotfyBill(count);
                    }

                }

            });




        }


    }

}
