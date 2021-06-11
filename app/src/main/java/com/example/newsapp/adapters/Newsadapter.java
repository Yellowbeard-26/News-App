package com.example.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.HomepageModel;
import com.example.newsapp.R;

import java.util.List;

public class Newsadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<HomepageModel.Article> articles;
    //could have also used <Article> if article was a standalone java class
    int image_left=1;
    int image_top=2;

    public Newsadapter(Context context, List<HomepageModel.Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
        {
            return image_top;
        }else
        {
            return image_left;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==image_left)
        {
            View v= LayoutInflater.from(context).inflate(R.layout.items_news,parent,false);
            return new ViewHolder(v);
        }
       else
        {
            View v= LayoutInflater.from(context).inflate(R.layout.items_news_image,parent,false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        HomepageModel.Article singleNews =articles.get(holder.getAdapterPosition());

        ViewHolder viewHolder=(ViewHolder) holder;
        viewHolder.newsTitle.setText(singleNews.getTitle());
        viewHolder.newsDesc.setText(singleNews.getDescription());
        viewHolder.newsDate.setText(singleNews.getPublishedAt());

        if(singleNews.getSource().getName() !=null)
        {
            viewHolder.newsSource.setText(singleNews.getSource().getName());
        }

        Glide.with(context)
                .load(singleNews.getUrlToImage())
                .into(viewHolder.newsImage);








    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        View holder;
        ImageView newsImage;
        TextView newsTitle,newsDesc,newsDate,newsSource;
        public ViewHolder(@NonNull View view)
        {
            super(view);
            holder=view;
            newsImage=holder.findViewById(R.id.news_image);
            newsTitle=holder.findViewById(R.id.news_title);
            newsDate=holder.findViewById(R.id.news_date);
            newsDesc=holder.findViewById(R.id.news_desc);
            newsSource=holder.findViewById(R.id.news_source);
        }
    }
}
