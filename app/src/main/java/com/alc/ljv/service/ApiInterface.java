package com.alc.ljv.service;

import com.alc.ljv.model.DataModel;
import com.alc.ljv.model.SingleUserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import retrofit2.http.Url;

public interface ApiInterface {

    @GET("/search/users?q=language:java+location:lagos")
    Call<DataModel> getJavaDevs();

    @GET
    Call<DataModel> getJavaDevs1(@Url String url);

    //   Call<DataModel> getJavaDevs(@Path("location") String Location);
    @GET("/users/{developerName}")
    Call<SingleUserModel> getJavaDevDetails(@Path("developerName") String devName);

}