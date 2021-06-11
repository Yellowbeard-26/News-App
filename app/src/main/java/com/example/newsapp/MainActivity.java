package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.adapters.Gridcategoryadapter;
import com.example.newsapp.adapters.Newsadapter;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    SliderLayout sliderLayout;
    GridView gridView;
    Gridcategoryadapter adapter;
    RecyclerView recyclerView;
    Newsadapter newsadapter;
    List<HomepageModel.Article> articles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitiateViews();

        addimagestoslider();

        getData();
    }

    private void getData() {
        apiInterface in = Apiclient.getApiClient().create(apiInterface.class);
        Map<String, String> params = new HashMap<>();
        params.put("page", 1 + "");
        params.put("posts", 10 + "");

        Call<HomepageModel> call = in.getApi(params);
        call.enqueue(new Callback<HomepageModel>() {

            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {
                HomepageModel homepageModel=response.body();
                Log.e("main", "onResponse: total result ="+homepageModel.getTotalResults() );
                updatedatahomepage(homepageModel);
            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {

            }
        });
    }




    private void addimagestoslider() {



    }

    private void InitiateViews() {
        sliderLayout=(SliderLayout) findViewById(R.id.slider);
        gridView=findViewById(R.id.grid_view);
        adapter=new Gridcategoryadapter(this);
        gridView.setAdapter(adapter);
        recyclerView=findViewById(R.id.recy_news);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        articles=new ArrayList<>();
        newsadapter=new Newsadapter(this,articles);



    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }


    private void updatedatahomepage(HomepageModel body) {


            for(int i=0;i<body.getArticles().size();i++)
            {
                DefaultSliderView defaultSliderView=new DefaultSliderView(this);
                defaultSliderView.setRequestOption(new RequestOptions().fitCenter());
                defaultSliderView.image(body.getArticles().get(i).getUrlToImage());
                defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {

                    }
                });
                sliderLayout.addSlider(defaultSliderView);
            }
            sliderLayout.startAutoCycle();
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
            sliderLayout.setDuration(3000);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

            for(int i=0;i<body.getArticles().size();i++)
            {
                articles.add(body.getArticles().get(i));
            }
            recyclerView.setAdapter(newsadapter);
        }

    }

