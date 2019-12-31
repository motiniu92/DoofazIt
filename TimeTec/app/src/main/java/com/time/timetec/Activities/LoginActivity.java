package com.time.timetec.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.time.timetec.R;
import com.time.timetec.Retrofit.ApiClient;
import com.time.timetec.Models.LoginInfo;
import com.time.timetec.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login";
    private Context mContext;

    ImageView top_curve,logo;
    EditText email,password;
    TextView email_text, password_text;
    LinearLayout new_user_layout;
    CardView login_card;
    List<LoginInfo> loginInfos;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        top_curve = findViewById(R.id.top_curve);
        email = findViewById(R.id.email);
        email_text = findViewById(R.id.email_text);
        password = findViewById(R.id.password);
        password_text = findViewById(R.id.password_text);
        logo = findViewById(R.id.logo);
        new_user_layout = findViewById(R.id.new_user_text);
        login_card = findViewById(R.id.login_card);
        loginInfos=new ArrayList<>();

        progressDialog =new ProgressDialog(this);


        Animation top_curve_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.top_down);
        top_curve.startAnimation(top_curve_anim);

        Animation editText_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.edittext_anim);
        email.startAnimation(editText_anim);
        password.startAnimation(editText_anim);

        Animation field_name_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.field_name_anim);
        email_text.startAnimation(field_name_anim);
        password_text.startAnimation(field_name_anim);
        logo.startAnimation(field_name_anim);

        Animation center_reveal_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.center_reveal_anim);
        login_card.startAnimation(center_reveal_anim);

        Animation new_user_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.down_top);
        new_user_layout.startAnimation(new_user_anim);



    }

    public void register(View view) {
        startActivity(new Intent(LoginActivity.this,Registration.class));

    }

    public void loginButton(View view) {
        // Toast.makeText(this,"Login Clicked",Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(LoginActivity.this,MainActivity.class));


        String email_text=email.getText().toString().trim();
        String passord_text=password.getText().toString().trim();
        login(email_text,passord_text);

    }



    private void login(String email,String password){
        Call<List<LoginInfo>> call= ApiClient.getmInstance().getApi().login(
            email,password
        );



        call.enqueue(new Callback<List<LoginInfo>>() {
            @Override
            public void onResponse(Call<List<LoginInfo>> call, Response<List<LoginInfo>> response) {

                loginInfos=response.body();
                Log.d(TAG, "onResponse: list size : "+loginInfos.size());
                Log.d(TAG, "onResponse: list size : "+loginInfos.get(0).getLoginSuccess());
                Log.d(TAG, "onResponse: list size : "+loginInfos.get(0).getCustomerId());

                if (loginInfos.get(0).getLoginSuccess().equals("1")){
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    SharedPrefManager.getInstance(LoginActivity.this).saveUser(loginInfos.get(0).getCustomerId());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();

                }

                else {
                    Toast.makeText(LoginActivity.this, "Wrong User Name And Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoginInfo>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
