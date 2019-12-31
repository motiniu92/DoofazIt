package com.zhomprass.zhomprasslimited.Activities

import CatagoryList
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.jsibbold.zoomage.ZoomageView
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.internetIsConnected
import q.rorbin.verticaltablayout.VerticalTabLayout

class ZoomImageActivity : AppCompatActivity() {


    private lateinit var zoomImage:ZoomageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoom_image)
        zoomImage=findViewById(R.id.zoomImage)


        if (!internetIsConnected()){

            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Error",
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.RED)
            snackbar.show()



        }


        val intent=intent
        val imageLink=intent.getStringExtra("image")

        Glide.with(this).load(imageLink).into(zoomImage)




    }





}