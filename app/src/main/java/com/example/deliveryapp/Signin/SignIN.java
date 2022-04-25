package com.example.deliveryapp.Signin;

import static java.lang.Integer.parseInt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.FirebaseStore.EmailCheckInfrace;
import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.FirebaseStore.InsertStatus;
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

    ActivitySignInBinding binding;
    userInfo user;
    String Fullname, phone, email, adders, password;
    TextInputEditText name, pass, phon, Emal, adder;
    //StrogPage strogPage;

    AlertDialog alertDialog;
    GetViewModle mymodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = binding.textName;
        pass = binding.textPass;
        phon = binding.textphone;
        Emal = binding.textEmail;
        adder = binding.textCity;

        mymodel=new ViewModelProvider(this).get(GetViewModle.class);

        binding.btnSing.setOnClickListener(view -> {
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
        });
        binding.textLogin.setOnClickListener(view -> {
            Intent intent= new Intent(SignIN.this, LoginPage.class);
            startActivity(intent);

        });
    }


  public void CheckEmail(String Email)
  {

      alertDialog=
              new AlertDialog.Builder(this).setTitle(R.string.wait)
                      .setMessage(getString(R.string.registering))
                      .setIcon(R.drawable.ic_reging).create();
      alertDialog.show();
      mymodel.CheckByEmail(Email, new EmailCheckInfrace() {
          @Override
          public void ChecKEmail(int statusUser) {
              if(statusUser==0)
              {


                  user= new userInfo(Fullname,email,password,adders,phone,0);
                  InsrteTheData(user);
              }
              else
              {
                  Toast.makeText(SignIN.this, R.string.errorEmailisExsit,Toast.LENGTH_LONG).show();
                  if(alertDialog.isShowing())
                      alertDialog.dismiss();

              }
          }

          @Override
          public void messgeError(String Error) {
              if(alertDialog.isShowing())
                  alertDialog.dismiss();
              Toast.makeText(SignIN.this,Error,Toast.LENGTH_LONG).show();
          }
      });


  }


    public void InsrteTheData ( userInfo use)
    {
        mymodel.InsertUserData(use, new InsertStatus() {
            @Override
            public void insetDataStatus(int Status) {
                if (Status==0)
                {
                    Toast.makeText(SignIN.this, R.string.RegDone,Toast.LENGTH_LONG).show();
                    name.setText("");
                    pass.setText("");
                    phon.setText("");
                    Emal.setText("");
                    adder.setText("");
                    if(alertDialog.isShowing())
                        alertDialog.dismiss();
                    Intent intent= new Intent(SignIN.this, LoginPage.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void getErrorMessge(String error) {
                if(alertDialog.isShowing())
                    alertDialog.dismiss();
                Toast.makeText(SignIN.this, error,Toast.LENGTH_LONG).show();
            }
        });
    }
}