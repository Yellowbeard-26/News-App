package com.example.newsapp;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.request.RequestOptions;
import com.example.newsapp.adapters.Gridcategoryadapter;
import com.glide.slider.library.SliderLayout;
import com.glide.slider.library.slidertypes.BaseSliderView;
import com.glide.slider.library.slidertypes.DefaultSliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    SliderLayout sliderLayout;
    GridView gridView;
    Gridcategoryadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitiateViews();

        addimagestoslider();
    }

    private void addimagestoslider() {

        List<Integer> images=new ArrayList<>();
        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);

        for(int i=0;i<images.size();i++)
        {
            DefaultSliderView defaultSliderView=new DefaultSliderView(this);
            defaultSliderView.setRequestOption(new RequestOptions().centerCrop());
            defaultSliderView.image(images.get(i));
            defaultSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView slider) {

                }
            });
        sliderLayout.addSlider(defaultSliderView);
        }
        sliderLayout.startAutoCycle();
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setDuration(3000);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }

    private void InitiateViews() {
        sliderLayout=findViewById(R.id.slider);
        gridView=findViewById(R.id.grid_view);
        adapter=new Gridcategoryadapter(this);
        gridView.setAdapter(adapter);


    }

    @Override
    protected void onStop() {
        super.onStop();
        sliderLayout.stopAutoCycle();
    }
}