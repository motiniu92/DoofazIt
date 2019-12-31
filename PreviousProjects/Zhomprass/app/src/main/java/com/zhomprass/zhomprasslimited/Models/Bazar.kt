package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class Bazar(

    @SerializedName("id") val id : Int,
    @SerializedName("thana_id") val thana_id : Int,
    @SerializedName("name") val name : String
)