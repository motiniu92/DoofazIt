package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class UserInfo (

    @SerializedName("login_success") val login_success : Int,
    @SerializedName("customer_id") val customer_id : Int
){
}