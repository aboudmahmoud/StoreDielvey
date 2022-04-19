
package com.example.deliveryapp.ProducteControl;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.example.deliveryapp.FirebaseStore.AddingProducteStatus;
import com.example.deliveryapp.FirebaseStore.GetViewModle;
import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.R;
import com.example.deliveryapp.databinding.ActivityAddPrdocteBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;


public class AddPrdocte extends AppCompatActivity {
    ActivityAddPrdocteBinding bindin;
    Uri imageuri, AddersOnFrieStoreg;
    GetViewModle mymodel;
    DateFormat df;
    String filename, extention;
    AlertDialog alertDialog;
    TextInputEditText PN, PP, PC;
    String ProdutName, ProdutePrice, ProducteCompany;
    ProducteInfo producte;
    ActivityResultLauncher<String> activityResultLauncher;
    boolean imageSeltcted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindin = ActivityAddPrdocteBinding.inflate(getLayoutInflater());
        setContentView(bindin.getRoot());
        PN = bindin.ProdcteName;
        // PQ=bindin.ProdcteQaenty;
        PP = bindin.ProdctePrice;
        PC = bindin.ProdcteCompany;
        mymodel = new ViewModelProvider(this).get(GetViewModle.class);
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Date d = new Date();
                        df = new SimpleDateFormat("HH:mm:ss a, dd:MM:yyyy", Locale.getDefault());
                        filename = df.format(d);


                        imageuri = result;

                        extention = getFileExtension(imageuri);
                        // bindin.Img.setImageURI(imageuri);
                        bindin.PhotoName.setText(filename);
                        imageSeltcted = true;
                    }
                });
        bindin.btnChosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();

            }
        });


        bindin.btUpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProdutName = PN.getText().toString();
                //  ProducteQuanty=PQ.getText().toString();
                ProdutePrice = PP.getText().toString();
                ProducteCompany = PC.getText().toString();
                if (ProdutName.isEmpty()) {
                    PN.setError(getString(R.string.errorEmpty));
                    return;
                }

                if (ProdutePrice.isEmpty()) {
                    PN.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (ProducteCompany.isEmpty()) {
                    PC.setError(getString(R.string.errorEmpty));
                    return;
                }
                //   int ProductQuanty=Integer.parseInt(ProducteQuanty);
                if (imageSeltcted) {
                    UplodImaga(ProdutName, ProdutePrice, ProducteCompany);
                } else {
                    Toast.makeText(AddPrdocte.this, R.string.entiringimag, Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });


    }

    private void SelectImage() {

        activityResultLauncher.launch("image/*");


    }

    void UplodImaga(String pn, String pp, String pc) {
        alertDialog =
                new AlertDialog.Builder(this).setTitle(R.string.wait)
                        .setMessage(getString(R.string.up))
                        .setIcon(R.drawable.ic_clouding).create();
        alertDialog.show();
        mymodel.UploadImagee(pn, pp, pc, filename, extention, imageuri, new AddingProducteStatus() {
            @Override
            public void statusUplod(boolean status) {

            }

            @Override
            public void ErroImageUplod(String Error) {
                alertDialog.dismiss();
                Toast.makeText(AddPrdocte.this, R.string.failed + " " + Error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void statusInsert(boolean status) {
                alertDialog.dismiss();
                Toast.makeText(AddPrdocte.this, R.string.succes, Toast.LENGTH_LONG).show();
                PN.setText(null);
                // PQ.setText(null);
                PP.setText(null);
                PC.setText(null);
                bindin.PhotoName.setText(R.string.path_of_photo);
                if (alertDialog.isShowing())
                    alertDialog.dismiss();
            }

            @Override
            public void ErroInsert(String Error) {
                alertDialog.dismiss();
                Toast.makeText(AddPrdocte.this, R.string.failed + " " + Error, Toast.LENGTH_LONG).show();

            }
        });
    }

    private String getFileExtension(Uri mUri) {

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}