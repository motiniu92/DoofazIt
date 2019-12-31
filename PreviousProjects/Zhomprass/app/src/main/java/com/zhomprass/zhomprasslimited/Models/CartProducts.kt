package com.zhomprass.zhomprasslimited.Models

import com.google.gson.annotations.SerializedName

class CartProducts(
    val id: Int,
    val cat_id: Int,
    val sub_cat_id: Int,
    val third_cat_id: Int,
    val product_name: String,
    val price: Int,
    val point: Int,
    val quantity: Int,
    val image: String
) {
}