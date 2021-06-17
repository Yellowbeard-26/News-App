package com.example.newsapp.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.newsapp.Apiclient;
import com.example.newsapp.OurYtmodel;
import com.example.newsapp.R;
import com.example.newsapp.adapters.ViewPagerAdapter;
import com.example.newsapp.apiInterface;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YoutubeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

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
        getSupportActionBar().setTitle("Youtube Videos");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getYoutubedata();
    }

    private void getYoutubedata() {

        Log.e("ytact", "getYoutubedata:in" );
        apiInterface in = Apiclient.getApiClient().create(apiInterface.class);
        Call<OurYtmodel> call = in.getYoutubedataFromServer();



        call.enqueue(new Callback<OurYtmodel>() {
            @Override
            public void onResponse(Call<OurYtmodel> call, Response<OurYtmodel> response) {

                Log.e("ytact", "onResponse: "+response.body() );
                viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),response.body(),YoutubeActivity.this);
                viewPager.setAdapter(viewPagerAdapter);
            }

            @Override
            public void onFailure(Call<OurYtmodel> call, Throwable t) {

                Log.e("ytct", "onFailure: "+ t);
                Toast.makeText(YoutubeActivity.this,"Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }


}