package com.time.timetec;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME="my_shared_pref";
    private static SharedPrefManager mInstance;
    private Context mContext;

    public SharedPrefManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized SharedPrefManager getInstance(Context mContext){
        if (mInstance==null){
            mInstance=new SharedPrefManager(mContext);
        }
        return mInstance;
    }

    public void saveUser(int id){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("id",id);
        editor.apply();
    }

    public boolean loggedIn(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id",-1)!=-1;
    }


//    public User getUser(){
//        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return new User(
//                sharedPreferences.getInt("id",-1),
//                sharedPreferences.getString("email",null),
//                sharedPreferences.getString("name",null)
//        );
//    }


    public void clear(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
