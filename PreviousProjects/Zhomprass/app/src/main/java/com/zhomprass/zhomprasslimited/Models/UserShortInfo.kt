package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class UserShortInfo(

    @SerializedName("zpl") val zpl : String,
    @SerializedName("position") val position : Int,
    @SerializedName("rank") val rank : Int,
    @SerializedName("available_balance") val available_balance : Double
){
}