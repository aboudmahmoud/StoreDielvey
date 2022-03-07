package com.example.deliveryapp.Adapters;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import com.example.deliveryapp.Moudle.Ball;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProduteAdapter extends RecyclerView.Adapter<ProduteAdapter.ProduteViewHolder> {
    ArrayList<ProducteInfo> producteInfos;

    private int[] Quantity;
    int[] quanty;
    double[] price,amout;
    double[] totalprice;
    int[] NameAdded,QuantyBuy;
    Ball ball;
    ArrayList<Ball> balls;
    Context context;
    FloatingActionButton floatingActionButton;
    String PNAmes="";
    public ProduteAdapter(ArrayList<ProducteInfo> producteInfos, Context context,FloatingActionButton floatingActionButton) {
        this.producteInfos = producteInfos;
        setTheArray(producteInfos.size());

        this.context = context;
        this.floatingActionButton= floatingActionButton;
        ///  this.listener=listener;
        balls = new ArrayList<>();

    }

    void setTheArray(int size)
    {
        Quantity = new int[size];
        quanty= new int[size];
        price = new double[size];
        amout = new double[size];
        totalprice = new double[size];
        NameAdded=new int[size];
        QuantyBuy=new int[size];
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
//        holder.lottieAnimationView.setVisibility(View.VISIBLE);
//        holder.relativeLayout.setVisibility(View.GONE);
//        holder.relativeLayout.setVisibility(View.GONE);
        ProducteInfo producteInfo = producteInfos.get(position);
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(context.getString(R.string.getp));
        progressDialog.show();
        //set Texts that we gett
        holder.ProducteCompany.setText(producteInfo.getProudcteCompanty());
        holder.ProdutePrice.setText(producteInfo.getProductePrice() + "$");
        holder.ProdutName.setText("" + producteInfo.getProducteName());
        StorageReference storageRef = FirebaseStorage.getInstance().getReference()
                .child("images/" + producteInfo.getProudcteImageUri());
        try {
            File localfile = File.createTempFile("temp", "." + producteInfo.getExtentionImage());
            storageRef.getFile(localfile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localfile.getPath());
                    holder.ProduteImage.setImageBitmap(bitmap);

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    //   holder.relativeLayout.setVisibility(View.VISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            if (progressDialog.isShowing())
                progressDialog.dismiss();
        }

    price[holder.getAdapterPosition()] = Double.parseDouble(producteInfo.getProductePrice());
    quanty[holder.getAdapterPosition()] = Integer.parseInt(holder.ProducteQuanty.getText().toString());

        holder.Mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quanty[holder.getAdapterPosition()] < 1) {
                    Toast.makeText(context, R.string.lessthanOne, Toast.LENGTH_LONG).show();
                } else {
                    quanty[holder.getAdapterPosition()]--;
                    holder.ProducteQuanty.setText("" + quanty[holder.getAdapterPosition()]);
                    totalprice[holder.getAdapterPosition()] = price[holder.getAdapterPosition()] * quanty[holder.getAdapterPosition()];
                    holder.ProdutePrice.setText(totalprice[holder.getAdapterPosition()] + "$");
                }

            }
        });

        holder.BLUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quanty[holder.getAdapterPosition()]++;
                holder.ProducteQuanty.setText("" + quanty[holder.getAdapterPosition()]);
                totalprice[holder.getAdapterPosition()] = price[holder.getAdapterPosition()] * quanty[holder.getAdapterPosition()];
                holder.ProdutePrice.setText(totalprice[holder.getAdapterPosition()] + "$");
            }
        });

        NameAdded[holder.getAdapterPosition()]=0;
holder.AddTOCar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ball=new Ball();
        if ( NameAdded[holder.getAdapterPosition()]==0&&  quanty[holder.getAdapterPosition()]!= 0)
        {

            PNAmes=  holder.ProdutName.getText().toString();
            NameAdded[holder.getAdapterPosition()]=1;
            ball.setProudteName(PNAmes);
            ball.setProudcteQunaty(quanty[holder.getAdapterPosition()]);
            ball.setProductePrice(totalprice[holder.getAdapterPosition()]+"");
            balls.add(ball);

        }
        if ( NameAdded[holder.getAdapterPosition()]==1&& quanty[holder.getAdapterPosition()]!= 0)
        {

            for (int i=0 ; i<balls.size();i++)
            {
           if (balls.get(i).getProudteName().equals(holder.ProdutName.getText().toString()))
           {
               balls.get(i).setProudcteQunaty(quanty[holder.getAdapterPosition()]);
               balls.get(i).setProductePrice(totalprice[holder.getAdapterPosition()]+"");
           }
            }

        }



    }
});
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // holder.ProdutePrice.setText("Aboud");


             for (int i=0 ; i<balls.size();i++)
             {
                 System.out.println("Name of Proudet "+ balls.get(i).getProudteName());
                 System.out.println("Price of Proudet "+ balls.get(i).getProductePrice());
                 System.out.println("Quanty of Proudet "+ balls.get(i).getProudcteQunaty());
             }


            }
        });

    }



    @Override
    public int getItemCount() {
        return producteInfos.size();
    }

    class ProduteViewHolder extends RecyclerView.ViewHolder {
        TextView ProdutName, ProducteQuanty, ProdutePrice, ProducteCompany ;
        MaterialButton  AddTOCar;
        ImageView ProduteImage;
        ProducteInfo produte;
        //    RelativeLayout  relativeLayout;
        TextView BLUS, Mins;

        public ProduteViewHolder(@NonNull View itemView) {

            super(itemView);
            ProdutName = itemView.findViewById(R.id.ProduteNameR);
            ProducteQuanty = itemView.findViewById(R.id.ProdutQuanty);
            ProdutePrice = itemView.findViewById(R.id.ProuductePrice);
            ProducteCompany = itemView.findViewById(R.id.ProudteCampny);
            ProduteImage = itemView.findViewById(R.id.ProduteImage);
            // lottieAnimationView=(LottieAnimationView)itemView.findViewById(R.id.anv);
            BLUS = itemView.findViewById(R.id.blus);
            Mins = itemView.findViewById(R.id.mins);
            AddTOCar = itemView.findViewById(R.id.AddToCar);
            //relativeLayout= itemView.findViewById(R.id.Relaout);


        }
    }

}
