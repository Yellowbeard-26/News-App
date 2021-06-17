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

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentYoutube extends Fragment {


    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    Context context;
String cid,pageToken="";
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_youtube,container,false);
        recyclerView=view.findViewById(R.id.video_recy);
        swipeRefreshLayout=view.findViewById(R.id.swipe);
        progressBar=view.findViewById(R.id.prog);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

        LoadDatafromServer();


        cid=getArguments().getString("cid");
        return view;
    }

    private void LoadDatafromServer() {

        Retrofit retrofit1=new Retrofit.Builder().baseUrl("https://www.googleapis.com/youtube/v3/")
                .addConverterFactory(GsonConverterFactory.create()).build();


        Map<String,String> params=new HashMap<>();
        params.put("part","snippet");
        params.put("channelId",cid);
        params.put("maxResults","10");
        params.put("pageToken",pageToken);
        params.put("key","AIzaSyCZTyu0wYYPJyAjFTVXFSaDyJchJI0KFkc");

        apiInterface api=retrofit1.create(apiInterface.class);
        Call<Ytmodel> call = api.getYoutubeServerdata(params);

        call.enqueue(new Callback<Ytmodel>() {
            @Override
            public void onResponse(Call<Ytmodel> call, Response<Ytmodel> response) {
                
            }

            @Override
            public void onFailure(Call<Ytmodel> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
