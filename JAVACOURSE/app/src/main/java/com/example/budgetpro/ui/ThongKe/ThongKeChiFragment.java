package com.example.budgetpro.ui.ThongKe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.ThongkeChiRecyclerViewAdapter;
import com.example.budgetpro.dao.ChiDao;
import com.example.budgetpro.dao.ThuDao;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.repository.ChiRepository;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

//import lecho.lib.hellocharts.model.PieChartData;
//import lecho.lib.hellocharts.model.SliceValue;
//import lecho.lib.hellocharts.view.PieChartView;

public class ThongKeChiFragment extends Fragment {
    private RecyclerView mRv;
    private ThongkeChiRecyclerViewAdapter mAdapter;
    private ThongKeChiViewModel mViewModel;

    private TextView tv_tongtien;
    private List<Chi> chi;
//    PieChartView pieChartView;
    PieChart pieChart;
    PieData pieData;
    List<PieEntry> pieEntryList = new ArrayList<>();

    public static ThongKeChiFragment newInstance() {
        return new ThongKeChiFragment();
    }
    public ThongKeChiViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thong_ke_chi_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView2);
        mAdapter = new ThongkeChiRecyclerViewAdapter(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv.setAdapter(mAdapter);
        tv_tongtien = view.findViewById(R.id.tv_tongtien);

        pieChart = view.findViewById(R.id.chart);


    }

    @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ThongKeChiViewModel.class);
        mViewModel.getAllChi().observe(getActivity(), new Observer<List<Chi>>() {
            @Override
            public void onChanged(List<Chi> chis) {
                mAdapter.setList(chis);
            }
        });
        mViewModel.total().observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer aInteger) {
                if(mRv!=null) {
                    tv_tongtien.setText(String.format("%,d", aInteger) + " VNĐ");
                    if(tv_tongtien.getText().toString().isEmpty() && aInteger.equals(null) )
                    {
                        pieChart.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(),"Không thống kê được số tiền đã chi",Toast.LENGTH_SHORT).show();
                    }else {

                        try {
                            pieChart.setUsePercentValues(false);
                            pieEntryList.add(new PieEntry(Integer.parseInt(String.valueOf(aInteger)), "Tổng Chi"));
                            pieEntryList.add(new PieEntry(10000000, "MAX"));

                            pieChart.setEntryLabelTextSize(10.0f);
                            pieChart.setEntryLabelColor(Color.parseColor("#000000"));


                            PieDataSet pieDataSet = new PieDataSet(pieEntryList, "Khoản Chi / Khoản Chi Tối Đa");
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
                            pieChart.getDescription().setText("Tổng Chi / Tối đa");
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
            }
        });

//        mViewModel.total1().observe(getActivity(), new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
////                tv_tongtien2.setText(String.format("%,d", integer) + " VNĐ");
//                pieEntryList.add(new PieEntry(Integer.parseInt(String.valueOf(integer)),"Tổng Thu"));
//            }
//        });

    }
}