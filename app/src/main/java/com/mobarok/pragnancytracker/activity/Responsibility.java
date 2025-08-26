package com.mobarok.pragnancytracker.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.database.DataProvider;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

public class Responsibility extends AppCompatActivity {
    TextView textView;
    int[] textviews = {R.id.r1, R.id.r2, R.id.r3, R.id.r4, R.id.r5, R.id.r6, R.id.r7, R.id.r8, R.id.r9, R.id.r10, R.id.r11, R.id.r12};



    AdmobAds admobAds;
    private void loadAd(){
        admobAds = new AdmobAds(this)
                .setAdStatus(getResources().getBoolean(R.bool.ADS_ON))
                .setBannerId(getResources().getString(R.string.BANNER_ID))
                .setInterstitialId(getResources().getString(R.string.INTERSTITIAL_ID))
                .setClick(getResources().getInteger(R.integer.AD_INTERVAL))
                .loadBanner(R.id.banner_container)
                .build();

    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        }
        setContentView((int) R.layout.activity_responsibility);
        EnableEdg2Edge.enable(this);
        loadAd();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("দায়িত্ব");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (admobAds.isLoaded()){
                        admobAds.showInstant(new AdmobAds.Dismissed() {
                            @Override
                            public void onclick() {
                                finish();
                            }
                        });
                    }else {
                        finish();
                    }
                }
            });
        }
        setData();
    }

    private void setData() {
        Spanned spanned;
        for (int i = 0; i < DataProvider.responsibility.length; i++) {
            if (Build.VERSION.SDK_INT >= 24) {
                spanned = Html.fromHtml(DataProvider.responsibility[i], 0);
            } else {
                spanned = Html.fromHtml(DataProvider.responsibility[i]);
            }
            ((TextView) findViewById(this.textviews[i])).setText(spanned);
        }
    }

    @Override
    public void onBackPressed() {
        if (admobAds.isLoaded()){
            admobAds.showInstant(new AdmobAds.Dismissed() {
                @Override
                public void onclick() {
                    finish();
                }
            });
        }else {
            finish();
        }
    }
}
