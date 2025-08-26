package com.mobarok.pragnancytracker.fregment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.bumptech.glide.Glide;
import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.database.WeekModel;


public class ImageFragment extends Fragment {
    WeekModel weekModel;

    public static ImageFragment newInstance(WeekModel weekModel) {
        ImageFragment fragment = new ImageFragment();
        fragment.weekModel = weekModel;
        return fragment;
    }

    TextView tv1, tv2, tv3, tv4, tv5;
    ImageView imageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);
        imageView= view.findViewById(R.id.image);
        tv1.setText(Html.fromHtml(weekModel.getTv1()));
        tv2.setText(Html.fromHtml(weekModel.getTv2()));
        tv3.setText(Html.fromHtml(weekModel.getTv3()));
        tv4.setText(Html.fromHtml(weekModel.getTv4()));
        tv5.setText(Html.fromHtml(weekModel.getTv5()));
        Glide.with(getContext())
                .load(weekModel.getIvLink())
                .into(imageView);



        return view;
    }




}
