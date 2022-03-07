package com.example.deliveryapp.Login;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.FirebaseStore.StrogPage;
import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.AddPrdocte;
import com.example.deliveryapp.ProducteControl.ProducteViewr;
import com.example.deliveryapp.R;
import com.example.deliveryapp.Signin.SignIN;
import com.example.deliveryapp.Waclome.WaclomePage;
import com.example.deliveryapp.databinding.ActivityLoginPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {
ActivityLoginPageBinding binding;
    userInfo user;
    public static int statis=0;
    String  email, password;
    TextInputEditText pass ,Emal;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        Emal = binding.Emailtext;
        pass= binding.passtext;

        binding.btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = Emal.getText().toString();
                password = pass.getText().toString();
                if (email.isEmpty())
                {
                    Emal.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (password.isEmpty())
                {
                    pass.setError(getString(R.string.errorEmpty));
                    return;
                }
                getUsers(email,password);



            }
        });
    }

    public void getUsers(String Email, String Password)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.log));
        progressDialog.show();
        ArrayList<userInfo>  users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count =0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = ""+document.get("fullName");
                                String email = ""+document.get("email");
                                String password = ""+document.get("password");
                                String phone = ""+document.get("phoneNumber");
                                String place = ""+document.get("place");
                                String ste= ""+ document.get("status");
                                int status=parseInt(ste);

                                users.add(new userInfo(name,email,password,place,phone,status));

                                if (Email.equals(document.get("email")))
                                {
                                    if (Password.equals(document.get("password")))
                                    {
                                        count=1;
                                        if(progressDialog.isShowing())
                                            progressDialog.dismiss();
                                        if (status==0)
                                        {
                                            Toast.makeText(LoginPage.this, R.string.walcome,Toast.LENGTH_LONG).show();
                                            Intent intent= new Intent(LoginPage.this, ProducteViewr.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {
                                            Toast.makeText(LoginPage.this, R.string.walcome,Toast.LENGTH_LONG).show();
                                            Intent intent= new Intent(LoginPage.this, AddPrdocte.class);
                                            startActivity(intent);
                                            finish();
                                        }


                                    }
                                    else
                                    {
                                        count=2;
                                        if(progressDialog.isShowing())
                                            progressDialog.dismiss();
                                        Toast.makeText(LoginPage.this,R.string.passwordworng,Toast.LENGTH_LONG).show();

                                    }
                                    //statis=count;
                                }


                            }
                            if(count==0)
                            {
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                                Toast.makeText(LoginPage.this,R.string.dosent,Toast.LENGTH_LONG).show();
                            }
                        } else {
                          //  Log.w("TAG", "Error getting documents.", task.getException());
                            Toast.makeText(LoginPage.this,getString(R.string.Errorgetingdata) +task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });

      //  return users;

    }

    public void onCLicke(View view) {
        Intent intent= new Intent(LoginPage.this, SignIN.class);
        startActivity(intent);
    }
}