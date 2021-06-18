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

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    List<OurYtmodel> ourYtmodel;
    public ViewPagerAdapter(@NonNull FragmentManager fm, ArrayList<OurYtmodel> ourYtmodel, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
        this.ourYtmodel=ourYtmodel;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle=new Bundle();
        bundle.putString("cid",ourYtmodel.get(position).getChannelid());
        FragmentYoutube fragmentYoutube=new FragmentYoutube();
        fragmentYoutube.setArguments(bundle);
        return fragmentYoutube;
    }

    @Override
    public int getCount() {
        return ourYtmodel.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return ourYtmodel.get(position).getTitle();
    }
}
