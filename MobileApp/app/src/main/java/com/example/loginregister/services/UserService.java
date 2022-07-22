package com.example.loginregister.services;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.loginregister.models.ApiResponse;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserService {

    private static final String urlLogin = "http://"
            +Api.SERVER_NAME+":"
            +Api.SERVER_PORT
            +"/LoginRegister/signup.php";
    private OkHttpClient okHttpClient = new OkHttpClient();
    public void login (String userName, String password, IUserResponse userResponse) {
        //newCall is "async"
        okHttpClient.newCall(new Request.Builder().url(urlLogin).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //link die, internet failed, 404, internet permission
                        Log.d("failed", "Login failed");
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        //success
                        try {
                            String jsonString = response.body().string();
                            JSONObject jsonObject = new JSONObject(jsonString);
                            //JSONArray responseArray = new JSONArray(jsonString);
                            //userResponse.login(new ApiResponse(sta));
                        }catch (Exception e) {
                            userResponse.login(null, e.getLocalizedMessage());
                        }

                    }
                });
        Log.d("ddd", "do other...");
    }
}
