package com.example.lab2_20200643;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/api/")
    Call<UserData> getUserData();

}
