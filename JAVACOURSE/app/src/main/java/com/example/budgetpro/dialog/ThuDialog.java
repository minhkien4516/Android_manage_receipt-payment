package com.example.budgetpro.dialog;

import android.app.AlertDialog;
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
import com.example.budgetpro.adapter.LoaiThuSpinnerAdapter;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.ui.Saving.ReceiptFragment;
import com.example.budgetpro.ui.Saving.ReceiptViewModel;
import com.example.budgetpro.ui.ThongKe.ThongKeThuFragment;
import com.example.budgetpro.ui.ThongKe.ThongKeThuViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ThuDialog {
    private ReceiptViewModel mkindofSavingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;


    private TextInputEditText etId,etName,etAmount,etNote;
    private Spinner spType;
    private boolean mEditMode;
    private LoaiThuSpinnerAdapter mAdapter;
    private TextView tvNgay;

    public ThuDialog(final Context context, ReceiptFragment fragment, Thu... thu) {
        mkindofSavingViewModel =fragment.getViewModel();

        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_thu,null);
        etId =view.findViewById(R.id.etId1);
        etName=view.findViewById(R.id.etName1);
        etAmount=view.findViewById(R.id.etAmount1);
        etNote=view.findViewById(R.id.etNote1);
        tvNgay=view.findViewById(R.id.tvNgay);

        spType=view.findViewById(R.id.spType1);
        mAdapter=new LoaiThuSpinnerAdapter(fragment.getActivity());
        mkindofSavingViewModel.getAllLoaiThu().observe(fragment.getActivity(), new Observer<List<LoaiThu>>() {
            @Override
            public void onChanged(List<LoaiThu> loaiThus) {
                mAdapter.setList(loaiThus);
            }
        });

        spType.setAdapter(mAdapter);

        if(thu!=null&&thu.length>0)
        {
            DecimalFormat decimalFormat=new DecimalFormat("###,###");
            etId.setText(""+thu[0].tid);
            etName.setText(thu[0].ten);
            etAmount.setText(""+thu[0].sotien);
            etNote.setText(thu[0].ghichu);
            tvNgay.setText(thu[0].ngay);
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
                        if(etName.getText().toString().isEmpty()&&etAmount.getText().toString().isEmpty()&&etNote.getText().toString().isEmpty()&&etAmount.getText().toString().isEmpty()&&tvNgay.getText().toString().isEmpty())
                        {
                            mdialog.dismiss();
                            Toast.makeText(context,"Điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                        }else {
                            DecimalFormat decimalFormat=new DecimalFormat("###,###");
                            Thu lt = new Thu();
                            lt.ten = etName.getText().toString();
                            lt.sotien = Double.parseDouble(etAmount.getText().toString());
                            lt.ghichu = etNote.getText().toString();
                            lt.ngay=tvNgay.getText().toString();
                            lt.ltid = ((LoaiThu) mAdapter.getItem(spType.getSelectedItemPosition())).lid;

                            if (mEditMode) {
                                lt.tid = Integer.parseInt(etId.getText().toString());
                                mkindofSavingViewModel.update(lt);

                            } else {
                                mkindofSavingViewModel.insert(lt);

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
