package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class SliderImage (
    @SerializedName("id") val id : Int,
    @SerializedName("title") val title : String,
    @SerializedName("image") val image : String,
    @SerializedName("url") val url : String
)