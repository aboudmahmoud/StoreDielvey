package com.example.deliveryapp.RoomDatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.deliveryapp.Moudle.userInfo;

public class Resportry {
    UserDao userDao;
    CurrentUserDao currentUserDao;

    public Resportry(Application application) {
        //That Code Can Work in main Activity
        MyRoomDatabase dp = MyRoomDatabase.getDatabase(application);
        userDao = dp.userDao();
        currentUserDao = dp.currentUserDao();
    }

    void InsertUser(userInfo usar) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.InsertUser(usar);
            }
        });
    }

    void DeleteUser(userInfo usar) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.DeleteUser(usar);
            }
        });
    }

    LiveData<userInfo> getUser(String Id) {
        return userDao.getUser(Id);
    }


    void InsertCurrentUser(CurrentUser usar) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                currentUserDao.InsertCurrentUser(usar);
            }
        });
    }


    void DeleteCurrenUser(CurrentUser usar) {
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                currentUserDao.DeleteCurrentUser(usar);
            }
        });
    }

    String getCurrenUserId() {
        return currentUserDao.getCurrenUserId();
    }


    int getCurrenstatus() {
        return currentUserDao.getCurrenstatus();
    }


    CurrentUser getCurrentUser() {
        return currentUserDao.getCurrentUser();
    }
}
