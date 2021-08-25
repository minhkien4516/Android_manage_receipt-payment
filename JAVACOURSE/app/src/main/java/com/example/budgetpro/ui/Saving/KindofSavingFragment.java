package com.example.budgetpro.ui.Saving;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.ClipData;
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
import com.example.budgetpro.adapter.LoaiThuRecyclerviewAdapter;
import com.example.budgetpro.dialog.LoaiChiDialog;
import com.example.budgetpro.dialog.LoaiThuDetailDialog;
import com.example.budgetpro.dialog.LoaiThuDialog;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.ui.PayMent.KindofpaymentFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class KindofSavingFragment extends Fragment {
    private RecyclerView mRv;
    private LoaiThuRecyclerviewAdapter mAdapter;
    private KindofSavingViewModel mViewModel;

    public static KindofSavingFragment newInstance() {
        return new KindofSavingFragment();
    }

    public KindofSavingViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kindof_saving_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv=view.findViewById(R.id.recyclerView);
        mAdapter=new LoaiThuRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        FloatingActionButton fab=view.findViewById(R.id.fab);
        final KindofSavingFragment currentFragment=this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments=getFragmentManager().getFragments();
                Fragment fragment= fragments.get(fragments.size()-1);

                if (fragment instanceof KindofSavingFragment)
                {
                    LoaiThuDialog dialog1 = new LoaiThuDialog(getActivity(), (KindofSavingFragment) fragment);
                    dialog1.show();
                }
//                else if (fragment instanceof ReceiptFragment)
//                {
//                    ThuDialog dialog2 = new ThuDialog(currentContext, (ReceiptFragment) fragment);
//                    dialog2.show();
//                }


            }
        });


        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu=mAdapter.getItem(position);
                LoaiThuDialog dialog=new LoaiThuDialog(getActivity(),currentFragment,loaiThu);
                dialog.show();
            }
        });

        mAdapter.setOnItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LoaiThu loaiThu=mAdapter.getItem(position);
                LoaiThuDetailDialog dialog=new LoaiThuDetailDialog(getActivity(),currentFragment,loaiThu);
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
                        LoaiThu lt=mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Loại thu đã được xoá",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(lt);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(KindofSavingViewModel.class);
        mViewModel.getAllLoaiThu().observe(getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });
    }

}