package com.example.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.newsapp.adapters.YoutubeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentYoutube extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    Context context;
String cid,pageToken="";

YoutubeAdapter youtubeAdapter;
List<Ytmodel.Item> items=new ArrayList<>();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_youtube,container,false);
        recyclerView=view.findViewById(R.id.video_recy);
        swipeRefreshLayout=view.findViewById(R.id.swipe);
        progressBar=view.findViewById(R.id.prog);

        youtubeAdapter=new YoutubeAdapter(context,items);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        cid=getArguments().getString("cid");
        LoadDatafromServer();

        swipeRefreshLayout.setOnRefreshListener(this);


        return view;
    }

    private void LoadDatafromServer() {

        Retrofit retrofit1=new Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        Map<String,String> params=new HashMap<>();
        params.put("part","snippet");
        params.put("channelId",cid);
        params.put("maxResults","20");
        params.put("pageToken",pageToken);
        params.put("key","AIzaSyCZTyu0wYYPJyAjFTVXFSaDyJchJI0KFkc");

        apiInterface api=retrofit1.create(apiInterface.class);
        Call<Ytmodel> call = api.getYoutubeServerdata(params);

        call.enqueue(new Callback<Ytmodel>() {
            @Override
            public void onResponse(Call<Ytmodel> call, Response<Ytmodel> response) {
                items.clear();
                items.addAll(response.body().getItems());
                recyclerView.setAdapter(youtubeAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Ytmodel> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        LoadDatafromServer();
        progressBar.setVisibility(View.GONE);
    }
}
