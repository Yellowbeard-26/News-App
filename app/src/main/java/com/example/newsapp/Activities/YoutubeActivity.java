package com.example.newsapp.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapp.R;
import com.google.android.material.tabs.TabLayout;

public class YoutubeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        initviews();
    }

    private void initviews() {
        toolbar=findViewById(R.id.toolbary);
        viewPager=findViewById(R.id.view_pager);
        tabLayout=findViewById(R.id.tablayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getYoutubedata();
    }

    private void getYoutubedata() {

    }


}