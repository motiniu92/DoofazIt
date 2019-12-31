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
import com.zhomprass.zhomprasslimited.Activities.DistrictActivity
import com.zhomprass.zhomprasslimited.Activities.ProductsActivity
import com.zhomprass.zhomprasslimited.Activities.SubcategoryActivity
import com.zhomprass.zhomprasslimited.Fragments.HomeFragment
import com.zhomprass.zhomprasslimited.Models.BrandList
import com.zhomprass.zhomprasslimited.Models.Division
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager

class DivisionListAdapter(val list: ArrayList<Division>, val context: Context):RecyclerView.Adapter<DivisionListAdapter.ViewHolder> (){

    var number=0
    var count=0



    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.catagoryNameId)
        val cardView=itemView.findViewById<CardView>(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.division_list_layout,parent,false)

        return ViewHolder(view)

    }
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text=list.get(position).name
        holder.cardView.setOnClickListener(View.OnClickListener {

            val intent=Intent(context, DistrictActivity::class.java)
            SharedPrefManager.getInstance(context)?.saveSelectedDistrictid(list.get(position).id)

            context.startActivity(intent)
        })





    }






}