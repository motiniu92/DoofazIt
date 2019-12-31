package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

data class ThiredBrandList(

    @SerializedName("id") val id : Int,
    @SerializedName("cat_id") val cat_id : Int,
    @SerializedName("sub_cat_id") val sub_cat_id : Int,
    @SerializedName("third_cat_id") val third_cat_id : Int,
    @SerializedName("brand_name") val brand_name : String,
    @SerializedName("position") val position : Int,
    @SerializedName("status") val status : Boolean,
    @SerializedName("image") val image : String
) {
}