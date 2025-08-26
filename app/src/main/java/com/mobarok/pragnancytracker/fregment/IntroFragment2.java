package com.mobarok.pragnancytracker.fregment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobarok.pragnancytracker.tools.CustomPref;
import com.mobarok.pragnancytracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class IntroFragment2 extends Fragment {


    ImageView changeDate;
    TextView currentDate;
    CustomPref customPref;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro2, container, false);
        customPref = new CustomPref(getContext());

        changeDate = view.findViewById(R.id.changeDate);
        currentDate = view.findViewById(R.id.currentDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calculatedCalendar = (Calendar) calendar.clone();
        calculatedCalendar.add(Calendar.DAY_OF_YEAR, 280);
        String calculatedDateStr = dateFormat.format(calculatedCalendar.getTime());
        customPref.setPossibleDate(calculatedDateStr);
        currentDate.setText(dateFormat.format(calendar.getTime()));
        customPref.setDate(dateFormat.format(calendar.getTime()));




        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar minDateCalendar = Calendar.getInstance();
                minDateCalendar.add(Calendar.DAY_OF_YEAR, -280);
                long minDateMillis = minDateCalendar.getTimeInMillis();
                long maxDateMillis = System.currentTimeMillis();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                Calendar calculatedCalendar = (Calendar) calendar.clone();
                                calculatedCalendar.add(Calendar.DAY_OF_YEAR, 280);
                                String calculatedDateStr = dateFormat.format(calculatedCalendar.getTime());
                                currentDate.setText(dateFormat.format(calendar.getTime()));
                                customPref.setDate(dateFormat.format(calendar.getTime()));
                                customPref.setPossibleDate(calculatedDateStr);
                                Toast.makeText(getContext(), ""+dateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.getDatePicker().setMaxDate(maxDateMillis);
                datePickerDialog.getDatePicker().setMinDate(minDateMillis);
                datePickerDialog.show();

            }
        });





        return view;
    }
}