package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScreenTransfer extends AppCompatActivity {
    EditText editBank,editAccount;
    Button btnCheck;
    TextView txtName;
    String url = "https://60b32e4f1bec230017bf3480.mockapi.io/";
    private MyService mMyService;
    private Boolean isConnect = false;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            mMyService =binder.getService();
            isConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect= false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_transfer);
        editBank = findViewById(R.id.editBank);
        editAccount = findViewById(R.id.editAcc);
        txtName = findViewById(R.id.txtNameTim);
        btnCheck = findViewById(R.id.btnCheck);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kiemtra(url);
            }
        });
    }

    private void kiemtra(String url) {
        ArrayList<Account> lstTaiKhoans = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(url + "Acc", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0;i<response.length();i++){
                        JSONObject obj = (JSONObject)response.get(i);
                        String bank = obj.getString("Bank");
                        String account = obj.getString("Account");
                        String name = obj.getString("Name");

                        Account acc = new Account(bank,account,name);
                        lstTaiKhoans.add(acc);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                String bank = editBank.getText().toString().trim();
                String acc = editAccount.getText().toString().trim();
//                txtName.setText(mMyService.LayName(lstTaiKhoans,bank,acc)+"");
                String a = mMyService.LayName(lstTaiKhoans,bank,acc);
                txtName.setText(a+"");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}