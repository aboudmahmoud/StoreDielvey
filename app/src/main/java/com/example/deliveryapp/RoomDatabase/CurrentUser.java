package com.example.deliveryapp.RoomDatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "StusTable")
public class CurrentUser {
    @PrimaryKey
    @NonNull
    private String Id;
    @NonNull
    public  int CruuentLog=0;

    public static boolean CurrentSataus=false;

    public CurrentUser() {

    }

    public CurrentUser(String id, int cruuentLog) {
        Id = id;
        CruuentLog = cruuentLog;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
