package com.example.newsapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface apiInterface {
    @GET("top-headlines?sources=google-news-in&apiKey=2e5f4dfb507d42ce97e65b4158ece119")
    Call<HomepageModel> getApi(@QueryMap Map<String,String> params);



    @GET("everything")
    Call<HomepageModel> getCustom(@Query("q") String se,@Query("apiKey") String key);
}
