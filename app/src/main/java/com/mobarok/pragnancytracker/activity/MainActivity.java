package com.mobarok.pragnancytracker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.adapter.ScaleAdapter;
import com.mobarok.pragnancytracker.database.DataProvider;
import com.mobarok.pragnancytracker.database.WeekModel;
import com.mobarok.pragnancytracker.database.WeeklyDataProvider;
import com.mobarok.pragnancytracker.tools.AdmobAds;
import com.mobarok.pragnancytracker.tools.CustomPref;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolBar;
    LinearLayout btn1, btn2, btn3, btn4, btn5, btn6, btnBaby, btnDisclaimer, btnHospital;
    CustomPref customPref;
    TextView leftDay, goneDay, goneDay2;
    int currentWeek;
    ImageView imageView;
    TextView detailsTv;
    RelativeLayout viewLay;
    private MediaPlayer mediaPlayer;
    ImageView btnPlay;
    boolean isPlaying = false;
    boolean complete = false;
    RecyclerView scaleRecycler, scale;
    ScaleAdapter scaleAdapter;
    View startScale;
    BottomSheetDialog aboutDialog;

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
        setContentView(R.layout.activity_main);
        EnableEdg2Edge.enable(this);
        loadAd();
        scale = findViewById(R.id.scale);
        scaleRecycler = findViewById(R.id.scaleRecycler);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.primary));


        WeeklyDataProvider.addItem();
        customPref = new CustomPref(this);
        startScale = findViewById(R.id.startScale);
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        setTitle("স্বাগতম");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            // Set the OnClickListener on the home button
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exitPopUp();
                }
            });
        }


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnBaby = findViewById(R.id.btnBaby);
        btnDisclaimer = findViewById(R.id.btnDisclaimer);
        btnHospital = findViewById(R.id.btnHospital);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, Emergency.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, Emergency.class));
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, Food.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, Food.class));
                }

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, Responsibility.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, Responsibility.class));
                }

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, BmiActivity.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, BmiActivity.class));
                }

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, NotePadActivity.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, NotePadActivity.class));
                }

            }
        });


        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (admobAds.isLoaded()){
                    admobAds.show(new AdmobAds.Dismissed() {
                        @Override
                        public void onclick() {
                            startActivity(new Intent(MainActivity.this, QuestionActivity.class));
                        }
                    });
                }else {
                    startActivity(new Intent(MainActivity.this, QuestionActivity.class));
                }

            }
        });



        btnBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HtmlActivity.class);
                intent.putExtra("html",DataProvider.Baby_Care);
                intent.putExtra("title",getResources().getString(R.string.baby_care));
                startActivity(intent);
            }
        });

        btnDisclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HtmlActivity.class);
                intent.putExtra("html", DataProvider.Disclaimer);
                intent.putExtra("title", getResources().getString(R.string.title_disclaimer));
                startActivity(intent);
            }
        });

        btnHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HtmlActivity.class);
                intent.putExtra("html", DataProvider.Hospital_list);
                intent.putExtra("title", getResources().getString(R.string.hospital_list));
                startActivity(intent);
            }
        });



        initAll();


    }

    private void setScale(int i) {
        ScaleAdapter scaleMain = new ScaleAdapter(this,40, false, false);
        scaleAdapter = new ScaleAdapter(this,i, false, false);
        scaleRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        scale.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        scale.setAdapter(scaleMain);
        scaleRecycler.setAdapter(scaleAdapter);
        startScale.setOnClickListener(view -> {
            if (admobAds.isLoaded()){
                admobAds.show(() -> {
                    Intent intent = new Intent(this, ScaleActivity.class);
                    intent.putExtra("currentWeek",i);
                    startActivity(intent);
                });
            }else {

                Intent intent = new Intent(this, ScaleActivity.class);
                intent.putExtra("currentWeek",i);
                startActivity(intent);
            }
        });
    }

    private void initAll() {
        leftDay = findViewById(R.id.leftDay);
        goneDay = findViewById(R.id.goneDay);
        goneDay2 = findViewById(R.id.goneDay2);
        goneDay.setText(GoneDays());
        goneDay2.setText(GoneDays());
        leftDay.setText("বাকি রয়েছে: "+LeftDays(true)+" দিন");
        imageView = findViewById(R.id.imageView);
        detailsTv = findViewById(R.id.detailsTV);
        btnPlay = findViewById(R.id.btnPlay);
        setView(currentWeek);
        setScale(currentWeek);





    }

    private void setView(int currentWeek) {
        viewLay = findViewById(R.id.viewLay);
        if (currentWeek>2){

            detailsTv.setText(DataProvider.shortDescriptionData[currentWeek-1]);

            WeekModel weekModelImage = WeeklyDataProvider.weeklyData.get(currentWeek);


            WeekModel weekModelSound = WeeklyDataProvider.weeklyData.get(currentWeek);
            Glide.with(MainActivity.this)
                    .load(weekModelImage.getIvLink())
                    .into(imageView);


            viewLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, WeekActivity.class);
                    intent.putExtra("position", currentWeek);
                    startActivity(intent);
                }
            });

            if (weekModelSound.getSound()!=1){
                initPlayer(weekModelSound.getSound());

                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                /*if (currentWeek<=2){
                    Toast.makeText(MainActivity.this, "তৃতীয় সপ্তাহ হতে এখানে তথ্য দেখতে পাবেন", Toast.LENGTH_SHORT).show();
                }else {*/
                        if (isPlaying){
                            mediaPlayer.pause();
                            btnPlay.setImageResource(R.drawable.ic_play);
                            isPlaying = false;
                        }else {
                            if (complete){
                                initPlayer(weekModelSound.getSound());
                                complete = false;
                            }
                            mediaPlayer.start();
                            btnPlay.setImageResource(R.drawable.ic_pause);
                            isPlaying = true;
                        }
                        /* }*/
                    }
                });
            }else {
                btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "তৃতীয় সপ্তাহ হতে এখানে তথ্য দেখতে পাবেন", Toast.LENGTH_SHORT).show();

                    }
                });
            }






        }else {
            imageView.setImageResource(R.drawable.women);
            detailsTv.setText("তৃতীয় সপ্তাহ হতে এখানে তথ্য দেখতে পাবেন");
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "তৃতীয় সপ্তাহ হতে অডিও শুনতে পাবেন", Toast.LENGTH_SHORT).show();

                }
            });
            viewLay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "তৃতীয় সপ্তাহ হতে এখানে তথ্য দেখতে পাবেন", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private void initPlayer(int sound) {
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                complete = true;
                releaseMediaPlayer();
            }
        });
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


        String resultText = "এখন চলছে: "+weeks+" সপ্তাহ "+remainingDays+" দিন";
        currentWeek = (int) weeks;
        return resultText;
    }

    private String LeftDays(boolean day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date targetDate = null;
        try {
            targetDate = dateFormat.parse(customPref.getPossibleDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = Calendar.getInstance().getTime();
        long daysDifference = TimeUnit.DAYS.convert(Math.abs(targetDate.getTime() - currentDate.getTime()), TimeUnit.MILLISECONDS);
        long weeks = daysDifference / 7;
        long remainingDays = daysDifference % 7;
        String resultText = "বাকি রয়েছে: " + weeks + " সপ্তাহ " + remainingDays + " দিন (" + daysDifference+")";
        if (day){
            return String.valueOf(daysDifference);
        }else {
            return resultText;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            isPlaying = false;
            btnPlay.setImageResource(R.drawable.ic_play);
        }
    }

    @Override
    public void onBackPressed() {
        exitPopUp();

    }
    TextView powered, mail;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (admobAds.isLoaded()) admobAds.show();
        if (item.getItemId()==R.id.action_end){
            new AlertDialog.Builder(this)
                    .setTitle("প্রেগনেন্সি শেষ করুন.!")
                    .setMessage("আপনি কি চলমান প্রেগনেনসি টি শেষ করতে ইচ্ছুক..?")
                    .setPositiveButton("হ্যাঁ.!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            customPref.setSession(true);
                            customPref.setDate("null");
                            customPref.setPossibleDate("null");
                            startActivity(new Intent(MainActivity.this, IntroActivity.class));
                            finish();
                        }
                    }).setNegativeButton("না", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setNeutralButton("রেটিং দিন", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName()));
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()));
                                startActivity(intent);
                            }
                        }
                    }).show();
        } else if (item.getItemId()==R.id.action_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "Check out this app!";
            String message = getResources().getString(R.string.APP_SHARE_MSG) +
                    "https://play.google.com/store/apps/details?id="+getPackageName();
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(intent, "Share via"));

        } else if (item.getItemId()==R.id.action_feedback) {
            String[] recipientEmails = {getResources().getString(R.string.DEVELOPER_CONTACT_MAIL)}; // Replace with your email address
            String subject = "Feedback for Your "+getResources().getString(R.string.app_name)+" App";
            String message = "Hello,\n\nI wanted to provide some feedback regarding your app...";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // Only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, recipientEmails);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(intent);


        } else if (item.getItemId()==R.id.action_about) {
            aboutDialog = new BottomSheetDialog(this);
            aboutDialog.setContentView(R.layout.about_layout);
            powered = aboutDialog.findViewById(R.id.powered);
            mail = aboutDialog.findViewById(R.id.mail);
            mail.setText("Email: "+getResources().getString(R.string.DEVELOPER_CONTACT_MAIL));
            powered.setText(getResources().getString(R.string.POWERED_BY));

            if (aboutDialog.isShowing())
                aboutDialog.dismiss();
            else aboutDialog.show();
        }

        return true;
    }
    private void exitPopUp() {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Are you sure want to close this app..?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAndRemoveTask();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setNeutralButton("Rate Us", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            // Open the Play Store page for your app
                            Uri uri = Uri.parse("market://details?id=" + getPackageName());
                            Intent rateIntent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(rateIntent);
                        } catch (ActivityNotFoundException e) {
                            // If the Play Store is not installed, open the app page in the web browser
                            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                            Intent rateIntent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(rateIntent);
                        }
                    }
                }).show();
    }

}