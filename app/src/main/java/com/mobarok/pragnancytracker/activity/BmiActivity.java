package com.mobarok.pragnancytracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

public class BmiActivity extends AppCompatActivity {

    private EditText weightEditText;
    private EditText heightFeetEditText;
    private EditText heightInchesEditText;
    private Button calculateButton;
    private TextView bmiResultTextView;
    private ProgressBar bmiProgressBar;
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
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        }
        setContentView((int) R.layout.activity_bmi);
        EnableEdg2Edge.enable(this);
        loadAd();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        setTitle("ওজন");
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

        weightEditText = findViewById(R.id.weightEditText);
        heightFeetEditText = findViewById(R.id.heightFeetEditText);
        heightInchesEditText = findViewById(R.id.heightInchesEditText);
        calculateButton = findViewById(R.id.calculateButton);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        bmiProgressBar = findViewById(R.id.bmiProgressBar);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (admobAds.isLoaded()){
                    admobAds.showInstant(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            calculateBMI();
                        }
                    });
                }else {
                    calculateBMI();
                }

            }
        });

    }
    private void calculateBMI() {
        if (weightEditText.getText().toString().isEmpty()){
            weightEditText.setError(getString(R.string.weight_kg));
        } else if (heightFeetEditText.getText().toString().isEmpty()){
            heightFeetEditText.setError(getString(R.string.height_feet));
        } else if (heightInchesEditText.getText().toString().isEmpty()){
            heightInchesEditText.setError(getString(R.string.height_inches));
        } else {

            double weight = Double.parseDouble(weightEditText.getText().toString());

            int heightFeet = Integer.parseInt(heightFeetEditText.getText().toString());
            int heightInches = Integer.parseInt(heightInchesEditText.getText().toString());
            double heightInchesTotal = (heightFeet * 12) + heightInches;
            double heightCm = heightInchesTotal * 2.54;

            double bmi = weight / ((heightCm / 100) * (heightCm / 100));

            bmiResultTextView.setText(getString(R.string.your_bmi) + String.format("%.2f", bmi));

            int progress = (int) ((bmi / 40) * 100); // Assuming a maximum BMI of 40
            bmiProgressBar.setProgress(progress);
            // Set different color for healthy BMI range (18.5 to 24.9)
            if (bmi >= 18.5 && bmi <= 24.9) {
                Toast.makeText(this, "Your Bmi is Healthy", Toast.LENGTH_SHORT).show();
                bmiProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progress_bar_healthy));
            } else {
                Toast.makeText(this, "Your Bmi is UnHealthy", Toast.LENGTH_SHORT).show();
                bmiProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.custom_progress_bar));
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