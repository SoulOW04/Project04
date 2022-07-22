package com.example.loginregister.services;

import com.example.loginregister.models.ApiResponse;

import java.util.ArrayList;
public interface IUserResponse {
    public void login(ApiResponse apiResponse, String error);
}
