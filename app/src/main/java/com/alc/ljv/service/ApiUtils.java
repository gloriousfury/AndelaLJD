package com.alc.ljv.service;

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://api.github.com";


    public static ApiInterface getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }



}