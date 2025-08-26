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

public class Emergency extends AppCompatActivity {
    String s;
    TextView textView;
    int[] textviews = {R.id.emergency1, R.id.emergency2, R.id.emergency3, R.id.emergency4, R.id.emergency5, R.id.emergency6, R.id.emergency7};

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
        setContentView(R.layout.activity_emergency);
        EnableEdg2Edge.enable(this);
        loadAd();
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.emergency));
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
        for (int i = 0; i < 7; i++) {
            this.s = "" + DataProvider.emergencyTitle[i] + "\n\n" + DataProvider.emergencyData[i];
            if (Build.VERSION.SDK_INT >= 24) {
                spanned = Html.fromHtml(this.s, 0);
            } else {
                spanned = Html.fromHtml(this.s);
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
