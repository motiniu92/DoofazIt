package com.zhomprass.zhomprasslimited.Utlis

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(private val mContext: Context) {



    fun loggedIn(): Boolean {
        val sharedPreferences = mContext.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getInt("id", -1) != -1
    }

    //
//    public User getUser(){
//        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return new User(
//                sharedPreferences.getInt("id",-1),
//                sharedPreferences.getString("email",null),
//                sharedPreferences.getString("name",null)
//        );
//    }
    fun clear() {
        val sharedPreferences = mContext.getSharedPreferences(
            SHARED_PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        editor.commit()
    }


    fun selectCatagory(catagoryId:Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()
        editor.putInt("category_id",catagoryId)
        editor.apply()

    }
    fun getSelectedCatagory(): Int {
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("category_id",0)

    }


    fun selectCatagoryName(catagoryName:String){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()
        editor.putString("category_name",catagoryName)
        editor.apply()

    }

    fun thiredCategoryName(id:Int,name:String){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()
        editor.putString("category_name_thired",name)
        editor.putInt("id_thired",id)
        editor.apply()
    }


    fun getSelectedCatagoryThired(): Int {
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("id_thired",0)

    }

    fun getSelectedCatagoryNameThired(): String? {
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getString("category_name_thired","Sub Categories")

    }


    fun getSelectedCatagoryName(): String? {
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getString("category_name","Sub Categories")

    }


    fun saveUser(id: Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()

        editor.putInt("user_id",id)
        editor.apply()


    }


    fun userId():Int{
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("user_id",0)
    }




    fun saveSelectedDistrictid(id:Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()

        editor.putInt("district_id",id)
        editor.apply()
    }


    fun getSelectedDistrictId():Int{
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("district_id",0)
    }


    fun saveSelectedThanaid(id:Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()

        editor.putInt("thana_id",id)
        editor.apply()
    }


    fun saveSelectedBazarid(id:Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()

        editor.putInt("bazar_id",id)
        editor.apply()
    }



    fun getSelectedThanaId():Int{
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("thana_id",0)
    }



    fun getSelectedBazarId():Int{
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("bazar_id",0)
    }







    fun saveSelectedShopid(id:Int){
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        val editor = sharedPrefManager.edit()

        editor.putInt("shop_id",id)
        editor.apply()
    }



    fun getSelectedShopId():Int{
        val sharedPrefManager=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPrefManager.getInt("shop_id",0)
    }





    companion object {
        private const val SHARED_PREF_NAME = "my_shared_pref"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mContext: Context?): SharedPrefManager? {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mContext!!)
            }
            return mInstance
        }
    }

}