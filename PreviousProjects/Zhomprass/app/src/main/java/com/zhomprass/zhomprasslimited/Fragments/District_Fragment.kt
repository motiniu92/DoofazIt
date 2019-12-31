package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.DistrictListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.District
import com.zhomprass.zhomprasslimited.Models.Division
import com.zhomprass.zhomprasslimited.Models.Thana

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class District_Fragment : Fragment() {

    lateinit var district: ArrayList<District>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_district_, container, false)
        recyclerView=view.findViewById(R.id.divisionRec)
        recyclerView.layoutManager= GridLayoutManager(activity,3)
        recyclerView.setHasFixedSize(true)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDistrict()

    }



    private fun getDistrict(){
        SharedPrefManager.getInstance(activity)?.getSelectedDistrictId()?.let {
            ApiClient.instance.getDistrict(it)
                .enqueue(object : Callback<List<District>> {
                    override fun onFailure(call: Call<List<District>>, t: Throwable) {

                        println(t.message)

                    }

                    override fun onResponse(
                        call: Call<List<District>>,
                        response: Response<List<District>>
                    ) {

                        if (response.isSuccessful){
                            if (response.body()==null){
                                return
                            } else{
                                district=response.body() as ArrayList<District>

                                val adapter= activity?.let { DistrictListAdapter(district, it) }
                                recyclerView.adapter=adapter
                            }
                        }


                    }

                })
        }
    }








}