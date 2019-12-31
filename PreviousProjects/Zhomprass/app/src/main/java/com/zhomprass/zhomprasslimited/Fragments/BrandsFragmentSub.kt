package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.SubBrandsListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.BrandList
import com.zhomprass.zhomprasslimited.Models.SubBrandList
import com.zhomprass.zhomprasslimited.Models.SubCategoryList

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class BrandsFragmentSub : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var subList: ArrayList<SubBrandList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_brands_fragment_sub, container, false)
        recyclerView=view.findViewById(R.id.subBrandsRecycler)
        recyclerView.layoutManager= GridLayoutManager(activity,3)


        getBrandsSub()


        return view
    }






    private fun getBrandsSub(){
        SharedPrefManager.getInstance(activity)?.getSelectedCatagory()?.let {
            ApiClient.instance.getBrandsWithSub(it)
                .enqueue(object :Callback<List<SubBrandList>>{
                    override fun onFailure(call: Call<List<SubBrandList>>, t: Throwable) {

                        println(t.message)
                    }

                    override fun onResponse(
                        call: Call<List<SubBrandList>>,
                        response: Response<List<SubBrandList>>
                    ) {
                        if (response.isSuccessful){
                            if (response.body()==null){
                                return
                            } else{
                                subList=response.body() as ArrayList<SubBrandList>
                                val adapter= activity?.let { SubBrandsListAdapter(subList, it) }
                                recyclerView.adapter=adapter

                                println("Fucking size is : ${subList.size}")
                            }
                        }
                    }

                })
        }
    }


}
