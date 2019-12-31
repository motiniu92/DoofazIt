package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.BrandsListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.CatagorListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.BrandList
import com.zhomprass.zhomprasslimited.Models.SubBrandList

import com.zhomprass.zhomprasslimited.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class BrandsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<BrandList>
    private lateinit var subList: ArrayList<SubBrandList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_brands, container, false)
        recyclerView=view.findViewById(R.id.brandsRecyclerView)
        recyclerView.layoutManager= GridLayoutManager(activity,3)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBrands()
    }



    private fun getBrands(){
        ApiClient.instance.getBrands()
            .enqueue(object : Callback<List<BrandList>> {
                override fun onFailure(call: Call<List<BrandList>>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<BrandList>>,
                    response: Response<List<BrandList>>
                ) {
                    if (response.isSuccessful){
                        list= response.body() as ArrayList<BrandList>
                        if (list.size!=null){
                            val adapter= activity?.let { BrandsListAdapter(list, it) }
                            recyclerView.adapter=adapter
                        }

                    }


                    for (i in list){
                        println(i.brand_name)
                        println(i.id)
                    }
                }

            })

    }




}
