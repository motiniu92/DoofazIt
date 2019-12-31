package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.SubBrandsListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.ThiredBrandsListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.SubBrandList
import com.zhomprass.zhomprasslimited.Models.ThiredBrandList

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class BrandsFragmentThired : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var subList: ArrayList<ThiredBrandList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_brands_fragment_thired, container, false)
        recyclerView=view.findViewById(R.id.thiredBrandsRecycler)
        recyclerView.layoutManager= GridLayoutManager(activity,3)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBrandsSub()
    }



    private fun getBrandsSub(){
        SharedPrefManager.getInstance(activity)?.getSelectedCatagoryThired()?.let {
            ApiClient.instance.getBrandsWithThired(it)
                .enqueue(object : Callback<List<ThiredBrandList>> {
                    override fun onFailure(call: Call<List<ThiredBrandList>>, t: Throwable) {

                        println(t.message)
                    }

                    override fun onResponse(
                        call: Call<List<ThiredBrandList>>,
                        response: Response<List<ThiredBrandList>>
                    ) {
                        if (response.isSuccessful){
                            if (response.body()==null){
                                return
                            } else{
                                subList=response.body() as ArrayList<ThiredBrandList>
                                val adapter= activity?.let { ThiredBrandsListAdapter(subList, it) }
                                recyclerView.adapter=adapter

                                println("Fucking size is : ${subList.size}")
                            }
                        }
                    }

                })
        }
    }


}
