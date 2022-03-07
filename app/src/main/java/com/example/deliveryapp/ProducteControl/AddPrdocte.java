
package com.example.deliveryapp.ProducteControl;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

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
Uri imageuri,AddersOnFrieStoreg;
    StorageReference storageRef;
    FirebaseFirestore db;
    DateFormat df;
    String filename,extention;
    ProgressDialog progressDialog;
    TextInputEditText PN,PP,PC;
    String ProdutName,ProdutePrice,ProducteCompany;
    ProducteInfo producte;

    boolean imageSeltcted=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindin = ActivityAddPrdocteBinding.inflate(getLayoutInflater());
        setContentView(bindin.getRoot());
        PN=bindin.ProdcteName;
       // PQ=bindin.ProdcteQaenty;
        PP=bindin.ProdctePrice;
        PC=bindin.ProdcteCompany;
        bindin.btnChosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();

            }
        });

        bindin.btUpD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProdutName=PN.getText().toString();
              //  ProducteQuanty=PQ.getText().toString();
                ProdutePrice=PP.getText().toString();
                ProducteCompany=PC.getText().toString();
                if (ProdutName.isEmpty())
                {
                    PN.setError(getString(R.string.errorEmpty));
                    return;
                }

                if (ProdutePrice.isEmpty())
                {
                    PN.setError(getString(R.string.errorEmpty));
                    return;
                }
                if (ProducteCompany.isEmpty())
                {
                    PC.setError(getString(R.string.errorEmpty));
                    return;
                }
             //   int ProductQuanty=Integer.parseInt(ProducteQuanty);
                if(imageSeltcted)
                {
                    UplodImage(ProdutName,ProdutePrice,ProducteCompany);
                }
                else
                {
                    Toast.makeText(AddPrdocte.this, R.string.entiringimag,Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private void SelectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);


    }
void UplodImage(String pn , String pp,String pc)
{
    // pn = ProdutName;
    // pn = ProdutName;
    progressDialog = new ProgressDialog(this);
    progressDialog.setTitle(getString(R.string.up));
    progressDialog.show();
    storageRef = FirebaseStorage.getInstance().getReference("images/"+filename+"."+extention);
    storageRef.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//taskSnapshot.getMetadata().getPath()
            ///storageRef.getPath()
            producte= new ProducteInfo(pn,pp,storageRef.getName(),pc,extention);
            UploadTheData(producte);
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
           // System.out.println("Error is "+e.getMessage() );
            Toast.makeText(AddPrdocte.this, R.string.failed+" "+e.getMessage(),Toast.LENGTH_LONG).show();
            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }
    });
}
void UploadTheData(ProducteInfo producte)
{
    db = FirebaseFirestore.getInstance();
    db.collection("ProducteInfo").add(producte).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
         //   System.out.println("Succses");
            // UplodImage();
            Toast.makeText(AddPrdocte.this, R.string.succes,Toast.LENGTH_LONG).show();
            PN.setText(null);
           // PQ.setText(null);
            PP.setText(null);
            PC.setText(null);
            bindin.PhotoName.setText(R.string.path_of_photo);
            if(progressDialog.isShowing())
                progressDialog.dismiss();

        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(AddPrdocte.this, R.string.failed+" "+e.getMessage(),Toast.LENGTH_LONG).show();

            if(progressDialog.isShowing())
                progressDialog.dismiss();
        }
    });

}



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100&& data!=null)
        {
            Date d = new Date();
            df = new SimpleDateFormat("HH:mm:ss a, dd:MM:yyyy", Locale.getDefault());
            filename = df.format(d);


            imageuri =data.getData();

            extention=getFileExtension(imageuri);
           // bindin.Img.setImageURI(imageuri);
            bindin.PhotoName.setText(filename);
            imageSeltcted=true;

        }
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}