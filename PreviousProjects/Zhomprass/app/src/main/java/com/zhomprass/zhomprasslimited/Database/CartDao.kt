package com.zhomprass.zhomprasslimited.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zhomprass.zhomprasslimited.Models.MultiProducts
import com.zhomprass.zhomprasslimited.Models.RelatedProduct

@Dao
interface CartDao {

    @Insert
    fun insertToCart(products: RelatedProduct)

    @Delete
    fun delete(products: RelatedProduct)

    @Query("select * from cart_item")
    fun getAll():ArrayList<RelatedProduct>
}