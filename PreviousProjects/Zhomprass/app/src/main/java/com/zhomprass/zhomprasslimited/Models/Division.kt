package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class Division (

    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String
){
}