package com.time.timetec.Retrofit;

import com.time.timetec.Models.Catagory;
import com.time.timetec.Models.LoginInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("login_api.php")
    Call<List<LoginInfo>> login(
            @Query("user_name") String username,
            @Query("password") String password
    );

    @GET("category_name.php")
    Call<List<Catagory>> getCatagory();



}
