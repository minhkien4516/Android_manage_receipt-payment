package com.example.budgetpro.ui.Saving;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ThuViewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SavingFragment extends Fragment {
    private ViewPager2 mVp;
    private TabLayout mT1;
    public SavingFragment() {

    }


    public static SavingFragment newInstance(String param1, String param2) {
        SavingFragment fragment = new SavingFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVp=view.findViewById(R.id.viewPager2);
        mT1=view.findViewById(R.id.tabLayout);

        ThuViewPager2Adapter adapter=new ThuViewPager2Adapter(getActivity());
        mVp.setAdapter(adapter);

        new TabLayoutMediator(mT1, mVp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0)
                {
                    tab.setIcon(R.drawable.iconloaithu);
                    tab.setText("Khoản Thu");
                }else
                {
                    tab.setIcon(R.drawable.icontutien);
                    tab.setText("Loại Khoản Thu");
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

        return inflater.inflate(R.layout.fragment_saving, container, false);
    }
}