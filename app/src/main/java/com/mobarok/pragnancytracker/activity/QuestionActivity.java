package com.mobarok.pragnancytracker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.database.DataProvider;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

public class QuestionActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    QAdapter qAdapter;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        EnableEdg2Edge.enable(this);
        loadAd();
        if (Build.VERSION.SDK_INT >= 21) getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("জিজ্ঞাসা");
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
        qAdapter = new QAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(qAdapter);





    }
    private class QAdapter extends RecyclerView.Adapter<QAdapter.ViewHolder>{

        @NonNull
        @Override
        public QAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull QAdapter.ViewHolder holder, int position) {
            holder.question.setText(DataProvider.questions[position]);
            holder.answer.setText(DataProvider.answers[position]);
            holder.question_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (admobAds.isLoaded()){
                        admobAds.show(new AdmobAds.Dismissed() {
                            @Override
                            public void onclick() {
                                if (holder.answer.getVisibility()==View.VISIBLE && holder.line.getVisibility()==View.VISIBLE){
                                    holder.line.setVisibility(View.GONE);
                                    holder.answer.setVisibility(View.GONE);
                                }else {
                                    holder.answer.setVisibility(View.VISIBLE);
                                    holder.line.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }else {
                        if (holder.answer.getVisibility()==View.VISIBLE && holder.line.getVisibility()==View.VISIBLE){
                            holder.line.setVisibility(View.GONE);
                            holder.answer.setVisibility(View.GONE);
                        }else {
                            holder.answer.setVisibility(View.VISIBLE);
                            holder.line.setVisibility(View.VISIBLE);
                        }
                    }

                }
            });
        }

        @Override
        public int getItemCount() {
            return DataProvider.questions.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView question, answer;
            CardView question_card;
            View line;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                question = itemView.findViewById(R.id.question);
                answer = itemView.findViewById(R.id.answer);
                question_card = itemView.findViewById(R.id.question_card);
                line = itemView.findViewById(R.id.line);
            }
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