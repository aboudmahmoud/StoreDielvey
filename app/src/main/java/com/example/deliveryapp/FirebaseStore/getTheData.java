package com.example.deliveryapp.FirebaseStore;

import androidx.lifecycle.MutableLiveData;

import com.example.deliveryapp.Moudle.userInfo;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;

public interface getTheData {
//Observable<MutableLiveData<userInfo>> userData();
void useingData(userInfo usernfo,int count);

void getError(String Error);

}