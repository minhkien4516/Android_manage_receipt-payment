package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.ui.Saving.KindofSavingFragment;
import com.example.budgetpro.ui.Saving.KindofSavingViewModel;

public class LoaiThuDetailDialog {
    private KindofSavingViewModel mkindofSavingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;

    private TextView tvId,tvName;


    public LoaiThuDetailDialog(final Context context, KindofSavingFragment fragment, LoaiThu loaiThu) {
        mkindofSavingViewModel =fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_detail_loai_thu,null);
        tvId =view.findViewById(R.id.tvId1);
        tvName=view.findViewById(R.id.tvName2);

        tvId.setText(""+loaiThu.lid);
        tvName.setText(loaiThu.ten);

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
