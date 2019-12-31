package com.time.timetec.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.ads.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import com.time.timetec.Adapters.ViewPagerAdpater
import com.time.timetec.Models.Catagory
import com.time.timetec.R
import com.time.timetec.Retrofit.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private var adpater: ViewPagerAdpater? = null
    private var tableLayout: TabLayout? = null
    private var productViewPager: ViewPager? = null
    private var catagories: List<Catagory>? = null
    private var adView: AdView? = null
    private var interstitialAd: InterstitialAd? = null
    var adRequest: AdRequest? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        MobileAds.initialize(activity, R.string.add_mob_id.toString())
        catagories = ArrayList()
        tableLayout = view.findViewById(R.id.tab_layout)
        productViewPager = view.findViewById(R.id.productdetails_viewPager)
        catagory
        setBannerAds(view)
        return view
    }

    private val catagory: Unit
        private get() {
            val call = ApiClient.getmInstance().api.catagory
            call.enqueue(object : Callback<List<Catagory>?> {
                override fun onResponse(call: Call<List<Catagory>?>, response: Response<List<Catagory>?>) {
                    if (response.isSuccessful) {
                        catagories = response.body()
                        Log.d(TAG, "onResponse: catagory size :" + catagories!!.size)
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get1())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get2())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get3())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get4())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get5())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get6())
                        Log.d(TAG, "onResponse: catagory :" + catagories!![0].get7())
                        setUpViewPager()
                    }
                }

                override fun onFailure(call: Call<List<Catagory>?>, t: Throwable) {}
            })
        }

    private fun setUpViewPager() {
        adpater = ViewPagerAdpater(fragmentManager!!)
        adpater!!.addFragment(ComputerFragment(), "PRODUCTS")
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get1())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get2())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get3())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get4())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get5())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get6())
        adpater!!.addFragment(ComputerFragment(), catagories!![0].get7())
        //        adpater.addFragment(new ComputerFragment(),"Lelin");
//        adpater.addFragment(new ComputerFragment(),"Likhon");
//        adpater.addFragment(new ComputerFragment(),"Mishu");
        productViewPager!!.adapter = adpater
        tableLayout!!.addTab(tableLayout!!.newTab().setText("PRODUCTS"))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get1()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get2()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get3()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get4()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get5()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get6()))
        tableLayout!!.addTab(tableLayout!!.newTab().setText(catagories!![0].get7()))
        productViewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tableLayout))
        tableLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                productViewPager!!.currentItem = tab.position
                if (interstitialAd!!.isLoaded) {
                    interstitialAd!!.show()
                } else {
                    Toast.makeText(activity, "add is loading", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun setBannerAds(view: View) {
        adView = view.findViewById(R.id.adView)
        adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        interstitialAd = InterstitialAd(activity)
        interstitialAd!!.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        interstitialAd!!.loadAd(adRequest)
        adView.setAdListener(object : AdListener() {
            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
            }

            override fun onAdLeftApplication() {
                super.onAdLeftApplication()
            }

            override fun onAdOpened() {
                super.onAdOpened()
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
            }

            override fun onAdImpression() {
                super.onAdImpression()
            }
        })
        interstitialAd!!.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                interstitialAd!!.loadAd(AdRequest.Builder().build())
            }
        }
    }

    private fun setInterstitialAd() {}

    companion object {
        private const val TAG = "HomeFragment"
    }
}