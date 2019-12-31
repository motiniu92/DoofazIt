package com.zhomprass.zhomprasslimited.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.zhomprass.zhomprasslimited.Adpaters.SubTabPaggerAdapter
import com.zhomprass.zhomprasslimited.Fragments.BazarFragment
import com.zhomprass.zhomprasslimited.Fragments.BrandsFragment
import com.zhomprass.zhomprasslimited.Fragments.CatogoryFragment
import com.zhomprass.zhomprasslimited.Fragments.ThanaFragment
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.internetIsConnected

class BazarActvity : AppCompatActivity() {


    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagger: ViewPager
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bazar_actvity)

        tabLayout = findViewById(R.id.tab_layout)
        viewPagger = findViewById(R.id.productdetails_viewPager)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (!internetIsConnected()){

            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Error",
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.RED)
            snackbar.show()



        }


        setUpViewPagger()


    }


    private fun setUpViewPagger() {

        val adapter = SubTabPaggerAdapter(supportFragmentManager)
        adapter?.addFragment(CatogoryFragment())
        adapter?.addFragment(BrandsFragment())
        adapter?.addFragment(BazarFragment())


        tabLayout!!.addTab(tabLayout!!.newTab().setText("Categories"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Brands"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Shops"))
        tabLayout.selectedTabPosition



        viewPagger.adapter = adapter
//
//        viewPagger!!.addOnPageChangeListener(
//            TabLayout.TabLayoutOnPageChangeListener(
//                tabLayout
//            )
//        )

        viewPagger.currentItem=2

        viewPagger.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPagger!!.currentItem = p0!!.position
            }

        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}