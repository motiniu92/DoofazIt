package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

class RecentProduct(

    @SerializedName("id") val id : Int,
    @SerializedName("cat_id") val cat_id : Int,
    @SerializedName("sub_cat_id") val sub_cat_id : Int,
    @SerializedName("third_cat_id") val third_cat_id : Int,
    @SerializedName("product_name") val product_name : String,
    @SerializedName("product_description") val product_description : String,
    @SerializedName("price") val price : Int,
    @SerializedName("point") val point : Int,
    @SerializedName("status") val status : Boolean,
    @SerializedName("image") val image : String
) {
}