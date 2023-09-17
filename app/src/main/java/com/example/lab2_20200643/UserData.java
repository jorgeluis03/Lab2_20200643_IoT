package com.example.lab2_20200643;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserData {

    @SerializedName("results")
    private List<UserProfile> results;

    public List<UserProfile> getResults() {
        return results;
    }

}

