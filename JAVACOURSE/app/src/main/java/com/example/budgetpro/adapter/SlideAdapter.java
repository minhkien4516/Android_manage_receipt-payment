package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.budgetpro.R;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    //    private Object RelativeLayout;
//----------------------------------------
    public SlideAdapter(Context context){
        this.context=context;
    }

    public int[] slide_images={
            R.drawable.checklist,
            R.drawable.control_money,
            R.drawable.security
    };
    public String[] slide_headings={
            "ADD ITEMS",
            "CONTROL MONEY",
            "SECURITY"

    };
    public String[] slide_desc={
            " \uD83D\uDC49 Thêm những khoản tiền đã chi tiêu và những lợi ích thu vào",
            " \uD83D\uDC49 Kiểm soát được tài chính của cá nhân",
            " \uD83D\uDC49 An toàn hiện đại"
    };
    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView=(ImageView ) view.findViewById(R.id.imageView);
        TextView slideHeading=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription=(TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);
        return  view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
