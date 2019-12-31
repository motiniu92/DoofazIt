package com.zhomprass.zhomprasslimited.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.CartProductAdapter
import com.zhomprass.zhomprasslimited.Database.DatabaseHelper
import com.zhomprass.zhomprasslimited.Models.CartProducts
import com.zhomprass.zhomprasslimited.R

class CartActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper
    private  lateinit var list: ArrayList<CartProducts>
    private lateinit var adapter:CartProductAdapter
    private lateinit var placeOrderButton:Button
    public lateinit var totalpriceTv:TextView
//    private var removeList:ArrayList<Int>? =null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        recyclerView=findViewById(R.id.cartRecycler)
        recyclerView.layoutManager=LinearLayoutManager(this)
        databaseHelper= DatabaseHelper(this)
        placeOrderButton=findViewById(R.id.orderNowBtn);
        totalpriceTv=findViewById(R.id.totalPriceTv)
        list= ArrayList()



        getCartProduct()


        placeOrderButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,OrderActivity::class.java))
        })

    }


        override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.cart_tool,menu)
        return true
    }




    private fun getCartProduct(){
        val cursor=databaseHelper.getCart()
//        Toast.makeText(this,"Item is ${cursor.count}", Toast.LENGTH_LONG).show()


        if (cursor.count>0){
            while (cursor.moveToNext()){
                var cartProducts=CartProducts(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getInt(7),cursor.getString(8))
                list?.add(cartProducts)

            }
             adapter=CartProductAdapter(list,this)
            recyclerView.adapter=adapter



        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.getItemId() == android.R.id.home) {
            finish()
        }

        if (item.itemId== R.id.deleteId){




            println("get Size ${adapter.removeList.size}")
            val removeList=adapter.removeList

            for (i in removeList){
//                println("id ${list.get(i).id}")
                databaseHelper.deleteProducts(list.get(i.position).id)

                list.remove(i.cartProducts)
                adapter.notifyItemRemoved(i.position)

            }

            removeList.clear()

            totalpriceTv.text="Total : ৳${total()}"

            return true
        }
        return super.onOptionsItemSelected(item)
    }



    fun total():Int{
        val cursor=databaseHelper.getCart();
        var count=0

        if (cursor.count>0){
            while (cursor.moveToNext()){
                count+=cursor.getInt(5)*cursor.getInt(7)
                println("Counting........... $count")
            }
        }

        return count
    }
}
