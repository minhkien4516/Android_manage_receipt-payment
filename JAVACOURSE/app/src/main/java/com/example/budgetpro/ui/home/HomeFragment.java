package com.example.budgetpro.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.budgetpro.MainActivity;
import com.example.budgetpro.R;
import com.example.budgetpro.adapter.SlideAdapter;
import com.example.budgetpro.ui.PayMent.PaymentFragment;
import com.example.budgetpro.ui.Saving.SavingFragment;

public class HomeFragment extends Fragment {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SlideAdapter slideAdapter;
    private TextView[] mDots;

    private Button mNextBtn;
    private Button  mBackBtn;
    private int mCurrentPage;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mSlideViewPager=(ViewPager) root.findViewById(R.id.slideViewPager);
        mDotLayout=(LinearLayout) root.findViewById(R.id.dotsLayout);

        mNextBtn=(Button) root.findViewById(R.id.nextBtn);
        mBackBtn=(Button) root.findViewById(R.id.prevBtn);

        slideAdapter=new SlideAdapter(getActivity());
        mSlideViewPager.setAdapter(slideAdapter);
        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage +1);


            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage-1);

            }
        });
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
    public void addDotsIndicator( int position){
        mDots=new TextView[3];
        mDotLayout.removeAllViews();
        for(int i= 0;i<mDots.length;i++){
            mDots[i]=new TextView(getActivity());
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.design_default_color_on_secondary));

            mDotLayout.addView(mDots[i]);

        }
        if (mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.design_default_color_surface));
        }

    }
    ViewPager.OnPageChangeListener viewListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage=i;
            if(i==0){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("");



            }else if(i==mDots.length -1){
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mNextBtn.setText("Finish");
                        Fragment mFragment = new SavingFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment ).commit();
                    }
                });
                mBackBtn.setText("Back");
            }
            else{
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);

                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}