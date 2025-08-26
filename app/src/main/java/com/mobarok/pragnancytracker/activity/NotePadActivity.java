package com.mobarok.pragnancytracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.adapter.NoteAdapter;
import com.mobarok.pragnancytracker.adapter.NoteModel;
import com.mobarok.pragnancytracker.database.DatabaseNotePad;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NotePadActivity extends AppCompatActivity {

    FloatingActionButton addNote;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    ArrayList<NoteModel> noteList = new ArrayList<>();
    DatabaseNotePad databaseNotePad;
    BottomSheetDialog addNoteDialog;
    TextInputLayout titleEDLay, symptomsEDLay, dateEdLay, detailsEdLay;
    TextInputEditText titleED, symptomsEd, dateEd, detailsEd;
    Button btnSave;

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

    LinearLayout emptyLay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);
        EnableEdg2Edge.enable(this);
        loadAd();
        databaseNotePad = new DatabaseNotePad(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        emptyLay = findViewById(R.id.emptyLay);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.write_note));
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
        addNote = findViewById(R.id.addNote);
        noteList = databaseNotePad.getNoteList();
        if (noteList.isEmpty()){
            emptyLay.setVisibility(View.VISIBLE);
        }else {
            emptyLay.setVisibility(View.GONE);
        }
        retrieveNote();
        addNewNote();

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()) {
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            if (addNoteDialog.isShowing()){
                                addNoteDialog.dismiss();
                            }else addNoteDialog.show();
                        }
                    });
                } else {
                    if (addNoteDialog.isShowing()){
                        addNoteDialog.dismiss();
                    }else addNoteDialog.show();
                }

            }
        });



    }

    private void addNewNote() {
        addNoteDialog = new BottomSheetDialog(this);
        addNoteDialog.setContentView(R.layout.add_note_layout);
        titleEDLay = addNoteDialog.findViewById(R.id.titleEDLay);
        symptomsEDLay = addNoteDialog.findViewById(R.id.symptomsEDLay);
        detailsEdLay = addNoteDialog.findViewById(R.id.detailsEDLay);
        titleEDLay = addNoteDialog.findViewById(R.id.titleEDLay);
        titleED = addNoteDialog.findViewById(R.id.titleED);
        dateEd = addNoteDialog.findViewById(R.id.dateED);
        symptomsEd = addNoteDialog.findViewById(R.id.symptomsED);
        detailsEd = addNoteDialog.findViewById(R.id.detailsED);
        dateEdLay = addNoteDialog.findViewById(R.id.dateEDLay);
        btnSave = addNoteDialog.findViewById(R.id.btnSave);
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        dateEd.setText(dateFormat.format(currentDate));
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues();
                if (dateEd.getText().toString().isEmpty()){
                    dateEdLay.setError("তারিখ দিন");
                } else if (titleED.getText().toString().isEmpty()){
                    titleEDLay.setError("নোট এর নাম দিন");
                } else if (symptomsEd.getText().toString().isEmpty()){
                    symptomsEDLay.setError("লক্ষণসমূহ লিখুন");
                } else if (detailsEd.getText().toString().isEmpty()){
                    detailsEdLay.setError("বিস্তারিত সমস্যা লিখুন");
                } else {
                    contentValues.put(DatabaseNotePad.KEY_ID, titleED.getText().toString());
                    contentValues.put(DatabaseNotePad.KEY_DATE, dateEd.getText().toString());
                    contentValues.put(DatabaseNotePad.KEY_TITLE, titleED.getText().toString());
                    contentValues.put(DatabaseNotePad.KEY_SYMPTOMS, symptomsEd.getText().toString());
                    contentValues.put(DatabaseNotePad.KEY_DETAILS, detailsEd.getText().toString());
                    if (admobAds.isLoaded()){
                        admobAds.showInstant(new AdmobAds.Dismissed() {
                            @Override
                            public void onclick() {
                                databaseNotePad.addToNote(contentValues,null);
                                Toast.makeText(NotePadActivity.this, "Note Added Success", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(NotePadActivity.this, NotePadActivity.class));
                                finish();
                            }
                        });
                    }else {
                        databaseNotePad.addToNote(contentValues,null);
                        Toast.makeText(NotePadActivity.this, "Note Added Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(NotePadActivity.this, NotePadActivity.class));
                        finish();
                    }
                }
            }
        });


    }

    private void retrieveNote() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(this, noteList, new NoteAdapter.OnNoteClick() {
            @Override
            public void Onclick(NoteModel noteModel, int position) {
                if (admobAds.isLoaded()) admobAds.show();
            }
        }, new NoteAdapter.OnRefresh() {
            @Override
            public void onClick() {
                startActivity(new Intent(NotePadActivity.this, NotePadActivity.class));
                finish();
            }
        });
        recyclerView.setAdapter(noteAdapter);


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