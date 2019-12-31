package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.BazarListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.ThanaListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.Bazar
import com.zhomprass.zhomprasslimited.Models.Thana

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class BazarFragment : Fragment() {

    lateinit var bazar: ArrayList<Bazar>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_bazar, container, false)
        recyclerView = view.findViewById(R.id.divisionRec)
        recyclerView.layoutManager = GridLayoutManager(activity, 3) as RecyclerView.LayoutManager?
        recyclerView.setHasFixedSize(true)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBazar()
    }

    private fun getBazar(){
        SharedPrefManager.getInstance(activity)?.getSelectedBazarId()?.let {
            ApiClient.instance.getBazar(it)
                .enqueue(object : Callback<List<Bazar>> {
                    override fun onFailure(call: Call<List<Bazar>>, t: Throwable) {

                        println(t.message)

                    }

                    override fun onResponse(
                        call: Call<List<Bazar>>,
                        response: Response<List<Bazar>>
                    ) {

                        if (response.isSuccessful){
                            if (response.body()==null){
                                return
                            } else{
                                bazar=response.body() as ArrayList<Bazar>

                                val adapter= activity?.let { it1 -> BazarListAdapter(bazar, it1) }

                                recyclerView.adapter=adapter
                            }
                        }


                    }

                })
        }
    }




}

