package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyService extends Service {

    TextView txtTong;
    private MyBinder mBinder = new MyBinder();


    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }



    public String LayName(ArrayList<Account> arrayList,String a, String b){
        for (Account account : arrayList){
            if(a.equalsIgnoreCase(account.getBank())&& b.equalsIgnoreCase(account.getAccount())){
                return account.getName()+"";
            }
        }
        return "Xem lại thông tin";
    }

}
