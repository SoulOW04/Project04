package com.example.loginregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class SignUp extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword,etReenterPassword, etUsername;
    private TextView tvStatus;
    private Button btnRegister;
    private String URL = "http://10.0.2.2/LoginRegister/signup.php";
    private String  fullName , username, email, password, reenterPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etUsername = findViewById(R.id.username);
        etFullName = findViewById(R.id.fullname);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etReenterPassword = findViewById(R.id.reenterpassword);
        btnRegister = findViewById(R.id.buttonSignUp);
        tvStatus = findViewById(R.id.tvStatus);
        username = fullName = email = password = reenterPassword = "";
    }

    public void save(View view) {
        username = etUsername.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        fullName = etFullName.getText().toString().trim();
        reenterPassword = etReenterPassword.getText().toString().trim();
        if(!password.equals(reenterPassword)){
            Toast.makeText(this, "Password Mismatch", Toast.LENGTH_SHORT).show();
        }
        else if(!fullName.equals("") && !username.equals("") && !email.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        //tvStatus.setText("Successfully registered.");
                        Toast.makeText(SignUp.this, "Register Success!", Toast.LENGTH_SHORT).show();
                        btnRegister.setClickable(false);
                    } else if (response.equals("failure")) {
                        tvStatus.setText("Something went wrong!");                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("fullname", fullName);
                    data.put("email", email);
                    data.put("password", password);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
        Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
    }
    }

    public void login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }


}