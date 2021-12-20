package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText editEmailDK,editPassDK,editPhoneDK,editNameDK;
    Button btnSave;
    String url = "https://60b32e4f1bec230017bf3480.mockapi.io/";
    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmailDK = findViewById(R.id.editEmailDK);
        editPassDK = findViewById(R.id.editPassDK);
        editPhoneDK = findViewById(R.id.editPhoneDK);
        editNameDK = findViewById(R.id.editNameDK);
        btnSave = findViewById(R.id.btnSave);
        mQueue = Volley.newRequestQueue(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky(url);
                themtk(url);
            }
        });
    }
    private void dangky(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "dangky/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Register.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("email",editEmailDK.getText().toString().trim());
                params.put("pass",editPassDK.getText().toString().trim());
                params.put("phone",editPhoneDK.getText().toString().trim());
                params.put("name",editNameDK.getText().toString().trim());
                return params;
            }
        };
        mQueue.add(stringRequest);
    }
    private void themtk(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url + "taikhoan/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(Register.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Register.this, MainActivity.class));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Register.this, "Thêm không thành công", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("email",editEmailDK.getText().toString().trim());
                params.put("password",editPassDK.getText().toString().trim());
                return params;
            }
        };
        mQueue.add(stringRequest);
    }
}