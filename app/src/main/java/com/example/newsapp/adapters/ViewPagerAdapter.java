package com.example.newsapp.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapp.FragmentYoutube;
import com.example.newsapp.OurYtmodel;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    OurYtmodel ourYtmodel;
    public ViewPagerAdapter(@NonNull FragmentManager fm, OurYtmodel ourYtmodel, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
        this.ourYtmodel=ourYtmodel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle=new Bundle();
        bundle.putString("cid",ourYtmodel.getYoutubeData().get(position).getChannelId());
        FragmentYoutube fragmentYoutube=new FragmentYoutube();
        fragmentYoutube.setArguments(bundle);
        return fragmentYoutube;
    }

    @Override
    public int getCount() {
        return ourYtmodel.getYoutubeData().size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ourYtmodel.getYoutubeData().get(position).getTitle();
    }
}
