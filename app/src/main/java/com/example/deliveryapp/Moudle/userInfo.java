package com.example.deliveryapp.Moudle;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "UserTable")
public class userInfo implements Serializable {
    private String FullName,Email,Password,Place,phoneNumber;
    @PrimaryKey
    @NonNull
    private String UIde;
    private  int status;
public userInfo()
{}
    public userInfo(String fullName, String email, String password, String place, String phoneNumber, int status) {
        FullName = fullName;
        Email = email;
        Password = password;
        Place = place;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public String getUIde() {
        return UIde;
    }

    public void setUIde(String UIde) {
        this.UIde = UIde;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
