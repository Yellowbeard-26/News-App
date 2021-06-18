package com.example.newsapp.Activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapp.MyData;
import com.example.newsapp.OurYtmodel;
import com.example.newsapp.R;
import com.example.newsapp.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class YoutubeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

    private static ArrayList<OurYtmodel> y;
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

        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("News Videos");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getYoutubedata();
    }

    private void getYoutubedata() {


        y=new ArrayList<>();
        for(int i=0;i< MyData.Title.length;i++)
        {
            y.add(new OurYtmodel(MyData.channelid[i],MyData.Title[i]));
        }
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),y,YoutubeActivity.this);
              viewPager.setAdapter(viewPagerAdapter);

    }

//        Log.e("ytact", "getYoutubedata:in" );
//        apiInterface in = Apiclient.getApiClient().create(apiInterface.class);
//        Call<OurYtmodel> call = in.getYoutubedataFromServer();
//
//
//
//        call.enqueue(new Callback<OurYtmodel>() {
//            @Override
//            public void onResponse(Call<OurYtmodel> call, Response<OurYtmodel> response) {
//
//                Log.e("ytact", "onResponse: "+response.body() );
//                viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),response.body(),YoutubeActivity.this);
//                viewPager.setAdapter(viewPagerAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<OurYtmodel> call, Throwable t) {
//
//                Log.e("ytct", "onFailure: "+ t);
//                Toast.makeText(YoutubeActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}