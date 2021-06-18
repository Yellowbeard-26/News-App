package com.example.newsapp.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubevideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    TextView videoTitle,channelname,labelSimilar;
    RecyclerView recyclerView;
    YouTubePlayerView youTubePlayerView;

    String cid,vid,title,cna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtubevideo);
        vid=getIntent().getStringExtra("vid");
//        cid=getIntent().getStringExtra("cid");
//        vid=getIntent().getStringExtra("vid");
//        title=getIntent().getStringExtra("title");
//        cna=getIntent().getStringExtra("cname");
//
//        videoTitle.setText(title);
//        channelname.setText(cna);
//        labelSimilar.setText("More From "+channelname);




        InitViews();
        youTubePlayerView.initialize(getString(R.string.google_api_key),this);
    }

    private void InitViews() {
//        videoTitle=findViewById(R.id.video_title);
//        channelname=findViewById(R.id.channel_name);
//        labelSimilar=findViewById(R.id.label_similar);
//        recyclerView=findViewById(R.id.video_view_recy);

        youTubePlayerView=findViewById(R.id.youtube_player);




    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(vid);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}