package com.example.deliveryapp.Moudle;


import java.io.Serializable;

public class Ball implements Serializable {
ProducteInfo producteInfo;
double TotalPrice;
int Qunaty;

    public Ball(ProducteInfo producteInfo, double totalPrice, int qunaty) {
        this.producteInfo = producteInfo;
        TotalPrice = totalPrice;
        Qunaty = qunaty;
    }


    public ProducteInfo getProducteInfo() {
        return producteInfo;
    }

    public void setProducteInfo(ProducteInfo producteInfo) {
        this.producteInfo = producteInfo;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getQunaty() {
        return Qunaty;
    }

    public void setQunaty(int qunaty) {
        Qunaty = qunaty;
    }
}