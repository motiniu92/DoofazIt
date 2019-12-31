package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

class ShopList (

    @SerializedName("shop_id") val shop_id : Int,
    @SerializedName("bazar_id") val bazar_id : String,
    @SerializedName("owner_mobile") val owner_mobile : Int,
    @SerializedName("shop_name") val shop_name : String
) {
}