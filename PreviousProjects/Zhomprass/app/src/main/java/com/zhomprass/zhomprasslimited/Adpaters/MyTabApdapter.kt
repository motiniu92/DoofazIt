package com.zhomprass.zhomprasslimited.Adpaters

import CatagoryList
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhomprass.zhomprasslimited.R
import kotlinx.android.synthetic.main.activity_zoom_image.view.*
import q.rorbin.verticaltablayout.TabAdapter
import q.rorbin.verticaltablayout.widget.QTabView

class MyTabApdapter(val list: ArrayList<CatagoryList>,val context: Context,fm: FragmentManager,val image:Int) :TabAdapter,
    FragmentPagerAdapter(fm) {

    private val fragmentList: MutableList<Fragment> = java.util.ArrayList()






    override fun getIcon(position: Int): QTabView.TabIcon? {

//        if (position%2==0){
//            return null
//        }
        return QTabView.TabIcon.Builder().setIcon(image,image).setIconGravity(Gravity.LEFT).setIconSize(150,200)
            .build()
//        Glide.with(context).load(Config.IMAGE_LINE+list.get(position).image).into(return QTabView.TabIcon.Builder().build())
        //return null
    }

    override fun getBadge(position: Int): Int {
        return 0
    }

    override fun getBackground(position: Int): Int {
        return 0

    }

    override fun getTitle(position: Int): QTabView.TabTitle {
        return QTabView.TabTitle.Builder(context)
            .setContent(list.get(position).category_name)
            .setTextColor(Color.GRAY, Color.WHITE)
            .build()

    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]

    }


    override fun getCount(): Int {
        return list.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list.get(position).category_name
    }


}


