package com.example.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Gridcategoryadapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    Context context;
    List<DemoCategory> demoCategories;

    public Gridcategoryadapter(Context context) {
        this.context = context;
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        demoCategories=new ArrayList<>();

        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));
        demoCategories.add(new DemoCategory("Basic", R.drawable.img4));
        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));
        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));
        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));demoCategories.add(new DemoCategory("Basics", R.drawable.img1));
        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));
        demoCategories.add(new DemoCategory("Basics", R.drawable.img1));



    }

    @Override
    public int getCount() {
        return demoCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return demoCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHoldery holdery=null;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_category_layout,null);
            holdery=new ViewHoldery();
            holdery.circleImageView=convertView.findViewById(R.id.category_image);
            holdery.textView=convertView.findViewById(R.id.text_category);
            convertView.setTag(holdery);
        }
        else
        {
            holdery=(ViewHoldery) convertView.getTag();
        }
        holdery.textView.setText(demoCategories.get(position).Imagename);
        Glide.with(context)
                .load(demoCategories.get(position).imageid).into(holdery.circleImageView);
        return convertView;
    }
    static class ViewHoldery{
        CircleImageView circleImageView;
        TextView textView;
    }
    class DemoCategory{
        String Imagename;
        int imageid;

        public DemoCategory(String imagename, int imageid) {
            Imagename = imagename;
            this.imageid = imageid;
        }
    }
}
