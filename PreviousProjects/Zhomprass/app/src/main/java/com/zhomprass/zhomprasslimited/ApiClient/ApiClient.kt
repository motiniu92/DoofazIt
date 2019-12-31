package com.zhomprass.zhomprasslimited.ApiClient

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object ApiClient {

    private const val BASE_URL="https://zhomprass.com/app1/api/"

   val instance:ApiInterface by lazy {
       val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

       retrofit.create(ApiInterface::class.java)

   }
}


