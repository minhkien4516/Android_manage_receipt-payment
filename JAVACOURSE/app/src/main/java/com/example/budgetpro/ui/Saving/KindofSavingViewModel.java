package com.example.budgetpro.ui.Saving;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.budgetpro.dao.LoaiThuDao;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.repository.LoaiThuRepository;

import java.util.List;

public class KindofSavingViewModel extends AndroidViewModel {

    private LoaiThuRepository mLoaiThuRepository;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
    //tao doi tuong mLoaiThuRepository
    public KindofSavingViewModel(@NonNull Application application) {
        super(application);
        mLoaiThuRepository = new LoaiThuRepository(application);
        mAllLoaiThu=mLoaiThuRepository.getAllLoaiThu();
    }

    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }
    public void insert(LoaiThu loaiThu){
        mLoaiThuRepository.insert(loaiThu);
    }
    public void delete(LoaiThu loaiThu)
    {
        mLoaiThuRepository.delete(loaiThu);
    }
    public void update(LoaiThu loaiThu)
    {
        mLoaiThuRepository.update(loaiThu);
    }
}