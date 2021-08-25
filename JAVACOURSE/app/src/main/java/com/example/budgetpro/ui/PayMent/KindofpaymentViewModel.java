package com.example.budgetpro.ui.PayMent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.repository.LoaiChiRepository;

import java.util.List;

public class KindofpaymentViewModel extends AndroidViewModel {private LoaiChiRepository mLoaiChiRepository;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    //tao doi tuong mLoaiChiRepository
    public KindofpaymentViewModel(@NonNull Application application) {
        super(application);
        mLoaiChiRepository = new LoaiChiRepository(application);
        mAllLoaiChi=mLoaiChiRepository.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }
    public void insert(LoaiChi loaiChi){
        mLoaiChiRepository.insert(loaiChi);
    }
    public void delete(LoaiChi loaiChi)
    {
        mLoaiChiRepository.delete(loaiChi);
    }
    public void update(LoaiChi loaiChi)
    {
        mLoaiChiRepository.update(loaiChi);
    }
}