package com.example.budgetpro.ui.PayMent;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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
import com.example.budgetpro.adapter.ChiRecyclerviewAdapter;
import com.example.budgetpro.dialog.ChiDialog;
import com.example.budgetpro.dialog.LoaiThuDialog;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.ui.PayMent.PayableFragment_khoanchi;

import com.example.budgetpro.ui.PayMent.PayableFragmentKhoanchiViewModel;
import com.example.budgetpro.ui.Saving.KindofSavingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PayableFragment_khoanchi extends Fragment {
    private PayableFragmentKhoanchiViewModel mViewModel;
    private RecyclerView mRv;
    private ChiRecyclerviewAdapter mAdapter;

    public static PayableFragment_khoanchi newInstance() {
        return new PayableFragment_khoanchi();
    }
    public PayableFragmentKhoanchiViewModel getViewModel() {
        return mViewModel;
    }

    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv=view.findViewById(R.id.recyclerView);
        mAdapter=new ChiRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        FloatingActionButton fab=view.findViewById(R.id.fab);
        final PayableFragment_khoanchi currentFragment=this;
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getFragmentManager().getFragments();
                Fragment fragment = fragments.get(fragments.size() - 1);

                if (fragment instanceof PayableFragment_khoanchi) {
                    ChiDialog dialog1 = new ChiDialog(getActivity(), (PayableFragment_khoanchi) fragment);
                    dialog1.show();
                }
            }
        });

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Chi chi=mAdapter.getItem(position);
                ChiDialog dialog=new ChiDialog(getActivity(),currentFragment,chi);
                dialog.show();
            }
        });
        ItemTouchHelper helper= new ItemTouchHelper(
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
                        Chi chi=mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản Chi được xoá!",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(chi);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.payable_fragment_khoanchi_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(PayableFragmentKhoanchiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
            }
        });
    }

}