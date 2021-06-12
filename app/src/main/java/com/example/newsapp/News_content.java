package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class News_content extends AppCompatActivity {

    Button button;
    String imageurl;
    String t;
    String c;
    String h;
    String q;
    Intent intent;
    ImageView iv;
    String y;
    TextView title,content,desc,Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);


        intent= getIntent();
        iv=findViewById(R.id.n_image);
        title=findViewById(R.id.newsTitle);
        content=findViewById(R.id.news_content);
        desc=findViewById(R.id.ttt);
        t=intent.getStringExtra("title");
        title.setText(t);
        c=intent.getStringExtra("desc");
        content.setText(c);
        h=intent.getStringExtra("Content");
        desc.setText(h);
        q=intent.getStringExtra("uri");
        Auth=findViewById(R.id.author);
        y=intent.getStringExtra("auth");
        Auth.setText(y);

        button=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Uri uri = Uri.parse(q);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
            }
        });
        LoadImage(iv);
    }

    public void LoadImage(View view)
    {
        imageurl = intent.getStringExtra("url");
        try {
            Glide.with(this).load(imageurl).into(iv);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}