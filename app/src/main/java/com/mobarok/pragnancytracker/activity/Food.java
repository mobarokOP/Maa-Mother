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

public class Food extends AppCompatActivity {
    TextView food_text;

    /* access modifiers changed from: protected */
    TextView title01,title02,title03,title04,title05,title06,title07,title08,title09, title10,title11;
    TextView list01,list02,list03,list04,list05,list06,list07,list08,list09;

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
        setContentView((int) R.layout.activity_food);
        EnableEdg2Edge.enable(this);
        loadAd();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.food_list));
        food_text = findViewById(R.id.food_text);
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
        title01 = findViewById(R.id.title01);
        title02 = findViewById(R.id.title02);
        title03 = findViewById(R.id.title03);
        title04 = findViewById(R.id.title04);
        title05 = findViewById(R.id.title05);
        title06 = findViewById(R.id.title06);
        title07 = findViewById(R.id.title07);
        title08 = findViewById(R.id.title08);
        title09 = findViewById(R.id.title09);
        title10= findViewById(R.id.title10);
        title11 = findViewById(R.id.title11);
        list01 = findViewById(R.id.list01);
        list02 = findViewById(R.id.list02);
        list03 = findViewById(R.id.list03);
        list04 = findViewById(R.id.list04);
        list05 = findViewById(R.id.list05);
        list06 = findViewById(R.id.list06);
        list07 = findViewById(R.id.list07);
        list08 = findViewById(R.id.list08);
        list09 = findViewById(R.id.list09);

        food_text.setText(convertToSpanned(DataProvider.t0));
        title01.setText(convertToSpanned(DataProvider.food_array[0]));
        title02.setText(convertToSpanned(DataProvider.food_array[1]));
        title03.setText(convertToSpanned(DataProvider.food_array[2]));
        title04.setText(convertToSpanned(DataProvider.food_array[3]));
        title05.setText(convertToSpanned(DataProvider.food_array[4]));
        title06.setText(convertToSpanned(DataProvider.food_array[5]));
        title07.setText(convertToSpanned(DataProvider.food_array[6]));
        title08.setText(convertToSpanned(DataProvider.food_array[7]));
        title09.setText(convertToSpanned(DataProvider.food_array[8]));
        title10.setText(convertToSpanned(DataProvider.t1));
        title11.setText(convertToSpanned(DataProvider.t2));


        list01.setText(convertToSpanned(DataProvider.food_description[0]));
        list02.setText(convertToSpanned(DataProvider.food_description[1]));
        list03.setText(convertToSpanned(DataProvider.food_description[2]));
        list04.setText(convertToSpanned(DataProvider.food_description[3]));
        list05.setText(convertToSpanned(DataProvider.food_description[4]));
        list06.setText(convertToSpanned(DataProvider.food_description[5]));
        list07.setText(convertToSpanned(DataProvider.food_description[6]));
        list08.setText(convertToSpanned(DataProvider.food_description[7]));
        list09.setText(convertToSpanned(DataProvider.food_description[8]));


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


    private Spanned convertToSpanned(String str) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(str, 0);
        }
        return Html.fromHtml(str);
    }
}
