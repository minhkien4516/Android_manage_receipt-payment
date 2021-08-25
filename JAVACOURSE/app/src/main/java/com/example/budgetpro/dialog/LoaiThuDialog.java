package com.example.budgetpro.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.ui.Saving.KindofSavingFragment;
import com.example.budgetpro.ui.Saving.KindofSavingViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoaiThuDialog {
    private KindofSavingViewModel mkindofSavingViewModel;
    private LayoutInflater mLayoutInflater;
    private AlertDialog mdialog;

    private TextInputEditText etId,etName;
    private boolean mEditMode;

    public LoaiThuDialog(final Context context, KindofSavingFragment fragment,LoaiThu ... loaiThu) {
        mkindofSavingViewModel =fragment.getViewModel();
        mLayoutInflater=LayoutInflater.from(context);
        View view=mLayoutInflater.inflate(R.layout.dialog_loai_thu,null);
        etId =view.findViewById(R.id.etId1);
        etName=view.findViewById(R.id.etName1);
        if(loaiThu!=null&&loaiThu.length>0)
        {
            etId.setText(""+loaiThu[0].lid);
            etName.setText(loaiThu[0].ten);
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
                        LoaiThu lt=new LoaiThu();
                        lt.ten=etName.getText().toString();
                        if(mEditMode)
                        {
                            lt.lid=Integer.parseInt(etId.getText().toString());
                            mkindofSavingViewModel.update(lt);
                        }else {
                            mkindofSavingViewModel.insert(lt);
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
