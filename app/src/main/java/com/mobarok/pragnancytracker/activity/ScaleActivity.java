package com.mobarok.pragnancytracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.adapter.ScaleAdapter;
import com.mobarok.pragnancytracker.tools.CustomPref;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ScaleActivity extends AppCompatActivity {

    RecyclerView scaleRecycler, scale, numberRecycler;
    ScaleAdapter scaleAdapter;
    CustomPref customPref;
    TextView result;
    int currentWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);
        EnableEdg2Edge.enable(this);
        customPref = new CustomPref(this);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));
        scaleRecycler = findViewById(R.id.scaleRecycler);
        numberRecycler = findViewById(R.id.numberRecycler);
        scale = findViewById(R.id.scale);
        result = findViewById(R.id.result);
        currentWeek = getIntent().getIntExtra("currentWeek", 1);
        setScale(currentWeek);
        result.setText(GoneDays());
        if (currentWeek<13){
            result.append("১ম ত্রৈমাসিক");
        }else if (currentWeek<27){
            result.append("২য় ত্রৈমাসিক");
        }else {
            result.append("৩য় ত্রৈমাসিক");
        }


    }


    private void setScale(int i) {
        ScaleAdapter scaleMain = new ScaleAdapter(this,45, true, false);
        ScaleAdapter numberAdapter = new ScaleAdapter(this, 45, false, true);
        scaleAdapter = new ScaleAdapter(this,i, true, false);
        scaleRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        scale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        numberRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        numberRecycler.setAdapter(numberAdapter);
        scale.setAdapter(scaleMain);
        scaleRecycler.setAdapter(scaleAdapter);
    }

    private String GoneDays() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date targetDate = null;
        try {
            targetDate = dateFormat.parse(customPref.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date currentDate = Calendar.getInstance().getTime();
        long daysDifference = TimeUnit.DAYS.convert(Math.abs(targetDate.getTime() - currentDate.getTime()), TimeUnit.MILLISECONDS);
        long weeks = daysDifference / 7;
        long remainingDays = daysDifference % 7;

        // Calculate months
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTime(targetDate);
        int targetYear = targetCalendar.get(Calendar.YEAR);
        int targetMonth = targetCalendar.get(Calendar.MONTH);

        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH);

        int monthsPassed = (currentYear - targetYear) * 12 + currentMonth - targetMonth;

        String resultText = "এখন চলছে: "  + weeks + " সপ্তাহ ("+ monthsPassed + " মাস), ";
        return resultText;
    }


}