package com.zhomprass.zhomprasslimited.Adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhomprass.zhomprasslimited.Activities.CartActivity
import com.zhomprass.zhomprasslimited.Database.DatabaseHelper
import com.zhomprass.zhomprasslimited.Models.CartProducts
import com.zhomprass.zhomprasslimited.Models.DeleteProducts
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config

class CartProductAdapter(val list: ArrayList<CartProducts>, val cartActivity: CartActivity):RecyclerView.Adapter<CartProductAdapter.ViewHolder> (){


    val databaseHelper=DatabaseHelper(cartActivity)
    val removeList=ArrayList<DeleteProducts>()


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.offerProductPicId)
        val productsName=itemView.findViewById<TextView>(R.id.cartTv)
        val price=itemView.findViewById<TextView>(R.id.textView)
        val productsMul=itemView.findViewById<TextView>(R.id.cartPriceMul)
        val qunatity=itemView.findViewById<TextView>(R.id.countTv)
        val plusButton=itemView.findViewById<ImageButton>(R.id.plusBtn)
        val MinusButton=itemView.findViewById<ImageButton>(R.id.minusBtn)
        val checkbon=itemView.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(cartActivity).inflate(R.layout.cart_product_layout,parent,false)

        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        Glide.with(cartActivity).load(Config.IMAGE_LINE+list.get(position).image).centerCrop().fitCenter().into(holder.imageView)
        holder.productsName.text="${list.get(position).product_name}"
        holder.qunatity.text="${list.get(position).quantity}"
        holder.price.text="৳ ${list.get(position).price*list.get(position).quantity}"
        holder.productsMul.text="${list.get(position).price} X ${list.get(position).quantity}"
        cartActivity.totalpriceTv.text="Total : \u09F3${total()}"


        holder.plusButton.setOnClickListener(View.OnClickListener {
            var count=holder.qunatity.text.toString().toInt()
            count++
            holder.qunatity.text="${count}"
            holder.productsMul.text="${list.get(position).price} X ${count}"
            holder.price.text="৳ ${list.get(position).price*count}"
            databaseHelper.addQuantity(list.get(position).id,count)

            cartActivity.totalpriceTv.text="Total : \u09F3${total()}"





        })


        holder.MinusButton.setOnClickListener(View.OnClickListener {
            var count=holder.qunatity.text.toString().toInt()

            if (count==1){
                return@OnClickListener
            }
            count--
            holder.qunatity.text="${count}"
            holder.productsMul.text="${list.get(position).price} X ${count}"
            holder.price.text="৳ ${list.get(position).price*count}"
            databaseHelper.addQuantity(list.get(position).id,count)

            cartActivity.totalpriceTv.text="Total : \u09F3${total()}"




        })


        holder.checkbon.setOnClickListener(View.OnClickListener {
            if (holder.checkbon.isChecked){
                removeList.add(DeleteProducts(position,list.get(position)))
            }
        })




    }


    fun total():Int{
        val cursor=databaseHelper.getCart();
        var count=0

        if (cursor.count>0){
            while (cursor.moveToNext()){
                count+=cursor.getInt(5)*cursor.getInt(7)
                println("Counting........... $count")
            }
        }

        return count
    }







    fun getRemove():ArrayList<DeleteProducts>{
        return removeList
    }


    fun getRemoveIndex(){
        removeList.removeAt(0)
    }







}