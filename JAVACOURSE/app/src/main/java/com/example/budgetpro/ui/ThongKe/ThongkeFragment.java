package com.example.budgetpro.ui.ThongKe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ThongkeViewPager2Adapter;
import com.example.budgetpro.adapter.ThuViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThongkeFragment extends Fragment {
    private TabLayout mTl;
    private ViewPager2 mVp;

    public ThongkeFragment(){}

    public static ThongkeFragment newInstance(String param1, String param2) {
        ThongkeFragment fragment = new ThongkeFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp=view.findViewById(R.id.viewPager22);
        mTl=view.findViewById(R.id.tabLayout2);

        ThongkeViewPager2Adapter adapter=new ThongkeViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mTl, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0)
                {
                    tab.setIcon(R.drawable.iconkhoanchi);
                    tab.setText("Thống kê Khoản Chi");
                }else
                {
                    tab.setIcon(R.drawable.iconloaithu);
                    tab.setText("Thống kê Khoản Thu");
                }
            }
        }).attach();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thongke, container, false);
    }
}