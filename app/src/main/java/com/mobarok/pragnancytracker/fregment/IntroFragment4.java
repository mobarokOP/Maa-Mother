package com.mobarok.pragnancytracker.fregment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobarok.pragnancytracker.tools.CustomPref;
import com.mobarok.pragnancytracker.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class IntroFragment4 extends Fragment {
    View view;


    TextView detailsTV;
    CustomPref customPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_intro4, container, false);
        customPref = new CustomPref(getContext());
        detailsTV = view.findViewById(R.id.detailsTV);

        checkRunning();
        checkLeft();





        return view;
    }



    private void checkRunning() {
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
        detailsTV.setText("");
        String resultText = "এখন চলছে: "+weeks+" সপ্তাহ "+remainingDays+" দিন";
        detailsTV.append(resultText);
    }

    private void checkLeft() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date targetDate;
        try {
            targetDate = dateFormat.parse(customPref.getPossibleDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Date currentDate = Calendar.getInstance().getTime();
        long daysDifference = TimeUnit.DAYS.convert(Math.abs(targetDate.getTime() - currentDate.getTime()), TimeUnit.MILLISECONDS);
        long weeks = daysDifference / 7;
        long remainingDays = daysDifference % 7;
        String resultText = "বাকি রয়েছে: " + weeks + " সপ্তাহ " + remainingDays + " দিন (" + daysDifference+")";
        detailsTV.append("\n"+resultText);
    }
}