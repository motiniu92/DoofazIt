package com.zhomprass.zhomprasslimited.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhomprass.zhomprasslimited.Adpaters.SubCategoryListAdapter
import com.zhomprass.zhomprasslimited.Adpaters.ThiredCategoryListAdapter
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.SubCategoryList
import com.zhomprass.zhomprasslimited.Models.ThiredCategoryList

import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ThiredCategoryFragment : Fragment() {


    lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<ThiredCategoryList>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_thired_category, container, false)
        recyclerView=view.findViewById(R.id.ThiredCategoryRec)
        recyclerView.layoutManager= GridLayoutManager(activity,3)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getThiredCat()


    }




    private fun getThiredCat(){
        SharedPrefManager.getInstance(context)?.getSelectedCatagoryThired()?.let {
            ApiClient.instance.getThiredCategory(it)
                .enqueue(object : Callback<List<ThiredCategoryList>> {
                    override fun onFailure(call: Call<List<ThiredCategoryList>>, t: Throwable) {
                        println(t.message)
                    }

                    override fun onResponse(
                        call: Call<List<ThiredCategoryList>>,
                        response: Response<List<ThiredCategoryList>>
                    ) {
                        if (response.isSuccessful){

                            if (response.body()==null){
                                return
                            }
                            list= response.body() as ArrayList<ThiredCategoryList>


                            if (list !=null){
                                recyclerView.adapter= activity?.let { ThiredCategoryListAdapter(list, it) }
                                SharedPrefManager.getInstance(activity)
                            }

                        }
                    }

                })
        }

    }


}
