package com.example.budgetpro.ui.PayMent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ItemClickListener;
import com.example.budgetpro.adapter.LoaiChiRecyclerviewAdapter;
import com.example.budgetpro.dialog.ChiDialog;
import com.example.budgetpro.dialog.LoaiChiDetailDialog;
import com.example.budgetpro.dialog.LoaiChiDialog;
import com.example.budgetpro.dialog.LoaiThuDialog;
import com.example.budgetpro.dialog.ThuDialog;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.ui.PayMent.KindofpaymentViewModel;
import com.example.budgetpro.ui.Saving.KindofSavingFragment;
import com.example.budgetpro.ui.Saving.ReceiptFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class KindofpaymentFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiChiRecyclerviewAdapter mAdapter;
    private KindofpaymentViewModel mViewModel;

    public static KindofpaymentFragment newInstance() {
        return new KindofpaymentFragment();
    }
    public KindofpaymentViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kindofpayment_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRv=view.findViewById(R.id.recyclerView);
        mAdapter=new LoaiChiRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        FloatingActionButton fab=view.findViewById(R.id.fab);
        final KindofpaymentFragment currentFragment=this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments=getFragmentManager().getFragments();

                Fragment fragment= fragments.get(fragments.size()-1);

                if (fragment instanceof KindofpaymentFragment)
                {
                    LoaiChiDialog dialog3 = new LoaiChiDialog(getActivity(), (KindofpaymentFragment) fragment);
                    dialog3.show();
                }
            }
        });

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi=mAdapter.getItem(position);
                LoaiChiDialog dialog=new LoaiChiDialog(getActivity(),currentFragment,loaiChi);
                dialog.show();
            }
        });


        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiChi loaiChi=mAdapter.getItem(position);
                LoaiChiDetailDialog dialog=new LoaiChiDetailDialog(getActivity(),currentFragment,loaiChi);
                dialog.show();
            }
        });

        ItemTouchHelper helper=new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                ) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position=viewHolder.getAdapterPosition();
                        LoaiChi lc=mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Loại chi đã được xoá",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lc);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KindofpaymentViewModel.class);
        mViewModel.getAllLoaiChi().observe(getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
    }

}