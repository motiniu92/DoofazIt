package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.DivisionListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.District
import com.zhomprass.zhomprasslimited.Models.Division
import com.zhomprass.zhomprasslimited.Models.Thana

import com.zhomprass.zhomprasslimited.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ShopsFragment : Fragment() {

    lateinit var division:ArrayList<Division>
    lateinit var district: ArrayList<District>
    lateinit var thana: ArrayList<Thana>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_shops, container, false)
        recyclerView=view.findViewById(R.id.divisionRec)
        recyclerView.layoutManager=GridLayoutManager(activity,3)
        recyclerView.setHasFixedSize(true)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDivision()
    }





    private fun getDivision(){
        ApiClient.instance.getDivision()
            .enqueue(object :Callback<List<Division>>{
                override fun onFailure(call: Call<List<Division>>, t: Throwable) {

                    println(t.message)
                }

                override fun onResponse(
                    call: Call<List<Division>>,
                    response: Response<List<Division>>
                ) {

                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            division=response.body() as ArrayList<Division>

                            val adapter= activity?.let { DivisionListAdapter(division, it) }
                            recyclerView.adapter=adapter

                        }
                    }



                }

            })
    }









}
