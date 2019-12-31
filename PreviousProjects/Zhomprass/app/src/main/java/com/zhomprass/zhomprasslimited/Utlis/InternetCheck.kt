package com.zhomprass.zhomprasslimited.Utlis

import android.content.Context
import com.google.android.material.snackbar.Snackbar
import com.zhomprass.zhomprasslimited.R

fun internetIsConnected(): Boolean {
    return try {
        val command = "ping -c 1 google.com"
        Runtime.getRuntime().exec(command).waitFor() == 0
    } catch (e: Exception) {
        false
    }



}