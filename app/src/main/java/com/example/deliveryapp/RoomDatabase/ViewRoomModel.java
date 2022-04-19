package com.example.deliveryapp.RoomDatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.deliveryapp.Moudle.userInfo;

public class ViewRoomModel extends AndroidViewModel {
    Resportry resportry;
    public ViewRoomModel(@NonNull Application application) {
        super(application);
        resportry = new Resportry(application);
    }

    public void InsertUser(userInfo usar)
    {
        resportry.InsertUser(usar);
    }

    public void DeleteUser(userInfo usar)
    {
        resportry.DeleteUser(usar);
    }

   public LiveData<userInfo> Getlusar(String id) {
        //return
       return resportry.getUser(id);
    }



    public  void InsertCurrentUser(CurrentUser usar) {
        resportry.InsertCurrentUser(usar);
    }


    public void DeleteCurrenUser(CurrentUser usar) {
        resportry.DeleteCurrenUser(usar);
    }

    public  String getCurrenUserId() {
        return resportry.getCurrenUserId();
    }


    public int getCurrenstatus() {
        return resportry.getCurrenstatus();
    }


    public CurrentUser getCurrentUser() {
        return resportry.getCurrentUser();
    }

}
