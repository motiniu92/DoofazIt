package com.zhomprass.zhomprasslimited.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.zhomprass.zhomprasslimited.Models.RelatedProduct
import com.zhomprass.zhomprasslimited.Models.SingleProduct
import java.net.IDN




class DatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,4) {

    companion object{
        val DATABASE_NAME="cart_db"
        val TABLE_NAME="card_table"
        val ID="id"
        val CAT_ID="cat_id"
        val SUB_CAT_ID="sub_cat_id"
        val THIRD_CAT_ID="third_cat_id"
        val PRODUCT_NAME="product_name"
        val PRODUCT_DESCRIPTION="product_description"
        val PRICE="price"
        val POINT="point"
        val QUNATITY="quantity"
        val IMAGE="image"
    }
    override fun onCreate(db: SQLiteDatabase?) {

        val sql="CREATE TABLE "+ TABLE_NAME+" ("+ ID+" INTEGER,"+ CAT_ID+" INTEGER,"+ SUB_CAT_ID+" INTEGER,"+ THIRD_CAT_ID+" INTEGER,"+ PRODUCT_NAME+" VARCHAR(255),"+ PRICE+" INTEGER,"+ POINT+" INTEGER,"+ QUNATITY+" INTEGER,"+ IMAGE+" VARCHAR)";
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)

    }



    fun addToCart(relatedProduct: SingleProduct){

        val db=this.writableDatabase
        var values=ContentValues()
        values.put(ID,relatedProduct.id)
        values.put(CAT_ID,relatedProduct.cat_id)
        values.put(SUB_CAT_ID,relatedProduct.sub_cat_id)
        values.put(THIRD_CAT_ID,relatedProduct.third_cat_id)
        values.put(PRODUCT_NAME,relatedProduct.product_name)
        values.put(PRICE,relatedProduct.price)
        values.put(POINT,relatedProduct.point)
        values.put(QUNATITY,1)
        values.put(IMAGE,relatedProduct.image)

        db.insert(TABLE_NAME,null,values)



    }

    fun addQuantity(id:Int,quantity:Int){

        val db=this.writableDatabase
        db.execSQL("update $TABLE_NAME set $QUNATITY = $quantity where $ID = $id")


    }

    fun deleteProducts(id: Int){
        val db=this.writableDatabase
        db.delete(TABLE_NAME, ID+"="+id,null)
    }


    fun checkCart(id:Int):Boolean{
        val db=this.readableDatabase
        val cursor=db.rawQuery("select $ID from $TABLE_NAME where $ID = $id",null)
        if (cursor.count>0){
            return true
        }
        return false

    }



    fun getCart():Cursor{

        val db=this.readableDatabase
        val sql="SELECT * FROM $TABLE_NAME"
        val cursor=db.rawQuery(sql,null)

        return cursor
    }
}