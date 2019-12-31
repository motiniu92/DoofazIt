package com.zhomprass.zhomprasslimited.Fragments


import CatagoryList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.CatagorListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.MyTabApdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient

import com.zhomprass.zhomprasslimited.R
import q.rorbin.verticaltablayout.VerticalTabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class CatogoryFragment : Fragment() {



    lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<CatagoryList>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_catogory, container, false)
        recyclerView=view.findViewById(R.id.categoriesRecyclerView)
        recyclerView.layoutManager= GridLayoutManager(activity,3) as RecyclerView.LayoutManager?





        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getCatagory()


    }



    private fun getCatagory(){
        ApiClient.instance.getCatagory()
            .enqueue(object : Callback<List<CatagoryList>> {
                override fun onFailure(call: Call<List<CatagoryList>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<CatagoryList>>,
                    response: Response<List<CatagoryList>>
                ) {
                    if (response.isSuccessful){
                        list= response.body() as ArrayList<CatagoryList>
                            if (list.size!=null){
                                val adapter= activity?.let { CatagorListAdapter(list, it) }
                                recyclerView.adapter=adapter
                            }

                    }


                    for (i in list){
                        println(i.category_name)
                        println(i.id)
                    }
                }

            })

    }






}
