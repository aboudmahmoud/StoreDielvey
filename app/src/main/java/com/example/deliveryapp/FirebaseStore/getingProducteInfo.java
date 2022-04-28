package com.example.deliveryapp.FirebaseStore;

import android.net.Uri;

import com.example.deliveryapp.Moudle.ProducteInfo;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public interface getingProducteInfo {
    void getingProducted(ArrayList<ProducteInfo> producteInfos );
    void setImage(TreeMap<String, Uri> listOfImages);

    void GetTheError(String ErrorMessg);
}
