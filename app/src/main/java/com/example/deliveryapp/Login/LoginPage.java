package com.example.deliveryapp.Login;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.deliveryapp.FirebaseStore.getTheData;
import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.Moudle.userInfo;
import com.example.deliveryapp.ProducteControl.AddPrdocte;
import com.example.deliveryapp.R;
import com.example.deliveryapp.Signin.SignIN;
import com.example.deliveryapp.databinding.ActivityLoginPageBinding;
import com.google.android.material.textfield.TextInputEditText;

public class LoginPage extends AppCompatActivity {
    ActivityLoginPageBinding binding;

    String email, password;
    TextInputEditText pass, Emal;


    AlertDialog alertDialog;
    GetViewModle mymodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Emal = binding.Emailtext;
        pass = binding.passtext;
        mymodel = new ViewModelProvider(this).get(GetViewModle.class);

        binding.btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = Emal.getText().toString();
                password = pass.getText().toString();
                if (email.isEmpty()) {
                    Emal.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (password.isEmpty()) {
                    pass.setError(getString(R.string.errorEmpty));
                    return;
                }
                getUsers(email, password);


            }
        });

        binding.textSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignIN.class);
                startActivity(intent);
            }
        });
    }


    public void getUsers(String Email, String Password) {
         alertDialog=
                new AlertDialog.Builder(LoginPage.this).setTitle(R.string.wait)
                        .setMessage(getString(R.string.log))
                        .setIcon(R.drawable.ic_launcher_background).create();
        alertDialog.show();


        mymodel.getTheUserData(Email, Password, new getTheData() {
            @Override
            public void useingData(userInfo usernfo, int count) {
                if (count == 1) {
                    Toast.makeText(LoginPage.this, R.string.walcome, Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                    Intent intent = new Intent(LoginPage.this, AddPrdocte.class);
                    startActivity(intent);

                } else if (count == 2) {
                    alertDialog.dismiss();
                    Toast.makeText(LoginPage.this, R.string.passwordworng, Toast.LENGTH_LONG).show();
                } else if (count == 0) {
                    alertDialog.dismiss();
                    Toast.makeText(LoginPage.this, R.string.dosent, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void getError(String Error) {
                alertDialog.dismiss();
                Toast.makeText(LoginPage.this, getString(R.string.Errorgetingdata) + " " + Error, Toast.LENGTH_LONG).show();
            }
        });

    }


}