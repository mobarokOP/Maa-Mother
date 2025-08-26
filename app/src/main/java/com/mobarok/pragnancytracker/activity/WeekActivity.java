package com.mobarok.pragnancytracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.database.WeekModel;
import com.mobarok.pragnancytracker.database.WeeklyDataProvider;
import com.mobarok.pragnancytracker.fregment.ImageFragment;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

public class WeekActivity extends AppCompatActivity {
    Toolbar toolbar;

    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    int position1;

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

    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        EnableEdg2Edge.enable(this);
        loadAd();
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        WeeklyDataProvider.addItem();
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("সাপ্তাহিক পরিবর্তন সমূহ");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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

        position1 = getIntent().getIntExtra("position", 0)-2;
        if (position1!=0) position1--;


        // Set up the ViewPager and adapter
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        adapter = new ImagePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager, true);
        }
        tabLayout.getTabAt(position1).select();


        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (tabLayout != null) {
                    tabLayout.getTabAt(position).select();
                }
            }
        });


    }

    public class ImagePagerAdapter extends FragmentPagerAdapter {

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Override
        public Fragment getItem(int position) {
            WeekModel weekModel = WeeklyDataProvider.weeklyData.get(position+3);
            return ImageFragment.newInstance(weekModel);
        }

        @Override
        public int getCount() {
            return 38;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Optional: Set the title of each page in the indicator view (TabLayout)
            return (position + 3)+" সপ্তাহ";
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