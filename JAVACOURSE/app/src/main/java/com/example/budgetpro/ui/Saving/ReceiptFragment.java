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
import com.example.budgetpro.adapter.ThuRecyclerviewAdapter;
import com.example.budgetpro.dialog.LoaiThuDialog;
import com.example.budgetpro.dialog.ThuDialog;
import com.example.budgetpro.entity.Thu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ReceiptFragment extends Fragment {

    private ReceiptViewModel mViewModel;
    private RecyclerView mRv;
    private ThuRecyclerviewAdapter mAdapter;

    public static ReceiptFragment newInstance() {
        return new ReceiptFragment();
    }
    public ReceiptViewModel getViewModel() {
        return mViewModel;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv=view.findViewById(R.id.recyclerView);
        mAdapter=new ThuRecyclerviewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);

        FloatingActionButton fab=view.findViewById(R.id.fab);
        final ReceiptFragment currentFragment=this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments=getFragmentManager().getFragments();
                Fragment fragment= fragments.get(fragments.size()-1);

                if (fragment instanceof ReceiptFragment)
                {
                    ThuDialog dialog2 = new ThuDialog(getActivity(), (ReceiptFragment) fragment);
                    dialog2.show();
                }
            }
        });

        mAdapter.setOnItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Thu thu=mAdapter.getItem(position);
                ThuDialog dialog=new ThuDialog(getActivity(),currentFragment,thu);
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
                        Thu thu=mAdapter.getItem(position);

                        Toast.makeText(getActivity(),"Khoản Thu được xoá!",Toast.LENGTH_SHORT).show();
                        mViewModel.delete(thu);
                    }
                }
        );
        helper.attachToRecyclerView(mRv);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.receipt_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReceiptViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
    }

}