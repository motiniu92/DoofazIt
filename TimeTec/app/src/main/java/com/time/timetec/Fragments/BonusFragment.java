package com.time.timetec.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.time.timetec.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BonusFragment extends Fragment implements RewardedVideoAdListener {

    private Button button;
    private RewardedVideoAd rewardedVideoAd;


    public BonusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_bonus, container, false);

        button=view.findViewById(R.id.showViedo);

        button.setEnabled(false);

        MobileAds.initialize(getActivity(), String.valueOf(R.string.add_mob_id));
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        rewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setEnabled(false);
                if (rewardedVideoAd.isLoaded()){
                    rewardedVideoAd.show();
                }
            }
        });
        return view;
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        button.setEnabled(true);
    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }


    private void loadRewardedVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }
}
