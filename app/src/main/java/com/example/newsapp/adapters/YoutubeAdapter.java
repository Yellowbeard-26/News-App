package com.example.newsapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Activities.YoutubevideoActivity;
import com.example.newsapp.R;
import com.example.newsapp.Ytmodel;

import java.util.ArrayList;
import java.util.List;

public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.ViewHolder> {


    Context context;
    List<Ytmodel.Item> items= new ArrayList<>();

    public YoutubeAdapter(Context context, List<Ytmodel.Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Ytmodel.Item singleVideoitem=items.get(holder.getAdapterPosition());

         holder.videotitle.setText(singleVideoitem.getSnippet().getTitle());

         if(singleVideoitem.getSnippet().getThumbnails().getStandard()!=null)
         {
             Glide.with(context)
                     .load(singleVideoitem.getSnippet().getThumbnails().getHigh().getUrl()).into(holder.Thumbnail);

         }
         else
         {
             Glide.with(context)
                     .load(singleVideoitem.getSnippet().getThumbnails().getHigh().getUrl()).into(holder.Thumbnail);
         }



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView Thumbnail;
        TextView videotitle;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            Thumbnail=view.findViewById(R.id.thumb);
            videotitle=view.findViewById(R.id.titleeee);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Ytmodel.Item clickedVideo=items.get(getAdapterPosition());
            Uri uri=Uri.parse(clickedVideo.getSnippet().getThumbnails().getHigh().getUrl());

            Intent i=new Intent(context, YoutubevideoActivity.class);

            i.putExtra("vid",uri.getPathSegments().get(1));

            i.putExtra("cid",clickedVideo.getSnippet().getChannelId());

            i.putExtra("cname",clickedVideo.getSnippet().getChannelTitle());

            i.putExtra("title",clickedVideo.getSnippet().getTitle());
            context.startActivity(i);
        }
    }
}
