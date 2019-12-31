package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

class ThiredCategoryList(@SerializedName("id") val id : Int,
                         @SerializedName("cat_id") val cat_id : Int,
                         @SerializedName("sub_cat_id") val sub_cat_id : Int,
                         @SerializedName("sub_category_name") val sub_category_name : String,
                         @SerializedName("position") val position : Int,
                         @SerializedName("status") val status : Boolean,
                         @SerializedName("image") val image : String) {
}






