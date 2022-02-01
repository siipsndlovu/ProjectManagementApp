package com.example.projectmanagamentapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClass {

    private static final String BaseUrl = "http://web.socem.plymouth.ac.uk/COMP2000/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
