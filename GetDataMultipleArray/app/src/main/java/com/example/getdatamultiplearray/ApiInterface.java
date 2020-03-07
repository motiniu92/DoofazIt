package com.example.getdatamultiplearray;


import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {




    @GET("https://zhomprass.com/app1/api/customer_dashboard.php?customer_id=15&type=generation&fbclid=IwAR2H6HpnFBS7x3RaSRzceTBM0tfFhHRJ0t0gR0BmAYWIwC0DVYE4ZKTu9I8")
    Call<JsonArray> getMultipleArray(@Query("customer_id") int cust_id);
}
