package com.example.budgetpro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.budgetpro.ui.PayMent.KindofpaymentFragment;
import com.example.budgetpro.ui.PayMent.PayableFragment_khoanchi;

public class ChiViewPager2Adapter extends FragmentStateAdapter {
    public ChiViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position==0)
        {
            fragment= PayableFragment_khoanchi.newInstance();
        }else {
            fragment = KindofpaymentFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
