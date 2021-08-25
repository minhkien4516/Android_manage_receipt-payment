package com.example.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetpro.dao.AppDatabase;
import com.example.budgetpro.dao.LoaiChiDao;
import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

//dinh nghia truong mloaiChiDao, dinh nghia cac danh sach co trong do;
public class LoaiChiRepository {
    private LoaiChiDao mloaiChiDao;
    private LiveData<List<LoaiChi>> mAllLoaiChi;
    //khoi tao cho LoaiChiDao lay cai danh sach co trong bang
    public LoaiChiRepository(Application application) {
        this.mloaiChiDao = AppDatabase.getDatabase(application).loaiChiDao();
        mAllLoaiChi =mloaiChiDao.findAll();
    }
    //lay cac du lieu trong bang
    public LiveData<List<LoaiChi>> getAllLoaiChi() {
        return mAllLoaiChi;
    }

    public void insert(LoaiChi loaiChi)
    {
        new InsertAsynTask(mloaiChiDao).execute(loaiChi);
    }
    class InsertAsynTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mloaiChiDao;
        public InsertAsynTask(LoaiChiDao loaiChiDao){
            this.mloaiChiDao=loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.insert(loaiChis[0]);
            return null;
        }
    }

    public void delete(LoaiChi loaiChi)
    {
        new DeleteAsynTask(mloaiChiDao).execute(loaiChi);
    }
    class DeleteAsynTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mloaiChiDao;
        public DeleteAsynTask(LoaiChiDao loaiChiDao){
            this.mloaiChiDao=loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.delete(loaiChis[0]);
            return null;
        }
    }

    public void update(LoaiChi loaiChi)
    {
        new UpdateAsynTask(mloaiChiDao).execute(loaiChi);
    }
    class UpdateAsynTask extends AsyncTask<LoaiChi,Void,Void>{
        private LoaiChiDao mloaiChiDao;
        public UpdateAsynTask(LoaiChiDao loaiChiDao){
            this.mloaiChiDao=loaiChiDao;
        }

        @Override
        protected Void doInBackground(LoaiChi... loaiChis) {
            mloaiChiDao.update(loaiChis[0]);
            return null;
        }
    }
}
