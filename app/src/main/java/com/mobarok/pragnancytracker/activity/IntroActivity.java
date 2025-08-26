package com.mobarok.pragnancytracker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobarok.pragnancytracker.R;
import com.mobarok.pragnancytracker.fregment.IntroFragment1;
import com.mobarok.pragnancytracker.fregment.IntroFragment2;
import com.mobarok.pragnancytracker.fregment.IntroFragment3;
import com.mobarok.pragnancytracker.fregment.IntroFragment4;
import com.mobarok.pragnancytracker.tools.CustomPref;
import com.mobarok.pragnancytracker.tools.EnableEdg2Edge;

public class IntroActivity extends AppCompatActivity {

    private TextView btnPrevious,btnNext;
    int TOTAL_FRAGMENT = 4;
    int CURRENT_FRAGMENT = 1;
    CustomPref customPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        EnableEdg2Edge.enable(this);
        customPref = new CustomPref(this);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        check(1);

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CURRENT_FRAGMENT > 1) {
                    CURRENT_FRAGMENT--;
                    check(CURRENT_FRAGMENT);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CURRENT_FRAGMENT < TOTAL_FRAGMENT) {
                    CURRENT_FRAGMENT++;
                    check(CURRENT_FRAGMENT);
                }else {
                    customPref.setSession(false);
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                    finish();
                }
            }
        });




    }

    public void check(int page){
        replaceFragment(page);
        setSelected(page);
        if (page==1){
            btnPrevious.setVisibility(View.GONE);
        }else btnPrevious.setVisibility(View.VISIBLE);
        if (page==4){
            btnNext.setText("শুরু করুন");
        }else btnNext.setText("পরবর্তী");


    }


    ImageView iv1,iv2,iv3,iv4;
    private void setSelected(int position){
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);

        if (position==1){
            iv1.setImageResource(R.drawable.selectedbg);
            iv2.setImageResource(R.drawable.selectbg);
            iv3.setImageResource(R.drawable.selectbg);
            iv4.setImageResource(R.drawable.selectbg);
        } else if (position==2) {
            iv1.setImageResource(R.drawable.selectbg);
            iv2.setImageResource(R.drawable.selectedbg);
            iv3.setImageResource(R.drawable.selectbg);
            iv4.setImageResource(R.drawable.selectbg);
        }else if (position==3){
            iv1.setImageResource(R.drawable.selectbg);
            iv2.setImageResource(R.drawable.selectbg);
            iv3.setImageResource(R.drawable.selectedbg);
            iv4.setImageResource(R.drawable.selectbg);
        } else if (position==4) {
            iv1.setImageResource(R.drawable.selectbg);
            iv2.setImageResource(R.drawable.selectbg);
            iv3.setImageResource(R.drawable.selectbg);
            iv4.setImageResource(R.drawable.selectedbg);
        }

    }


    public void replaceFragment(int position) {
        Fragment fragment = null;
        if (position==1){
            fragment = new IntroFragment1();
        } else if (position==2){
            fragment = new IntroFragment2();
        } else if (position==3){
            fragment = new IntroFragment3();
        } else if (position==4){
            fragment = new IntroFragment4();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.mainFrame,fragment);
        fragmentTransaction.commit();
        //title.setText(titleTXT);
    }

}
