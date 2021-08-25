package com.example.budgetpro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.budgetpro.ui.ThongKe.ThongKeChiFragment;
import com.example.budgetpro.ui.ThongKe.ThongKeThuFragment;

public class ThongkeViewPager2Adapter extends FragmentStateAdapter {
    public ThongkeViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position==0)
        {
            fragment= ThongKeChiFragment.newInstance();
        }else
        {
            fragment= ThongKeThuFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
