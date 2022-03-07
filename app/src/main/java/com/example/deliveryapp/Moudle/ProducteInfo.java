package com.example.deliveryapp.Moudle;

public class ProducteInfo {
    String ProducteName,ProductePrice,ProudcteImageUri,ProudcteCompanty,userName,ExtentionImage;



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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
