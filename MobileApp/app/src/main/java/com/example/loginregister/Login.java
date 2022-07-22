package com.example.loginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginregister.models.ApiResponse;
import com.example.loginregister.services.IUserResponse;
import com.example.loginregister.services.UserService;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText etUserName, etPassword;
    private String userName, password;
    private Button btnLogin;
    //private String URL = "http://10.0.2.2/LoginRegister/login.php";
    //192.168.100.5
    private UserService userService = new UserService();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = password = "";
        etUserName = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.buttonLogin);
        etUserName.setText("tit");
        etPassword.setText("123");
        btnLogin.setOnClickListener(view -> this.login(view));


    }
    public void login(View view){
        userName = etUserName.getText().toString().trim();
        password = etPassword.getText().toString();
        if(!userName.equals("") && !password.equals("")){
            userService.login(userName, password, new IUserResponse() {
                @Override
                public void login(ApiResponse apiResponse, String error) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            //Data is here
                            Log.d("haha", "haha");
                        }
                    });
                }
            });
            /*
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        Toast.makeText(Login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        btnLogin.setClickable(false);
                        finish();
                    } else if (response.equals("")) {
                        Toast.makeText(Login.this, "Invalid Login ID, Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){

                @Override
                protected Map<String, String> getParams()  {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", userName);
                    data.put("password", password);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
             */
        }else{
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }
}