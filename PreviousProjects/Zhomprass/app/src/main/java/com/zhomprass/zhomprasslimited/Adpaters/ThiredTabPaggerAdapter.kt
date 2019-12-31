package com.zhomprass.zhomprasslimited.Adpaters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ThiredTabPaggerAdapter(fm: FragmentManager) :FragmentStatePagerAdapter(fm) {

    private val fragmentList: MutableList<Fragment> = java.util.ArrayList()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size

    }


    public fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }
}