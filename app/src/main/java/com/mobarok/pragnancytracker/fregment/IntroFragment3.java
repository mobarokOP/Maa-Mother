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


public class IntroFragment3 extends Fragment {

    ImageView changeDate;
    TextView possibleDate;
    CustomPref customPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro3, container, false);
        customPref = new CustomPref(getContext());
        Calendar calendar = Calendar.getInstance();
        changeDate = view.findViewById(R.id.changeDate);
        possibleDate = view.findViewById(R.id.possibleDate);
        if (!customPref.getPossibleDate().contains("null")) possibleDate.setText(customPref.getPossibleDate());

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calendar.set(year, monthOfYear, dayOfMonth);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                String selectedDateStr = dateFormat.format(calendar.getTime());
                                possibleDate.setText(selectedDateStr);
                                customPref.setPossibleDate(selectedDateStr);
                                Toast.makeText(getContext(), ""+dateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();

                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();

            }
        });

        return view;
    }
}