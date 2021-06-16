package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {


    SliderLayout sliderLayout;
    GridView gridView;
    Gridcategoryadapter adapter;
    RecyclerView recyclerView;
    Newsadapter newsadapter;
    List<HomepageModel.Article> articles;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    Button search;
    String apikey="2e5f4dfb507d42ce97e65b4158ece119";
    EditText Searchbar;
    boolean isfromstart=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search=(Button) findViewById(R.id.Buttonsearch);
        Searchbar=(EditText) findViewById(R.id.editTextTextPersonName3);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String se=Searchbar.getText().toString();
                if(se.length()==0||se.equalsIgnoreCase(""))
                {
                    Searchbar.setError("Searchbar Cannot be Empty");
                }
                else
                {
                    Custom(se);
                }
            }
        });



        InitiateViews();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Custom("Buisness");
                }
                if(position==1)
                {
                    Custom("Sports");
                }
                if(position==2)
                {
                    Custom("Movies");
                }
                if(position==3)
                {
                    Custom("Health");
                }
                if(position==4)
                {
                    Custom("Politics");
                }
                if(position==5)
                {
                    Custom("Religion");
                }
                if(position==6)
                {
                    Custom("Technology");
                }
                if(position==7)
                {
                    Custom("Science");
                }

            }
        });
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
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {


                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
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


        progressBar=findViewById(R.id.progress_bar);
        swipeRefreshLayout=findViewById(R.id.swipy);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(this);



    }

    @Override
    protected void onStop() {
        super.onStop();
//        sliderLayout.stopAutoCycle();
    }


    private void updatedatahomepage(HomepageModel body) {
        int i1;



        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        if(isfromstart)
        {
            articles.clear();
        }
            for(i1=0;i1<body.getArticles().size();i1++)
            {
                DefaultSliderView defaultSliderView=new DefaultSliderView(this);
                defaultSliderView.setRequestOption(new RequestOptions().fitCenter());
                defaultSliderView.image(body.getArticles().get(i1).getUrlToImage());
                int finalI = i1;
                defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        Intent i=new Intent(getApplicationContext(), News_content.class);
                        i.putExtra("url",body.getArticles().get(finalI).getUrlToImage());
                        i.putExtra("title",body.getArticles().get(finalI).getTitle());
                        i.putExtra("desc",body.getArticles().get(finalI).getDescription());
                        i.putExtra("Content",body.getArticles().get(finalI).getContent());
                        i.putExtra("uri",body.getArticles().get(finalI).getUrl());
                        i.putExtra("auth",body.getArticles().get(finalI).getAuthor());
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(i);

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

    @Override
    public void onRefresh() {
        getData();
        progressBar.setVisibility(View.GONE);
    }
    public void Custom(String s)
    {
        articles.clear();

        Log.e("main", "onResponse: data ="+s);
        Retrofit retrofit1=new Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiInterface api=retrofit1.create(apiInterface.class);
        Call<HomepageModel> call= api.getCustom(s,apikey);

        call.enqueue(new Callback<HomepageModel>() {
            @Override
            public void onResponse(Call<HomepageModel> call, Response<HomepageModel> response) {
                HomepageModel homepageModel=response.body();
                Log.e("main", "onResponse: data ="+response.body());
                for(int i=0;i<homepageModel.getArticles().size();i++)
                {
                    articles.add(homepageModel.getArticles().get(i));
                }
                recyclerView.setAdapter(newsadapter);
            }

            @Override
            public void onFailure(Call<HomepageModel> call, Throwable t) {

            }
        });
    }
}

