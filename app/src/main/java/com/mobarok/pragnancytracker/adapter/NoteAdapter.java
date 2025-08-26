package com.mobarok.pragnancytracker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.database.DatabaseNotePad;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context context;
    ArrayList<NoteModel> noteList;
    OnNoteClick onNoteClick;
    OnRefresh onRefresh;

    public NoteAdapter(Context context, ArrayList<NoteModel> noteList, OnNoteClick onNoteClick, OnRefresh onRefresh) {
        this.context = context;
        this.noteList = noteList;
        this.onNoteClick = onNoteClick;
        this.onRefresh = onRefresh;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DatabaseNotePad databaseNotePad = new DatabaseNotePad(context);
        NoteModel noteModel = noteList.get(position);
        holder.title.setText(noteModel.getTitle());
        holder.date.setText(noteModel.getDate());
        holder.details.setText(Html.fromHtml("<b>বিস্তারিত:</b><br/>"+noteModel.getDetails()));
        holder.symptoms.setText(Html.fromHtml("<b>লক্ষণসমূহ:</b><br/>"+noteModel.getSymptoms()));
        holder.itemNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNoteClick.Onclick(noteModel, position);
                if (holder.dLay.getVisibility()==View.GONE){
                    holder.dLay.setVisibility(View.VISIBLE);
                }else holder.dLay.setVisibility(View.GONE);
            }
        });
        holder.deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    databaseNotePad.removeNoteById(noteModel.getId());
                    Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
                    noteList.remove(position);
                    notifyItemRemoved(position);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onRefresh.onClick();
                        }
                    },500);

            }
        });

    }



    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView itemNote;
        TextView title, date, symptoms, details;
        ImageView deleteIcon;
        LinearLayout dLay;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNote = itemView.findViewById(R.id.itemNote);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            deleteIcon = itemView.findViewById(R.id.deleteIcon);
            symptoms = itemView.findViewById(R.id.symptoms);
            details = itemView.findViewById(R.id.details);
            dLay = itemView.findViewById(R.id.dLay);
        }
    }

    public interface OnNoteClick{
        void Onclick(NoteModel noteModel, int position);
    }
    public interface OnRefresh{
        void onClick();
    }
}
