package com.zhomprass.zhomprasslimited.Adpaters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.zhomprass.zhomprasslimited.Activities.ZoomImageActivity
import com.zhomprass.zhomprasslimited.R


class ProductSliderApapter(val context: Context,val imagge:String): SliderViewAdapter<ProductSliderApapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup?): ProductSliderApapter.Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.sliding_2,null)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: ProductSliderApapter.Holder?, position: Int) {

        //viewHolder!!.textViewDescription!!.text = "This is slider item $position"


        when (position) {
            0 -> viewHolder?.itemView?.let {
                Glide.with(it)
                    .load(imagge)
                    .into(viewHolder.imageViewBackground)
            }
            1 -> viewHolder?.itemView?.let {
                Glide.with(it)
                    .load(imagge)
                    .into(viewHolder.imageViewBackground)
            }
            2 -> viewHolder?.itemView?.let {
                Glide.with(it)
                    .load(imagge)
                    .into(viewHolder.imageViewBackground)
            }
            else -> viewHolder?.itemView?.let {
                Glide.with(it)
                    .load(imagge)
                    .into(viewHolder.imageViewBackground)
            }
        }


        viewHolder?.imageViewBackground?.setOnClickListener(View.OnClickListener {
            val intent=Intent(context,ZoomImageActivity::class.java)
            intent.putExtra("image",imagge)
            context.startActivity(intent)
        })



    }

    override fun getCount(): Int {
        return 4
    }


    class Holder(view: View):SliderViewAdapter.ViewHolder(view){
        var itemView = view
        var imageViewBackground=view.findViewById<ImageView>(R.id.iv_auto_image_slider)
       // var textViewDescription: TextView? = view.findViewById(R.id.tv_auto_image_slider)

    }
}