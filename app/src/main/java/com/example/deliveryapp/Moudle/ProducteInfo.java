package com.example.deliveryapp.Moudle;

import java.io.Serializable;

public class ProducteInfo  implements Serializable {
    String ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,ExtentionImage;



    public ProducteInfo(String producteName, String productePrice, String proudcteImageUri, String proudcteCompanty,String ExtentionImage) {
        ProducteName = producteName;
        ProductePrice = productePrice;
        ProudcteImageUri = proudcteImageUri;
        ProudcteCompanty = proudcteCompanty;
        this.ExtentionImage=ExtentionImage;
    }

    public String getExtentionImage() {
        return ExtentionImage;
    }

    public void setExtentionImage(String extentionImage) {
        ExtentionImage = extentionImage;
    }

    public String getProudcteCompanty() {
        return ProudcteCompanty;
    }

    public void setProudcteCompanty(String proudcteCompanty) {
        ProudcteCompanty = proudcteCompanty;
    }

    public String getProducteName() {
        return ProducteName;
    }

    public void setProducteName(String producteName) {
        ProducteName = producteName;
    }



    public void setProductePrice(String productePrice) {
        ProductePrice = productePrice;
    }

    public void setProudcteImageUri(String proudcteImageUri) {
        ProudcteImageUri = proudcteImageUri;
    }

    public String getProductePrice() {
        return ProductePrice;
    }

    public String getProudcteImageUri() {
        return ProudcteImageUri;
    }



}
