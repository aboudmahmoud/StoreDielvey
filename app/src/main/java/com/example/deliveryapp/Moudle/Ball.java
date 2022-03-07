package com.example.deliveryapp.Moudle;

import java.util.HashMap;

public class Ball {

    String ProudteName,ProductePrice,TotalProductePrice;
    int ProudcteQunaty;

    public String getTotalProductePrice() {
        return TotalProductePrice;
    }

    public void setTotalProductePrice(String totalProductePrice) {
        TotalProductePrice = totalProductePrice;
    }

    public Ball() {
    }



    public String getProudteName() {
        return ProudteName;
    }

    public void setProudteName(String proudteName) {
        ProudteName = proudteName;
    }

    public String getProductePrice() {
        return ProductePrice;
    }

    public void setProductePrice(String productePrice) {
        ProductePrice = productePrice;
    }

    public int getProudcteQunaty() {
        return ProudcteQunaty;
    }

    public void setProudcteQunaty(int proudcteQunaty) {
        ProudcteQunaty = proudcteQunaty;
    }
}