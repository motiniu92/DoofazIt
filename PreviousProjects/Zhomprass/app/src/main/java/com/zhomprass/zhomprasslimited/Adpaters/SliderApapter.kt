package com.zhomprass.zhomprasslimited.Adpaters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.zhomprass.zhomprasslimited.Models.SliderImage
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config


class SliderApapter(val context: Context,val imameLink:ArrayList<SliderImage>): SliderViewAdapter<SliderApapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup?): SliderApapter.Holder {
        val view=LayoutInflater.from(context).inflate(R.layout.sliding,null)
        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: SliderApapter.Holder?, position: Int) {

        //viewHolder!!.textViewDescription!!.text = "This is slider item $position"

        viewHolder?.imageViewBackground?.let {
            Glide.with(context).load(Config.SLIDER_IMAGE_LINE+imameLink.get(position).image)
                .into(
                    it
                )
        }





    }

    override fun getCount(): Int {
        return imameLink.size
    }


    class Holder(view: View):SliderViewAdapter.ViewHolder(view){
        var itemView = view
        var imageViewBackground=view.findViewById<ImageView>(R.id.iv_auto_image_slider)
       // var textViewDescription: TextView? = view.findViewById(R.id.tv_auto_image_slider)

    }
}