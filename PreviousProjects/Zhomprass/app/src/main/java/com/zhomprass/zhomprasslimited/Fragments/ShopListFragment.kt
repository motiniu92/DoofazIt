package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.BazarListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.ShopListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.Bazar
import com.zhomprass.zhomprasslimited.Models.ShopList

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ShopListFragment : Fragment() {

    lateinit var ShopList: ArrayList<ShopList>
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_shop_list, container, false)
        recyclerView = view.findViewById(R.id.divisionRec)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        recyclerView.setHasFixedSize(true)


        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getShopList()

    }


    private fun getShopList(){
        ApiClient.instance.getShop(SharedPrefManager.getInstance(activity)!!.getSelectedShopId())
            .enqueue(object :Callback<List<ShopList>>{
                override fun onFailure(call: Call<List<ShopList>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<ShopList>>,
                    response: Response<List<ShopList>>
                ) {

                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        } else{

                            ShopList=response.body() as ArrayList<ShopList>

                            val adapter= activity?.let { it1 -> ShopListAdapter(ShopList, it1) }

                            recyclerView.adapter=adapter
                        }
                    }
                }

            })
    }




    }






