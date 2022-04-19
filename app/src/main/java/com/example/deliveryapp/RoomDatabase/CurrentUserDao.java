package com.example.deliveryapp.RoomDatabase;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.deliveryapp.Moudle.userInfo;

@Dao
public interface CurrentUserDao {
    @Insert
    void InsertCurrentUser(CurrentUser usar);

    @Delete
    void DeleteCurrentUser(CurrentUser usar);

    @Query("select Id from StusTable  LIMIT 1")
    String getCurrenUserId();

    @Query("select CruuentLog from StusTable LIMIT 1")
    int getCurrenstatus();

    @Query("select * from StusTable LIMIT 1")
    CurrentUser  getCurrentUser();
}
