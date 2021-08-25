package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.ui.PayMent.KindofpaymentFragment;
import com.example.budgetpro.ui.PayMent.KindofpaymentViewModel;

public class LoaiChiDetailDialog {
    private KindofpaymentViewModel mkindofPayingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;

    private TextView tvId,tvName;


    public LoaiChiDetailDialog(final Context context, KindofpaymentFragment fragment, LoaiChi loaiChi) {
        mkindofPayingViewModel =fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_detail_loai_chi,null);
        tvId =view.findViewById(R.id.tvId1);
        tvName=view.findViewById(R.id.tvName2);

        tvId.setText(""+loaiChi.cid);
        tvName.setText(loaiChi.ten);


        AlertDialog.Builder builder=new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mdialog.dismiss();
                    }
                });

        mdialog=builder.create();
    }
    public void show(){
        mdialog.show();
    }
}
