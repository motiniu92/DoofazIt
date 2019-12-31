package com.zhomprass.zhomprasslimited.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.michaelgarnerdev.materialsearchview.MaterialSearchView
import com.zhomprass.zhomprasslimited.Database.DatabaseHelper
import com.zhomprass.zhomprasslimited.Fragments.DashboardFragment
import com.zhomprass.zhomprasslimited.Fragments.HomeFragment
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import com.zhomprass.zhomprasslimited.Utlis.internetIsConnected


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{



    private lateinit var toolbar:androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    lateinit var databaseHelper: DatabaseHelper
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var materialSearchView: MaterialSearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        drawerLayout=findViewById(R.id.drawer_layout)
        databaseHelper= DatabaseHelper(this)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)
        materialSearchView=findViewById(R.id.material_search_view)

        navigationView=findViewById(R.id.nav_view)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)

        val toogle=ActionBarDrawerToggle(this,drawerLayout,toolbar,
            R.string.open,
            R.string.close
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()



        if (SharedPrefManager.getInstance(this)!!.userId()==0){
            navigationView.menu.removeItem(R.id.logout)
        }


        if (SharedPrefManager.getInstance(this)!!.userId()!=0){
            navigationView.menu.removeItem(R.id.signIn)
        }


        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,HomeFragment()).commit()

        navigationView.setNavigationItemSelectedListener(this)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.cartId ->{
                    startActivity(Intent(this,CartActivity::class.java))
                    true
                }
                R.id.homeIdd ->{
                    materialSearchView.visibility=View.VISIBLE

                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,HomeFragment()).commit()
                    true
                }

                R.id.orderId ->{
                    materialSearchView.visibility=View.GONE
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,DashboardFragment()).commit()
                    true
                }



                else ->
                    true
            }

        }


        if (!internetIsConnected()){

            val snackbar = Snackbar.make(findViewById(android.R.id.content), "Network Error",
                Snackbar.LENGTH_LONG).setAction("Action", null)
            snackbar.setActionTextColor(Color.BLUE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.RED)
            snackbar.show()



        }






    }




//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        menuInflater.inflate(R.menu.toolbar_menu,menu)
//        return true
//    }

//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        if (item.itemId== R.id.locatonId){
//            Toast.makeText(this,"location selected ",Toast.LENGTH_SHORT).show()
//            return true
//        }
//
//        return super.onOptionsItemSelected(item)
//    }



    override fun onNavigationItemSelected(p0: MenuItem): Boolean {


        if (p0.itemId==R.id.signIn){
            startActivity(Intent(this,LoginActivity::class.java))
        }

        if (p0.itemId==R.id.aboutId){
            val intent=Intent(this,WebViewActivity::class.java)
            intent.putExtra("link","about")
            startActivity(intent)
        }

        if (p0.itemId==R.id.privacyPolicyId){
            val intent=Intent(this,WebViewActivity::class.java)
            intent.putExtra("link","privacyPolicy")
            startActivity(intent)
        }

        if (p0.itemId==R.id.tcId){
            val intent=Intent(this,WebViewActivity::class.java)
            intent.putExtra("link","tc")
            startActivity(intent)
        }


        if (p0.itemId== R.id.logout){
            SharedPrefManager.getInstance(this)?.clear()
            Toast.makeText(this,"Logout Successful",Toast.LENGTH_SHORT).show()
            recreate()
            drawerLayout.closeDrawers()

        }



        return true

    }






}
