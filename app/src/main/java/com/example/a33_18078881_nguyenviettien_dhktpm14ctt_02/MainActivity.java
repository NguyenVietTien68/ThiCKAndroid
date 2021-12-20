package com.example.a33_18078881_nguyenviettien_dhktpm14ctt_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signin;
    TextView txtDK;
    String url = "https://60b32e4f1bec230017bf3480.mockapi.io/";
    EditText editEmail,editPass;
    Button btnDN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        editEmail = findViewById(R.id.editEmail);
        editPass = findViewById(R.id.editPass);
        btnDN = findViewById(R.id.btnDangNhap);
        signin = findViewById(R.id.sign_in_button);
        txtDK = findViewById(R.id.txtDk);

        txtDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kiemtra(url);
            }
        });


        signin.setOnClickListener(view->{
            signIn();
        });
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(MainActivity.this, ScreenTransfer.class);
            startActivity(intent);
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            Log.w("ERRor", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void kiemtra(String url) {
        ArrayList<TaiKhoan> lstTaiKhoans = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(url + "taikhoan", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0;i<response.length();i++){
                        JSONObject obj = (JSONObject)response.get(i);
                        String tk = obj.getString("email");
                        String mk = obj.getString("password");

                        TaiKhoan taikhoan = new TaiKhoan(tk,mk);
                        lstTaiKhoans.add(taikhoan);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                String tkn = editEmail.getText().toString().trim();
                String mkn = editPass.getText().toString().trim();
                Boolean i =false;
                for (TaiKhoan taiKhoan : lstTaiKhoans
                ) {
                    if(tkn.equalsIgnoreCase(taiKhoan.getEmail())&& mkn.equalsIgnoreCase(taiKhoan.getPass())){
                        i = true;
                        break;
                    }
                }
                if(i== true){
                    Toast.makeText(MainActivity.this,"Dang nhap thanh cong",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, ScreenTransfer.class));
                }else{
                    Toast.makeText(MainActivity.this,"Dang nhap khong thanh cong",Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}