package com.example.deliveryapp.RoomDatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.deliveryapp.Moudle.userInfo;

@Dao
public interface UserDao {
    @Insert
    void InsertUser(userInfo usar);

    @Delete
    void DeleteUser(userInfo usar);

    @Query("select * from UserTable where UIde=:Id")
    LiveData<userInfo> getUser(String Id);

}
