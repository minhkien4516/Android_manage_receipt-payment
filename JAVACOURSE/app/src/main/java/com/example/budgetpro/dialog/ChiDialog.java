package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.budgetpro.R;
import com.example.budgetpro.adapter.LoaiChiSpinnerAdapter;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.ui.PayMent.PayableFragmentKhoanchiViewModel;
import com.example.budgetpro.ui.PayMent.PayableFragment_khoanchi;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ChiDialog {
    private PayableFragmentKhoanchiViewModel mkindofPayingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;

    private TextInputEditText etId,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiChiSpinnerAdapter mAdapter;
    private TextView tvNgay;

    public ChiDialog(final Context context, PayableFragment_khoanchi fragment, Chi... chi) {
        mkindofPayingViewModel =fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_chi,null);
        etId =view.findViewById(R.id.etId1);
        etName=view.findViewById(R.id.etName1);
        etAmount=view.findViewById(R.id.etAmount1);
        etNote=view.findViewById(R.id.etNote1);
        tvNgay=view.findViewById(R.id.tvNgay);

        spType=view.findViewById(R.id.spType1);
        mAdapter=new LoaiChiSpinnerAdapter(fragment.getActivity());
        mkindofPayingViewModel.getAllLoaiChi().observe(fragment.getActivity(), new Observer<List<LoaiChi>>() {
            @Override
            public void onChanged(List<LoaiChi> loaiChis) {
                mAdapter.setList(loaiChis);
            }
        });
        spType.setAdapter(mAdapter);

        if(chi!=null&&chi.length>0)
        {
            DecimalFormat decimalFormat=new DecimalFormat("###,###");
            etId.setText(""+chi[0].ccid);
            etName.setText(chi[0].ten);
            etAmount.setText(""+chi[0].sotien);
            etNote.setText(chi[0].ghichu);
            tvNgay.setText(chi[0].ngay);
            mEditMode=true;
        }else
        {
            mEditMode=false;
        }
        AlertDialog.Builder builder=new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mdialog.dismiss();
                    }
                })
                .setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (etName.getText().toString().isEmpty() && etAmount.getText().toString().isEmpty() && etNote.getText().toString().isEmpty() && etAmount.getText().toString().isEmpty() && tvNgay.getText().toString().isEmpty()) {
                            mdialog.dismiss();
                            Toast.makeText(context, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            Chi lc = new Chi();
                            lc.ten = etName.getText().toString();
                            lc.sotien = Double.parseDouble(etAmount.getText().toString());
                            lc.ghichu = etNote.getText().toString();
                            lc.ngay=tvNgay.getText().toString();
                            lc.lcid = ((LoaiChi) mAdapter.getItem(spType.getSelectedItemPosition())).cid;
                            if (mEditMode) {
                                lc.ccid = Integer.parseInt(etId.getText().toString());
                                mkindofPayingViewModel.update(lc);
                            } else {
                                mkindofPayingViewModel.insert(lc);
                                Toast.makeText(context, "Đã lưu!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
        tvNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayOfMonth);
                        String date = simpleDateFormat.format(calendar.getTime());
                        tvNgay.setText(date);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        mdialog=builder.create();
    }


    public void show(){
        mdialog.show();
    }
}
