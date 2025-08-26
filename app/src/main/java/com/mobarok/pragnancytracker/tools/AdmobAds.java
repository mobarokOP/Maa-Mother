package com.mobarok.pragnancytracker.tools;


import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class AdmobAds {
    private static final String TAG = "AdNetwork";
    private final Activity activity;
    private ProgressDialog progressDialog;

    InterstitialAd interstitialAd;
    private AdView adView;
    private int counter = 1;
    private Boolean adStatus = true;
    private Dismissed dismissed;
    private String interstitialId = "";
    private String bannerId = "";
    private int interval = 3;

    private boolean loaded = false;
    private boolean withClick;

    //public static boolean appOpenLoaded = false;




    public interface Dismissed {
        void onclick();
    }


    public AdmobAds(Activity activity) {
        this.activity = activity;
    }

    public AdmobAds build() {
        progressDialog = new ProgressDialog(activity);
        loadInterstitialAd();
        return this;
    }


    public AdmobAds init(){
        MobileAds.initialize(activity, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        return this;
    }

    public void show(Dismissed dismissed) {
        withClick = true;
        this.dismissed = dismissed;
        showInterstitialAd();
    }

    public void show() {
        withClick = false;
        showInterstitialAd();
    }

    public void showInstant(Dismissed dismissed) {
        withClick = true;
        this.dismissed = dismissed;
        showInterstitialAdInstant();
    }


    public void showInstant() {
        withClick = false;
        showInterstitialAdInstant();
    }

    public AdmobAds setAdStatus(Boolean adStatus) {
        this.adStatus = adStatus;
        return this;
    }

    public boolean isLoaded(){
        return loaded;
    }


    public AdmobAds setInterstitialId(String interstitialId) {
        this.interstitialId = interstitialId;
        return this;
    }

    public AdmobAds setBannerId(String bannerId) {
        this.bannerId = bannerId;
        return this;
    }


    public AdmobAds setClick(int interval) {
        this.interval = interval;
        return this;
    }


    public void loadInterstitialAd() {
        if (adStatus) {

            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(activity,interstitialId, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAdOP) {
                    interstitialAd = interstitialAdOP;
                    Log.d(TAG, "InterstitialAd is Loaded.");
                    loaded = true;
                    progressDialog.dismiss();
                    //Fullscreen callback || Requesting again when an ad is shown already
                    interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            if (progressDialog.isShowing())progressDialog.dismiss();
                            if (withClick) dismissed.onclick();
                            Log.d(TAG, "The ad was dismissed..");
                            loadInterstitialAd();
                        }

                    }); // FullScreen Callback Ends here


                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    // Handle the error
                    Log.d(TAG, "Ad Failed to load.");
                    interstitialAd = null;
                    progressDialog.dismiss();

                }

            });


        }
    }
    private void showInterstitialAd() {
        if (adStatus) {
            if (counter == interval) {
                if (interstitialAd != null){
                    progressDialog.setMessage("ADS Loading..");
                    progressDialog.show();
                    interstitialAd.show(activity);
                }else {
                    if (withClick) dismissed.onclick();
                    loadInterstitialAd();
                }
                counter = 1;
            } else {
                counter++;
                if (withClick) dismissed.onclick();
            }
            Log.d(TAG, "Current counter : " + counter);
        }
    }

    private void showInterstitialAdInstant() {
        if (adStatus) {
            if (interstitialAd != null){
                progressDialog.setMessage("ADS Loading..");
                progressDialog.show();
                interstitialAd.show(activity);
            }else {
                if (withClick) dismissed.onclick();
                loadInterstitialAd();
            }

        }
    }

    public AdmobAds loadBanner(int adContainerId){
        LinearLayout adContainer = activity.findViewById(adContainerId);
        if (adStatus){
            adView = new AdView(activity);
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId(bannerId);
            adContainer.addView(adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
        return this;
    }










}
