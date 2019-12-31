package com.zhomprass.zhomprasslimited.Activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zhomprass.zhomprasslimited.R

class WebViewActivity : AppCompatActivity() {
    lateinit var webPage:WebView
    private val link = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webPage = findViewById(R.id.webview_id)
        val bundle = intent.extras
        var link = bundle!!.getString("link")




        if (link.equals("about")) {
            webPage.loadUrl("https://zhomprass.com/About")
            webPage.getSettings().javaScriptEnabled = true
            webPage.setWebViewClient(WebViewClient())
        }


        if (link.equals("tc")) {
            webPage.loadUrl("https://zhomprass.com/TERMS")
            webPage.getSettings().javaScriptEnabled = true
            webPage.setWebViewClient(WebViewClient())
        }


        if (link.equals("privacyPolicy")) {
            webPage.loadUrl("https://zhomprass.com/PRIVACY")
            webPage.getSettings().javaScriptEnabled = true
            webPage.setWebViewClient(WebViewClient())
        }


    }

}