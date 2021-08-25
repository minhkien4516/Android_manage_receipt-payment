package com.example.budgetpro.ui.ThongKe;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ThongkeThuRecyclerViewAdapter;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.ui.Saving.ReceiptViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThongKeThuFragment extends Fragment {
    private RecyclerView mRv;
    private ThongkeThuRecyclerViewAdapter mAdapter;
    private ThongKeThuViewModel mViewModel;
    private TextView tv_tongtien1;

    PieChart pieChart;
    PieData pieData;
    List<PieEntry> pieEntryList = new ArrayList<>();

    public static ThongKeThuFragment newInstance() {
        return new ThongKeThuFragment();
    }
    public ThongKeThuViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv=view.findViewById(R.id.recyclerView2);
        mAdapter= new ThongkeThuRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        tv_tongtien1=view.findViewById(R.id.tv_tongtien1);
        pieChart = view.findViewById(R.id.chart);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thong_ke_thu_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThongKeThuViewModel.class);
        mViewModel.getAllThu().observe(getActivity(), new Observer<List<Thu>>() {
            @Override
            public void onChanged(List<Thu> thus) {
                mAdapter.setList(thus);
            }
        });
        mViewModel.total().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer aInteger) {
                tv_tongtien1.setText(String.format("%,d",aInteger)+" VNĐ");
                if(tv_tongtien1.getText().toString().isEmpty() && aInteger.equals(null) )
                {
                    pieChart.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(),"Không thống kê được số tiền thu được",Toast.LENGTH_SHORT).show();
                }else {

                    try {
                        pieChart.setUsePercentValues(false);
                        pieEntryList.add(new PieEntry(Integer.parseInt(String.valueOf(aInteger)), "Tổng Chi"));
                        pieEntryList.add(new PieEntry(20000000, "MAX"));

                        pieChart.setEntryLabelTextSize(10.0f);
                        pieChart.setEntryLabelColor(Color.parseColor("#000000"));


                        PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Khoản Thu / Khoản Thu Tối Đa");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        pieData = new PieData(pieDataSet);
//                    ValueFormatter vf = new ValueFormatter() { //value format here, here is the overridden method
//                        @Override
//                        public String getFormattedValue(float value) {
//                            return ""+(int)value;
//                        }
//                    };
//                    pieData.setValueFormatter(vf);
                        pieData.setValueTextSize(10.0f);
                        pieChart.setData(pieData);
                        pieChart.invalidate();
                        pieChart.getDescription().setText("Tổng Thu / Tối đa");
                        pieChart.getDescription().setTextSize(12.0f);
                        pieChart.setCenterText("THỐNG KÊ");
                        pieChart.setCenterTextSize(15.0f);
                        pieChart.setCenterTextColor(Color.parseColor("#0097A7"));
                    }catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}