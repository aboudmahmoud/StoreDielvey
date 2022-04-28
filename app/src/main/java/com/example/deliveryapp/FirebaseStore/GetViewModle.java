package com.example.deliveryapp.FirebaseStore;


import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliveryapp.Moudle.ProducteInfo;
import com.example.deliveryapp.Moudle.userInfo;


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Singleton;

public class GetViewModle extends ViewModel {

    public MutableLiveData<ArrayList<ProducteInfo>> prdouctInfi = new MutableLiveData<>();
    public MutableLiveData<TreeMap<String,Uri> >ListOfImage = new MutableLiveData<>();
    static public String ErrorMeesg;
    @Singleton
    StrogPage strogPage;
    public GetViewModle() {
        strogPage = new StrogPage();
    }



    public void getTheUserData(String Email, String Password, getTheData getData) {
        strogPage.getTheUserData(Email, Password, getData);

    }

    public void CheckByEmail(String Email, EmailCheckInfrace emailCheckInfrace) {

        strogPage.CheckByEmail(Email, emailCheckInfrace);
    }

    public void InsertUserData(userInfo use, InsertStatus insertStatus) {

        strogPage.InsertUserData(use, insertStatus);

    }

    public void getLiveProducte() {
        strogPage.getTheProducte(new getingProducteInfo() {
            @Override
            public void getingProducted(ArrayList<ProducteInfo> producteInfos) {
                prdouctInfi.setValue(producteInfos);
            }

            @Override
            public void setImage(TreeMap<String, Uri> listOfImages) {
                ListOfImage.setValue(listOfImages);
            }


            @Override
            public void GetTheError(String ErrorMessg) {
                ErrorMeesg=ErrorMessg;
            }
        });
    }

    public void UploadImagee(String pn, String pp, String pc, String filename, String extention, Uri imageuri, AddingProducteStatus statusCondetion) {

        strogPage.UploadImagee(pn, pp, pc, filename, extention, imageuri, statusCondetion);

    }





}
