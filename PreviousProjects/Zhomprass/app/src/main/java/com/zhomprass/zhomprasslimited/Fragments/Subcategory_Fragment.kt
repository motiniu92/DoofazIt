package com.zhomprass.zhomprasslimited.Fragments


import CatagoryList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.CatagorListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.SubCategoryListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
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
class Subcategory_Fragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<SubCategoryList>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_subcategory_, container, false)
        recyclerView=view.findViewById(R.id.subCategoryRec)
        recyclerView.layoutManager=GridLayoutManager(activity,3)


        return view
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        getSubCat()


    }



    private fun getSubCat(){
        SharedPrefManager.getInstance(context)?.getSelectedCatagory()?.let {
            ApiClient.instance.getSubCategory(it)
                .enqueue(object :Callback<List<SubCategoryList>>{
                    override fun onFailure(call: Call<List<SubCategoryList>>, t: Throwable) {
                        println(t.message)
                    }

                    override fun onResponse(
                        call: Call<List<SubCategoryList>>,
                        response: Response<List<SubCategoryList>>
                    ) {
                        if (response.isSuccessful){

                            if (response.body()==null){
                                return
                            }
                            list= response.body() as ArrayList<SubCategoryList>


                            if (list !=null){
                                recyclerView.adapter= activity?.let { SubCategoryListAdapter(list, it) }
                                SharedPrefManager.getInstance(activity)
                            }

                        }
                    }

                })
        }

    }






}
