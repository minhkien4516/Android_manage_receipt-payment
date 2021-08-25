package com.example.budgetpro.ui.ThongKe;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.repository.ChiRepository;
import com.example.budgetpro.repository.LoaiChiRepository;
import com.example.budgetpro.repository.ThuRepository;

import java.util.List;

public class ThongKeChiViewModel extends AndroidViewModel {

    private ChiRepository mChiRepository;
    private LiveData<List<Chi>> mAllChi;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    private LoaiChiRepository mLoaiChiRepository;
    private ThuRepository mThuRepository;
    private LiveData<Integer> mTotal;
    private LiveData<Integer> mTotal1;

    //tao doi tuong mChiRepository
    public ThongKeChiViewModel(@NonNull Application application) {
        super(application);
        mChiRepository = new ChiRepository(application);
        mAllChi = mChiRepository.getAllChi();
        mChiRepository =new ChiRepository(application);
        mTotal=mChiRepository.total();
        mThuRepository=new ThuRepository(application);
        mTotal1=mThuRepository.total();
        mLoaiChiRepository = new LoaiChiRepository(application);
        mAllLoaiChi = mLoaiChiRepository.getAllLoaiChi();
    }
    public LiveData<Integer> total1(){ return mTotal1; }
    public LiveData<Integer> total(){ return mTotal; }
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