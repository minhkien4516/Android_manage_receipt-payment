package com.example.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetpro.dao.AppDatabase;
import com.example.budgetpro.dao.LoaiThuDao;
import com.example.budgetpro.entity.LoaiThu;

import java.util.List;

//dinh nghia truong mloaiThuDao, dinh nghia cac danh sach co trong do;
public class LoaiThuRepository {
    private LoaiThuDao mloaiThuDao;
    private LiveData<List<LoaiThu>> mAllLoaiThu;
//khoi tao cho LoaiThuDao lay cai danh sach co trong bang
    public LoaiThuRepository(Application application) {
        this.mloaiThuDao = AppDatabase.getDatabase(application).loaiThuDao();
        mAllLoaiThu =mloaiThuDao.findAll();
    }
//lay cac du lieu trong bang
    public LiveData<List<LoaiThu>> getAllLoaiThu() {
        return mAllLoaiThu;
    }

    public void insert(LoaiThu loaiThu)
    {
        new InsertAsynTask(mloaiThuDao).execute(loaiThu);
    }
    class InsertAsynTask extends AsyncTask<LoaiThu,Void,Void>{
        private LoaiThuDao mloaiThuDao;
        public InsertAsynTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.insert(loaiThus[0]);
            return null;
        }
    }

    public void delete(LoaiThu loaiThu)
    {
        new DeleteAsynTask(mloaiThuDao).execute(loaiThu);
    }
    class DeleteAsynTask extends AsyncTask<LoaiThu,Void,Void>{
        private LoaiThuDao mloaiThuDao;
        public DeleteAsynTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.delete(loaiThus[0]);
            return null;
        }
    }

    public void update(LoaiThu loaiThu)
    {
        new UpdateAsynTask(mloaiThuDao).execute(loaiThu);
    }
    class UpdateAsynTask extends AsyncTask<LoaiThu,Void,Void>{
        private LoaiThuDao mloaiThuDao;
        public UpdateAsynTask(LoaiThuDao loaiThuDao){
            this.mloaiThuDao=loaiThuDao;
        }

        @Override
        protected Void doInBackground(LoaiThu... loaiThus) {
            mloaiThuDao.update(loaiThus[0]);
            return null;
        }
    }
}
