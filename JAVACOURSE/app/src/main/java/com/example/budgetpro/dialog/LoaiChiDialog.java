package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.ui.PayMent.KindofpaymentFragment;
import com.example.budgetpro.ui.PayMent.KindofpaymentViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiChiDialog {
    private KindofpaymentViewModel mkindofPayingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;

    private TextInputEditText etId,etName;
    private boolean mEditMode;

    public LoaiChiDialog(final Context context, KindofpaymentFragment fragment, LoaiChi ... loaiChi) {
        mkindofPayingViewModel =fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_loai_chi,null);
        etId =view.findViewById(R.id.etId1);
        etName=view.findViewById(R.id.etName1);
        if(loaiChi!=null&&loaiChi.length>0)
        {
            etId.setText(""+loaiChi[0].cid);
            etName.setText(loaiChi[0].ten);
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
                        LoaiChi lc=new LoaiChi();
                        lc.ten=etName.getText().toString();
                        if(mEditMode)
                        {
                            lc.cid=Integer.parseInt(etId.getText().toString());
                            mkindofPayingViewModel.update(lc);
                        }else {
                            mkindofPayingViewModel.insert(lc);
                            Toast.makeText(context, "Đã lưu!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        mdialog=builder.create();
    }
    public void show(){
        mdialog.show();
    }
}
