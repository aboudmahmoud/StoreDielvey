package com.example.deliveryapp.Signin;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.FirebaseStore.StrogPage;
import com.example.deliveryapp.Login.LoginPage;
import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.AddPrdocte;
import com.example.deliveryapp.R;
import com.example.deliveryapp.Waclome.WaclomePage;
import com.example.deliveryapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class SignIN extends AppCompatActivity {
    FirebaseFirestore db;
    ActivitySignInBinding binding;
    userInfo user;
    String Fullname, phone, email, adders, password;
    TextInputEditText name, pass, phon, Emal, adder;
    //StrogPage strogPage;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        name = binding.textName;
        pass = binding.textPass;
        phon = binding.textphone;
        Emal = binding.textEmail;
        adder = binding.textCity;


        binding.btnSing.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //
              //  strogPage= new StrogPage(getBaseContext());
                Fullname = name.getText().toString().trim();
                password = pass.getText().toString().trim();
                phone = phon.getText().toString().trim();
                email = Emal.getText().toString().trim();
                adders = adder.getText().toString().trim();
                if (Fullname.isEmpty())
                {
                    name.setError(getString(R.string.errorEmpty));
                 return;
                }
                int siz = password.length();
                if (password.isEmpty()|| siz<7 )
                {
                    pass.setError(getString(R.string.errorEmpty)+getString(R.string.less));
                    return;
                }
                if (phone.isEmpty())
                {
                    phon.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (email.isEmpty())
                {
                    Emal.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (adders.isEmpty())
                {
                    adder.setError(getString(R.string.errorEmpty));
                    return;
                }
                CheckEmail(email);

            // strogPage.insertuser(user);





              //  Toast.makeText(getApplicationContext(), "ur name is " + Fullname, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onClicke(View v) {
        Intent intent= new Intent(SignIN.this, LoginPage.class);
        startActivity(intent);
        //finish();
    }

    public void InsrteTheData ( userInfo use)
    {
        db.collection("users")
                .add(use)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {

                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d("TGHP", "DocumentSnapshot added with ID: " + documentReference.getId());
                       // System.out.println("Succses");

                        name.setText("");
                        pass.setText("");
                        phon.setText("");
                        Emal.setText("");
                        adder.setText("");
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(SignIN.this, R.string.RegDone,Toast.LENGTH_LONG).show();
                        //   done.countDown();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(SignIN.this,  e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
    }

    public void CheckEmail(String Email)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.registering));
        progressDialog.show();

        ArrayList<userInfo> users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int count =0;
                            for (QueryDocumentSnapshot document : task.getResult()) {


                                if (Email.equals(document.get("email")))
                                {
                                    count=1;

                                    //statis=count;
                                }


                            }
                            if(count==0)
                            {
                               // Toast.makeText(LoginPage.this,R.string.dosent,Toast.LENGTH_LONG).show();
                                user= new userInfo(Fullname,email,password,adders,phone,0);
                                InsrteTheData(user);
                            }
                            else
                            {
                                Toast.makeText(SignIN.this, R.string.errorEmailisExsit,Toast.LENGTH_LONG).show();
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                            }
                        } else {
                         //   Log.w("TAG", "Error getting documents.", task.getException());
                            Toast.makeText(SignIN.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });

        //  return users;

    }
}