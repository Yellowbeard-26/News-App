package com.example.newsapp.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    Context context;
    public ViewPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context=context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        Bundle bundle=new Bundle();
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
