package com.zhomprass.zhomprasslimited.Adpaters

import CatagoryList
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhomprass.zhomprasslimited.Activities.SubcategoryActivity
import com.zhomprass.zhomprasslimited.Activities.ThiredCategoryActivity
import com.zhomprass.zhomprasslimited.Fragments.HomeFragment
import com.zhomprass.zhomprasslimited.Models.SubCategoryList
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager

class SubCategoryListAdapter(val list: ArrayList<SubCategoryList>, val context: Context):RecyclerView.Adapter<SubCategoryListAdapter.ViewHolder> (){

    var number=0
    var count=0



    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imageView=itemView.findViewById<ImageView>(R.id.catagoryImage)
        val textView=itemView.findViewById<TextView>(R.id.catagoryNameId)
        val cardView=itemView.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.catagory_list_layout,parent,false)

        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        count=list.size
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(Config.IMAGE_LINE+list.get(position).image).centerCrop().fitCenter().into(holder.imageView)
        holder.textView.text=list.get(position).sub_category_name
        holder.cardView.setOnClickListener(View.OnClickListener {

            context.startActivity(Intent(context,ThiredCategoryActivity::class.java))
            SharedPrefManager.getInstance(context)
                ?.thiredCategoryName(list.get(position).id,list.get(position).sub_category_name)


        })





    }






}