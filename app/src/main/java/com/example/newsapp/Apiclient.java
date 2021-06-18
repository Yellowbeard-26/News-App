package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiclient extends AppCompatActivity {

    public static final String BaseURl="http://192.168.43.191/newsapp/wp-json/api/";

    private static Retrofit retrofit=null;
    public static Retrofit getApiClient()
    {
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();

        OkHttpClient client=new OkHttpClient.Builder().
                addInterceptor(loggingInterceptor).build();

        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(BaseURl).client(client).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
