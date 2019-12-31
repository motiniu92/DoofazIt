package com.zhomprass.zhomprasslimited.Adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhomprass.zhomprasslimited.Activities.ProductsDetailsActivity
import com.zhomprass.zhomprasslimited.Models.RecentProduct
import com.zhomprass.zhomprasslimited.Models.RelatedProduct
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config

class RelatedProductAdapter(val list:ArrayList<RelatedProduct>, val context: Context) : RecyclerView.Adapter<RelatedProductAdapter.ViewHolder>() {


    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val imageView=view.findViewById<ImageView>(R.id.offerProductPicId)
        val text=view.findViewById<TextView>(R.id.offerProductId)
        val price=view.findViewById<TextView>(R.id.offerPrice)
        val cardView=itemView.findViewById<CardView>(R.id.offerCardId)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recent_product_layout,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {



        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(Config.IMAGE_LINE+list.get(position).image).into(holder.imageView)
        holder.text.text="${list.get(position).product_name}"
        holder.price.text="${list.get(position).price} TK"
        holder.cardView.setOnClickListener(View.OnClickListener {
            val intent= Intent(context, ProductsDetailsActivity::class.java)
            intent.putExtra("product_id",list.get(position).id)
            context.startActivity(intent)
        })

    }


}