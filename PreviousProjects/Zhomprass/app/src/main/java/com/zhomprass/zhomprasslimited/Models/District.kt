package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class District(

    @SerializedName("id") val id : Int,
    @SerializedName("division_id") val division_id : Int,
    @SerializedName("name") val name : String
) {
}