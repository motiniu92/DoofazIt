package com.zhomprass.zhomprasslimited.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.scwang.wave.MultiWaveHeader
import com.zhomprass.zhomprasslimited.ApiClient.ApiClient
import com.zhomprass.zhomprasslimited.Models.Members
import com.zhomprass.zhomprasslimited.Models.UserInfo
import com.zhomprass.zhomprasslimited.R
import com.zhomprass.zhomprasslimited.Utlis.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    var top_curve: ImageView? = null
    var email: EditText? = null
    var password: EditText? = null
    var zipMember: TextView? = null
    var freeMember: TextView? = null
    var logo1: TextView? = null
    var logo2: TextView? = null
    var logo: TextView? = null
    lateinit var login_button:Button
    lateinit var multiWaveeHeader:ImageView
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        top_curve = findViewById(R.id.top_curve)
        email = findViewById(R.id.ed_name)
        password = findViewById(R.id.ed_pass)
        logo = findViewById(R.id.logo)
        freeMember = findViewById(R.id.new_user_text)
        zipMember = findViewById(R.id.tv_or)
        login_button = findViewById(R.id.nxt)
        logo1 = findViewById(R.id.logo1)
        logo2 = findViewById(R.id.logo2)
        multiWaveeHeader=findViewById(R.id.waveHeader)
        progressBar=findViewById(R.id.progressBar)
        animation()


//        multiWaveeHeader.velocity= 1.0F
//        multiWaveeHeader.progress=1.0F
//        multiWaveeHeader.isRunning
//        multiWaveeHeader.gradientAngle=45
//        multiWaveeHeader.waveHeight=50
//        multiWaveeHeader.startColor=resources.getColor(R.color.orange)
//        multiWaveeHeader.closeColor=resources.getColor(R.color.Aqua)


        getMemberZpl()
        getMemberFree()


        login_button.setOnClickListener(View.OnClickListener {
            login()
        })


    }


    private fun animation() {
        val top_curve_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.top_down)
        top_curve!!.startAnimation(top_curve_anim)
        val editText_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.edittext_anim)
        email!!.startAnimation(editText_anim)
        password!!.startAnimation(editText_anim)
        val field_name_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.field_name_anim)
        logo!!.startAnimation(field_name_anim)
        val center_reveal_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.center_reveal_anim)
        login_button!!.startAnimation(center_reveal_anim)
        val freeMember_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.down_top)
        freeMember!!.startAnimation(freeMember_anim)
        val zipMember_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.down_top)
        zipMember!!.startAnimation(zipMember_anim)
        val logo1_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.down_top)
        logo1!!.startAnimation(logo1_anim)
        val logo2_anim = AnimationUtils.loadAnimation(applicationContext, R.anim.down_top)
        logo2!!.startAnimation(logo2_anim)
    }




    private fun getMemberZpl(){
        ApiClient.instance.getTotalZpl("zpl")
            .enqueue(object :Callback<List<Members>>{
                override fun onFailure(call: Call<List<Members>>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<List<Members>>,
                    response: Response<List<Members>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            logo1!!.text="${response.body()!!.get(0).member}"
                        }
                    }
                }

            })
    }



    private fun getMemberFree(){
        ApiClient.instance.getTotalZpl("free")
            .enqueue(object :Callback<List<Members>>{
                override fun onFailure(call: Call<List<Members>>, t: Throwable) {


                }

                override fun onResponse(
                    call: Call<List<Members>>,
                    response: Response<List<Members>>
                ) {
                    if (response.isSuccessful){
                        if (response.body()==null){
                            return
                        }

                        else{
                            logo2!!.text="${response.body()!!.get(0).member}"
                        }
                    }
                }

            })
    }


    private fun login(){

        if (validation()){

            progressBar.visibility=View.VISIBLE
            var username= email?.text.toString();
            var pass:String= password?.text.toString();

            ApiClient.instance.login(
                username,
                pass
            ).enqueue(object :Callback<List<UserInfo>>{
                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    if (response.isSuccessful){


                      val userInfo=response.body()
                        println(userInfo!!.get(0).login_success)
                        if (userInfo.get(0).login_success==1){
                            SharedPrefManager.getInstance(this@LoginActivity)?.saveUser(userInfo.get(0).customer_id)
                            progressBar.visibility=View.GONE

                            Toast.makeText(this@LoginActivity,"Login Successful",Toast.LENGTH_SHORT).show()
                           startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                        }

                        else{
                            progressBar.visibility=View.GONE

                            Toast.makeText(this@LoginActivity,"Wrong Username And Password",Toast.LENGTH_SHORT).show()

                        }
                    }
                }

            })
        }
    }



    private fun validation():Boolean{
        if (email!!.text.isNullOrEmpty()){
            email!!.error="Enter a username"
            email!!.requestFocus()

            return false

        }

        if (password!!.text.isNullOrEmpty()){
            password!!.error="Enter a password"
            password!!.requestFocus()

            return false

        }

        return true

    }
}
