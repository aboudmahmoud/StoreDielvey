package com.example.deliveryapp.ProducteControl.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.FirebaseStore.ImageListner;
import com.example.deliveryapp.FirebaseStore.StrogPage;
import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.RproductspageBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProduteAdapter extends RecyclerView.Adapter<ProduteAdapter.ProduteViewHolder> {
    ArrayList<ProducteInfo> producteInfos;
    ArrayList<Ball> balls;
    Context context;
    FloatingActionButton floatingActionButton;
    int qunatnty;
    public ProduteAdapter(ArrayList<ProducteInfo> producteInfos,
                          Context context,
                          FloatingActionButton floatingActionButton) {
        this.producteInfos = producteInfos;
        this.context = context;
        this.floatingActionButton= floatingActionButton;
        ///  this.listener=listener;
        balls = new ArrayList<>();

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

            }
        });


    }



    @Override
    public int getItemCount() {
        return producteInfos.size();
    }

    class ProduteViewHolder extends RecyclerView.ViewHolder {
       private RproductspageBinding binding;
        StrogPage strogPage;
        double Totalprice=1;

        public ProduteViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= RproductspageBinding.bind(itemView);

        }

        void bind(ProducteInfo produ)
        {
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
            qunatnty=0;
            Totalprice=0;
            binding.mins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     qunatnty = Integer.parseInt(binding.ProdutQuanty.getText().toString());
                    if(qunatnty <1)
                    {
                        Toast.makeText(context, R.string.lessthanOne, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        qunatnty--;
                        binding.ProdutQuanty.setText( String.valueOf(qunatnty));

                        Totalprice=price*qunatnty;
                        binding.ProuductePrice.setText(Totalprice+"$");

                    }
                }
            });
            binding.blus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qunatnty = Integer.parseInt(binding.ProdutQuanty.getText().toString());
                    qunatnty++;
                    binding.ProdutQuanty.setText( String.valueOf(qunatnty));
                    Totalprice=price*qunatnty;
                    binding.ProuductePrice.setText(Totalprice+"$");
                }
            });
            binding.AddToCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (qunatnty ==0 || Totalprice ==0 )
                    {
                        Toast.makeText(context, R.string.lessthanOne, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        balls.add(new Ball(produ,Totalprice,qunatnty));
                    }

                }
            });




        }


    }

}
