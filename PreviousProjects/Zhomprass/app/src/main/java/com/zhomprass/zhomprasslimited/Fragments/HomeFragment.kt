package com.zhomprass.zhomprasslimited.Fragments


import CatagoryList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.amulyakhare.textdrawable.TextDrawable
import com.google.android.material.tabs.TabLayout
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.zhomprass.zhomprasslimited.Adpaters.OffersAdapter
import com.zhomprass.zhomprasslimited.Adpaters.RecentProductAdapter
import com.zhomprass.zhomprasslimited.Adpaters.SliderApapter
import com.zhomprass.zhomprasslimited.Adpaters.TabPaggerAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.*

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    private lateinit var tabLayout:TabLayout
    private lateinit var viewPagger:ViewPager
    private lateinit var sliderView: SliderView
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<Offers>
    private lateinit var sliderImage: ArrayList<SliderImage>
    private lateinit var recentProductList: ArrayList<RecentProduct>
    private lateinit var tempProductList: ArrayList<RecentProduct>
    private lateinit var userShortInfo: ArrayList<UserShortInfo>
    private lateinit var recentProductRecyler:RecyclerView
    private lateinit var layoutManager:GridLayoutManager
    lateinit var progressBar: ProgressBar
    private var isScrolled:Boolean=false
    private var currentItem:Int?=null
    private var totalItem:Int?=null
    private var scrolledItem:Int?=null
    private var counter:Int=0
    private var recentApapter:RecentProductAdapter?=null
    private lateinit var levelImage: ImageView
    private lateinit var positionImage: ImageView
    private lateinit var rankImage: ImageView
    private lateinit var availbleImage: ImageView




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home
            , container, false)

        tabLayout=view.findViewById(R.id.tab_layout)
        viewPagger=view.findViewById(R.id.productdetails_viewPager)
        sliderView=view.findViewById<SliderView>(R.id.imageSlided)
        recyclerView=view.findViewById(R.id.offerRecyclerView)
        levelImage=view.findViewById(R.id.levelImageView)
        positionImage=view.findViewById(R.id.positionImage)
        rankImage=view.findViewById(R.id.rankImage)
        availbleImage=view.findViewById(R.id.availableImage)
     //   progressBar=view.findViewById(R.id.progressBar)
       recyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.setHasFixedSize(true)

        recentProductRecyler=view.findViewById(R.id.recentProductsRecycler)
        layoutManager= GridLayoutManager(activity,2)
//        recentProductRecyler.layoutManager=StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recentProductRecyler.layoutManager=layoutManager






        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSliderImage()
        setUpViewPagger()
        getOffers()
        getRecentProducts()
        scrolling()


        if (SharedPrefManager.getInstance(activity)!!.userId()==0){
            setUserInfoWithOutLogin()

        }


        if (SharedPrefManager.getInstance(activity)!!.userId()!=0){
            getUserShortInfo()
        }





    }




    private fun setUpViewPagger(){

        val adapter= fragmentManager?.let { TabPaggerAdapter(it) }

        adapter?.addFragment(CatogoryFragment())
        adapter?.addFragment(BrandsFragment())
        adapter?.addFragment(ShopsFragment())



        viewPagger.adapter=adapter


        viewPagger.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))


        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {


            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPagger!!.currentItem = p0!!.position
            }

        })



    }




    private fun setUserInfoWithOutLogin(){
        val levelDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("0", resources.getColor(R.color.blue_ribbon),50)

        val positionDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("0", resources.getColor(R.color.torch_red),50)

        val rankDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("0", resources.getColor(R.color.supernova),50)

        val avaibleDrawable = TextDrawable.builder()
             .beginConfig().fontSize(20).endConfig().buildRoundRect("00.0", resources.getColor(R.color.fern),50)



        levelImage.setImageDrawable(levelDrawable)
        positionImage.setImageDrawable(positionDrawable)
        rankImage.setImageDrawable(rankDrawable)
        availbleImage.setImageDrawable(avaibleDrawable)
    }


    private fun setUserInfoWithLogin(level:String,positon:Int,rank:Int,avaible:Double){
        val levelDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("$level", resources.getColor(R.color.blue_ribbon),50)

        val positionDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("$positon", resources.getColor(R.color.torch_red),50)

        val rankDrawable = TextDrawable.builder().beginConfig().fontSize(30).endConfig().buildRoundRect("$rank", resources.getColor(R.color.supernova),50)

        val avaibleDrawable = TextDrawable.builder()
            .beginConfig().fontSize(20).endConfig().buildRoundRect("$avaible", resources.getColor(R.color.fern),50)



        levelImage.setImageDrawable(levelDrawable)
        positionImage.setImageDrawable(positionDrawable)
        rankImage.setImageDrawable(rankDrawable)
        availbleImage.setImageDrawable(avaibleDrawable)
    }


    private fun setUpSlider(imageList:ArrayList<SliderImage>){


        val adpater= activity?.let { SliderApapter(it,imageList) }
        sliderView.sliderAdapter=adpater
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.scrollTimeInSec = 4
    }



    private fun getUserShortInfo(){
        ApiClient.instance.getUserInfo(SharedPrefManager.getInstance(activity)!!.userId())
            .enqueue(object :Callback<List<UserShortInfo>>{
                override fun onFailure(call: Call<List<UserShortInfo>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<UserShortInfo>>,
                    response: Response<List<UserShortInfo>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            setUserInfoWithOutLogin()
                            return
                        }
                        else{
                            userShortInfo=response.body() as ArrayList<UserShortInfo>
                            setUserInfoWithLogin(userShortInfo.get(0).zpl,userShortInfo.get(0).position,userShortInfo.get(0).rank,userShortInfo.get(0).available_balance)

                        }
                    }
                }

            })

    }


    private fun getOffers(){
        ApiClient.instance.getOffers(1).enqueue(
            object :Callback<List<Offers>>{
                override fun onFailure(call: Call<List<Offers>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<Offers>>,
                    response: Response<List<Offers>>
                ) {

                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }
                        else{
                            list= response.body() as ArrayList<Offers>
                            val adapter= activity?.let { OffersAdapter(list, it) }
                            recyclerView.adapter=adapter


                        }
                    }
                }

            }
        )
    }



    private fun getRecentProducts(){
        ApiClient.instance.getRecentProduct(1).enqueue(
            object :Callback<List<RecentProduct>>{
                override fun onFailure(call: Call<List<RecentProduct>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<RecentProduct>>,
                    response: Response<List<RecentProduct>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }
                        else{
                            recentProductList=response.body() as ArrayList<RecentProduct>

                           recentApapter= activity?.let {
                               RecentProductAdapter(recentProductList,
                                   it
                               )
                           }


                            recentProductRecyler.adapter=recentApapter




                        }
                    }
                }


            }
        )
    }


    private fun getSliderImage(){
        ApiClient.instance.getSliderImage()
            .enqueue(object :Callback<List<SliderImage>>{
                override fun onFailure(call: Call<List<SliderImage>>, t: Throwable) {
                    println(t.message
                    )
                }

                override fun onResponse(
                    call: Call<List<SliderImage>>,
                    response: Response<List<SliderImage>>
                ) {
                    if (response.isSuccessful){
                        if (response.body() == null){
                            return
                        }

                        else{
                            sliderImage=response.body() as ArrayList<SliderImage>
                            setUpSlider(sliderImage)

                        }
                    }
                }

            })
    }


    private fun scrolling(){
        recentProductRecyler.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("scrolled method is called")


                currentItem=layoutManager.childCount
                totalItem=layoutManager.itemCount
                scrolledItem=layoutManager.findFirstVisibleItemPosition()
                println("current item $currentItem")
                println("total item $totalItem")
                println("scroller item $scrolledItem")


                if (isScrolled && (currentItem!! + scrolledItem!! ==totalItem)){
                    isScrolled=false
                    dataFetch()

                }




            }


            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    println("scrolling method is called and state $isScrolled")
                    currentItem=layoutManager.childCount
                    totalItem=layoutManager.itemCount
                    scrolledItem=layoutManager.findFirstVisibleItemPosition()


                    isScrolled=true
                }

            }
        })
    }

    private fun dataFetch() {

        println("data fetch is called")
      //  progressBar.visibility=View.VISIBLE
        var end=counter+10
        ApiClient.instance.getScrolledProduct(counter,end)
            .enqueue(object :Callback<List<RecentProduct>>{
                override fun onFailure(call: Call<List<RecentProduct>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<RecentProduct>>,
                    response: Response<List<RecentProduct>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                         //   progressBar.visibility=View.GONE
                            return
                        }
                        else{
                            tempProductList=response.body() as ArrayList<RecentProduct>
                            println("temp size is ${tempProductList.size}")


                            recentProductList.addAll(tempProductList)
                            tempProductList.clear()

                            println("recent size is ${recentProductList.size}")


                            recentApapter?.notifyDataSetChanged()
                           // progressBar.visibility=View.GONE




                        }
                    }

                }

            })

        counter=end
    }


}
