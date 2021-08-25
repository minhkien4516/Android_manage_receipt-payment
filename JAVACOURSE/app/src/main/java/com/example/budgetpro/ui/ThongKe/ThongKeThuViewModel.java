package com.example.budgetpro.ui.ThongKe;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.repository.LoaiThuRepository;
import com.example.budgetpro.repository.ThuRepository;

import java.util.List;

public class ThongKeThuViewModel extends AndroidViewModel {
    private ThuRepository mThuRepository;
    private LiveData<List<Thu>> mAllThu;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<Integer> mTotal;

    public ThongKeThuViewModel(@NonNull Application application) {
        super(application);
        mThuRepository = new ThuRepository(application);
        mAllThu=mThuRepository.getAllThu();
        mLoaiThuRepository=new LoaiThuRepository(application);
        mAllLoaiThu=mLoaiThuRepository.getAllLoaiThu();
        mThuRepository= new ThuRepository(application);
        mTotal=mThuRepository.total();
    }
    public LiveData<Integer> total(){return mTotal;}
    public LiveData<List<LoaiThu>> getAllLoaiThu(){
        return mAllLoaiThu;
    }
    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }
    public void insert(Thu thu){
        mThuRepository.insert(thu);
    }
    public void delete(Thu thu)
    {
        mThuRepository.delete(thu);
    }
    public void update(Thu thu)
    {
        mThuRepository.update(thu);
    }
}