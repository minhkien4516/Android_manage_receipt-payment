package com.example.budgetpro.ui.PayMent;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.repository.LoaiChiRepository;
import com.example.budgetpro.repository.ChiRepository;

import java.util.List;

public class PayableFragmentKhoanchiViewModel extends AndroidViewModel {
    private ChiRepository mChiRepository;
    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    private LoaiChiRepository mLoaiChiRepository;

    //tao doi tuong mChiRepository
    public PayableFragmentKhoanchiViewModel(@NonNull Application application) {
        super(application);
        mChiRepository = new ChiRepository(application);
        mAllChi=mChiRepository.getAllChi();
        mLoaiChiRepository=new LoaiChiRepository(application);
        mAllLoaiChi=mLoaiChiRepository.getAllLoaiChi();
    }

    public LiveData<List<LoaiChi>> getAllLoaiChi(){
        return mAllLoaiChi;
    }
    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }
    public void insert(Chi chi){
        mChiRepository.insert(chi);
    }
    public void delete(Chi chi)
    {
        mChiRepository.delete(chi);
    }
    public void update(Chi chi)
    {
        mChiRepository.update(chi);
    }
}