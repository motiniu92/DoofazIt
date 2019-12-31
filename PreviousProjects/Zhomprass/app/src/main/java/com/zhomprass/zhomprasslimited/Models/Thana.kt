package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

class Thana(

    @SerializedName("id") val id : Int,
    @SerializedName("district_id") val district_id : Int,
    @SerializedName("name") val name : String
) {
}