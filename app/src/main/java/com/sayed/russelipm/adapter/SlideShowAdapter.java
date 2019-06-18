package com.sayed.russelipm.adapter;


import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sayed.russelipm.R;

//import com.bumptech.glide.Glide;

public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    LayoutInflater inflater;

    public int[] images = {
            R.drawable.bg1,
            R.drawable.bg6,
            R.drawable.bg2,
            R.drawable.bg4,
            R.drawable.bg5,
            R.drawable.bg7,
            R.drawable.bg3
    };


    public SlideShowAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {

        return images.length;

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view==(LinearLayout)object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.slideshow_layout,container,false);

        ImageView img = (ImageView) view.findViewById(R.id.bg_iv);

        img.setImageResource(images[position]);

//        Glide.with(context).load(images[position]).into(img);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Snackbar.make( view ,"Image " + ( position + 1 ) , Snackbar.LENGTH_LONG ).show();
//            }
//        });

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout)object);

    }


}
