package com.time.timetec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.time.timetec.R;
import com.time.timetec.SharedPrefManager;

public class SplaceActivity extends AppCompatActivity {

    private ProgressBar mprogressBar;
    private boolean InternetCheck = true;
    private static int Splash_time_out = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);

        mprogressBar = findViewById(R.id.progressBar);
        postDelayMethod();
    }

    private void postDelayMethod() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final boolean InternetResult = checkConnection();
                if (InternetResult) {

                    if (SharedPrefManager.getInstance(SplaceActivity.this).loggedIn()){
                        Intent intent = new Intent(SplaceActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_from_left_out);
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplaceActivity.this, LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_from_right,R.anim.slide_from_left_out);
                        finish();
                    }




                } else {

                    mprogressBar.setProgress(View.VISIBLE);
                    mprogressBar.setProgress(View.GONE);

                    Toast.makeText(SplaceActivity.this, "Please check Internet connection!", Toast.LENGTH_SHORT).show();

                }

            }
        }, Splash_time_out);
    }
    protected boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null && info.isConnectedOrConnecting()){
            return true;

        }else {
            return false;
        }
    }
    public boolean checkConnection(){
        if (isOnline()){

            return InternetCheck;
        }else {

            InternetCheck = false;
            return InternetCheck;
        }
    }
}
