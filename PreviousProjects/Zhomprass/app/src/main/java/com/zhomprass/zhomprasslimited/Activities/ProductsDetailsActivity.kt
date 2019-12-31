package com.zhomprass.zhomprasslimited.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.zhomprass.zhomprasslimited.Adpaters.ProductSliderApapter
import com.zhomprass.zhomprasslimited.Adpaters.RelatedProductAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Database.AppDatabase
import com.zhomprass.zhomprasslimited.Database.DatabaseHelper
import com.zhomprass.zhomprasslimited.Models.RelatedProduct
import com.zhomprass.zhomprasslimited.Models.SingleProduct
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.Config
import com.zhomprass.zhomprasslimited.Utlis.internetIsConnected
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsDetailsActivity : AppCompatActivity() {

    private lateinit var sliderView: SliderView
    private lateinit var productName:TextView
    private lateinit var productDes:TextView
    private lateinit var productPrice:TextView
    private lateinit var productPoint:TextView
    private lateinit var addToCard:Button
    private lateinit var toolbar: Toolbar
    private lateinit var relatedRecyclerView: RecyclerView
    private lateinit var relatedProductList:ArrayList<RelatedProduct>
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var list:ArrayList<SingleProduct>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)
        productName=findViewById(R.id.productName)
        productDes=findViewById(R.id.descriptionText)
        productPrice=findViewById(R.id.priceTv)
        productPoint=findViewById(R.id.pointTv)
        addToCard=findViewById(R.id.addToCart)
        sliderView=findViewById<SliderView>(R.id.imageSlided)
        toolbar=findViewById(R.id.toolbar)
        relatedRecyclerView=findViewById(R.id.relatedRecyclerView)
        relatedRecyclerView.layoutManager= StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        databaseHelper= DatabaseHelper(this)

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






        val intent=intent

        val id=intent.getIntExtra("product_id",0)


        getProductDescription(id)


        addToCard.setOnClickListener(View.OnClickListener {

            if (databaseHelper.checkCart(list.get(0).id)){
                val snackbar = Snackbar.make(findViewById(android.R.id.content), "This Product All Ready in Cart",
                    Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.setActionTextColor(Color.BLUE)
                val snackbarView = snackbar.view
                snackbarView.setBackgroundColor(Color.RED)
                snackbar.show()

                return@OnClickListener
            }

            databaseHelper.addToCart(list.get(0))

            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Product Add to Cart Successfully",
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.RED)
            snackbar.show()

        })

        //Toast.makeText(this,"size is :"+appDb.getDao().getAll().size,Toast.LENGTH_LONG).show()





    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setUpSlider(image:String){


        val adpater=ProductSliderApapter(this,image)
        sliderView.sliderAdapter=adpater
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM)
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        sliderView.setIndicatorSelectedColor(Color.WHITE)
        sliderView.setIndicatorUnselectedColor(Color.GRAY)
        sliderView.scrollTimeInSec = 4
    }




    private fun getProductDescription(id:Int){

        ApiClient.instance.getSingleProduct(id)
            .enqueue(object :Callback<List<SingleProduct>>{
                override fun onFailure(call: Call<List<SingleProduct>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<SingleProduct>>,
                    response: Response<List<SingleProduct>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            list=response.body() as ArrayList<SingleProduct>


                            productDes.text="${list.get(0).product_description}"
                            productName.text="${list.get(0).product_name}"
                            productPrice.text="${list.get(0).price} TK"
                            productPoint.text="${list.get(0).point} P"
                            setUpSlider(Config.IMAGE_LINE+list.get(0).image)
                            getRelatedProduct(list.get(0).third_cat_id)
                        }
                    }

                }

            })

    }




    private fun getRelatedProduct(id: Int){

        ApiClient.instance.getRelatedProduct(id)
            .enqueue(object :Callback<List<RelatedProduct>>{
                override fun onFailure(call: Call<List<RelatedProduct>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<RelatedProduct>>,
                    response: Response<List<RelatedProduct>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            relatedProductList=response.body() as ArrayList<RelatedProduct>

                            val apapter=RelatedProductAdapter(relatedProductList,this@ProductsDetailsActivity)
                            relatedRecyclerView.adapter=apapter
                        }
                    }

                }

            })

    }



}
