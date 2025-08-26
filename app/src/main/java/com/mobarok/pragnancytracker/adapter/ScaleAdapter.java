package com.mobarok.pragnancytracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobarok.pragnancytracker.R;

public class ScaleAdapter extends RecyclerView.Adapter<ScaleAdapter.ViewHolder> {
    Context context;
    int currentWeek;
    Boolean main;
    View view;
    boolean number;


    public ScaleAdapter(Context context, int currentWeek, Boolean main, boolean number) {
        this.context = context;
        this.currentWeek = currentWeek;
        this.main = main;
        this.number = number;
    }

    @NonNull
    @Override
    public ScaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (main){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scale_main, parent, false);
        }else if (number){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_number, parent, false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scale, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScaleAdapter.ViewHolder holder, int position) {
        int i = position+1;
        if (number){
            if (position<9){
                holder.textView.setText("0"+i);
            }else {
                holder.textView.setText(""+i);
            }
        }else {
            if (position<=12){
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.scale_accent));
            }else if (position<=26){
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.scale_));
            }else {
                holder.view.setBackgroundColor(context.getResources().getColor(R.color.scale_dark));
            }
        }
    }

    @Override
    public int getItemCount() {
        return currentWeek;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view);
            if (number){
                textView = itemView.findViewById(R.id.textView);
            }
        }
    }
}
