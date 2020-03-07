package com.example.getdatamultiplearray;

public class ApiUtils {

    public static final String BASE_URL = "https://zhomprass.com/app1/api/";

    public static ApiInterface getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
