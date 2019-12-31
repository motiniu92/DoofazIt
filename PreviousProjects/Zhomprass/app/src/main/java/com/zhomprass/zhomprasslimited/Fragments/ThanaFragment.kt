package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.DistrictListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.ThanaListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.District
import com.zhomprass.zhomprasslimited.Models.Thana

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ThanaFragment : Fragment() {

    lateinit var thana: ArrayList<Thana>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_thana, container, false)
        recyclerView = view.findViewById(R.id.divisionRec)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.setHasFixedSize(true)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getThana()

    }

    private fun getThana(){
        SharedPrefManager.getInstance(activity)?.getSelectedThanaId()?.let {
            ApiClient.instance.getThana(it)
                .enqueue(object :Callback<List<Thana>>{
                    override fun onFailure(call: Call<List<Thana>>, t: Throwable) {

                        println(t.message)

                    }

                    override fun onResponse(
                        call: Call<List<Thana>>,
                        response: Response<List<Thana>>
                    ) {

                        if (response.isSuccessful){
                            if (response.body()==null){
                                return
                            } else{
                                thana=response.body() as ArrayList<Thana>

                                val adapter= activity?.let { it1 -> ThanaListAdapter(thana, it1) }

                                recyclerView.adapter=adapter
                            }
                        }


                    }

                })
        }
    }




}

