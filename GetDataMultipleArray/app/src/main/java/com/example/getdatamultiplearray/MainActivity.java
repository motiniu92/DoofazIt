package com.example.getdatamultiplearray;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView zplTv, positionTv, rankTv, zplmemberTv, fullNameTv, usernameTv, phoneNoTv, referralAmountTv, generationamountTv, zplamountTv,
            msPointTv, rankamountTv, weekamountTv, dailyamountTv, monthlyamountTv, dealerSpotTv, dealerroyalityTv, dealerReferralTv, totalEarningTv,
            totalConvertTv, totalWithdrawTv, availableBalanceTv;

    private ApiInterface api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullNameTv = findViewById(R.id.fullnameTv);
        usernameTv = findViewById(R.id.usernameTv);
        phoneNoTv = findViewById(R.id.phoneNoTv);
        zplTv = findViewById(R.id.zplTv);
        positionTv = findViewById(R.id.positionTv);
        rankTv = findViewById(R.id.rankTv);
       // zplmemberTv = findViewById(R.id.zplMemberTv);
        referralAmountTv = findViewById(R.id.referralAmountTv);
        generationamountTv = findViewById(R.id.generationamountTv);
        zplamountTv = findViewById(R.id.zplamountTv);
        msPointTv = findViewById(R.id.msPointTv);
        rankamountTv = findViewById(R.id.rankamountTv);
        weekamountTv = findViewById(R.id.weekamountTv);
        dailyamountTv = findViewById(R.id.dailyamountTv);
        monthlyamountTv = findViewById(R.id.monthlyamountTv);
        dealerSpotTv = findViewById(R.id.dealerSpotTv);
        dealerroyalityTv = findViewById(R.id.dealerroyalityTv);
        dealerReferralTv = findViewById(R.id.dealerReferralTv);

        totalEarningTv = findViewById(R.id.totalEarningTv);
        totalConvertTv = findViewById(R.id.totalConvertTv);
        totalWithdrawTv = findViewById(R.id.totalWithdrawTv);
        availableBalanceTv = findViewById(R.id.availableBlanceTv);

        api = ApiUtils.getUserService();

        getData();

    }

    private void getData() {
        Call<JsonArray> call = api.getMultipleArray(15);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        return;
                    } else {
                        try {
                            JSONArray jsonArray = new JSONArray(response.body().toString());
                            usernameTv.setText(jsonArray.getJSONObject(0).getString("user_name"));
                            fullNameTv.setText(jsonArray.getJSONObject(1).getString("full_name"));
                            phoneNoTv.setText(jsonArray.getJSONObject(2).getString("mobile_no"));
                            zplTv.setText("Zpl Lavel : "+jsonArray.getJSONObject(3).getString("zpl_level"));
                            positionTv.setText("Position : "+jsonArray.getJSONObject(4).getString("position"));

                            rankTv.setText("Rank : "+jsonArray.getJSONObject(5).getString("rank"));
                            totalEarningTv.setText(jsonArray.getJSONObject(6).getString("total_earn"));
                            totalConvertTv.setText(jsonArray.getJSONObject(7).getString("total_convert"));
                            totalWithdrawTv.setText(jsonArray.getJSONObject(8).getString("total_withdraw"));
                            availableBalanceTv.setText(jsonArray.getJSONObject(9).getString("available"));
                            referralAmountTv.setText(jsonArray.getJSONObject(10).getString("refer"));
                            generationamountTv.setText(jsonArray.getJSONObject(11).getString("generation"));
                            zplamountTv.setText(jsonArray.getJSONObject(12).getString("zpl"));
                            msPointTv.setText(jsonArray.getJSONObject(13).getString("total_point"));
                            rankamountTv.setText(jsonArray.getJSONObject(14).getString("rank"));
                            weekamountTv.setText(jsonArray.getJSONObject(15).getString("weekly"));
                            dailyamountTv.setText(jsonArray.getJSONObject(16).getString("daily"));
                            monthlyamountTv.setText(jsonArray.getJSONObject(17).getString("monthly"));
                            dealerSpotTv.setText(jsonArray.getJSONObject(18).getString("dealer_spot"));
                            dealerReferralTv.setText(jsonArray.getJSONObject(19).getString("dealer_refer"));
                            dealerroyalityTv.setText(jsonArray.getJSONObject(20).getString("dealer_royality"));





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

            }
        });


    }


}
