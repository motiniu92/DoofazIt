package com.zhomprass.zhomprasslimited.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zhomprass.zhomprasslimited.Adpaters.MultiProductAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.MultiProducts
import com.zhomprass.zhomprasslimited.Models.RelatedProduct
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.internetIsConnected
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    private lateinit var list:ArrayList<MultiProducts>
    private lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        recyclerView=findViewById(R.id.productsRecyclerView)
        recyclerView.layoutManager= GridLayoutManager(this,2)




        if (!internetIsConnected()){

            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Error",
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.RED)
            snackbar.show()



        }

        val intent=intent
        val id=intent.getIntExtra("id",0)
        val flags=intent.getIntExtra("flags",0)


        if (flags==1){
            getMultiProductsByBrand(id)
        }
        else if (flags==3){
            getShopProduct(id)
        }


        else{
            getMultiProducts(id)

        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun getMultiProducts(id:Int){
        ApiClient.instance.getMultiProduct(id)
            .enqueue(object :Callback<List<MultiProducts>>{
                override fun onFailure(call: Call<List<MultiProducts>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<MultiProducts>>,
                    response: Response<List<MultiProducts>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            list=response.body() as ArrayList<MultiProducts>
                            val adapter=MultiProductAdapter(list,this@ProductsActivity)
                            recyclerView.adapter=adapter

                            for (i in list){
                                println(i.product_name)
                            }
                        }
                    }
                }

            })
    }





    private fun getMultiProductsByBrand(id:Int){
        ApiClient.instance.getMultiProductByBrand(id)
            .enqueue(object :Callback<List<MultiProducts>>{
                override fun onFailure(call: Call<List<MultiProducts>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<MultiProducts>>,
                    response: Response<List<MultiProducts>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            list=response.body() as ArrayList<MultiProducts>
                            val adapter=MultiProductAdapter(list,this@ProductsActivity)
                            recyclerView.adapter=adapter

                            for (i in list){
                                println(i.product_name)
                            }
                        }
                    }
                }

            })
    }



    private fun getShopProduct(id:Int){
        ApiClient.instance.getShopProduct(id,0,10)
            .enqueue(object :Callback<List<MultiProducts>>{
                override fun onFailure(call: Call<List<MultiProducts>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<MultiProducts>>,
                    response: Response<List<MultiProducts>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            list=response.body() as ArrayList<MultiProducts>
                            val adapter=MultiProductAdapter(list,this@ProductsActivity)
                            recyclerView.adapter=adapter

                            for (i in list){
                                println(i.product_name)
                            }
                        }
                    }
                }

            })
    }



}
