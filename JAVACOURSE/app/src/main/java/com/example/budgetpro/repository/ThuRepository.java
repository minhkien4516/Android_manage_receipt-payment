package com.example.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetpro.dao.AppDatabase;
import com.example.budgetpro.dao.ThuDao;
import com.example.budgetpro.entity.Thu;

import java.util.List;

//dinh nghia truong mthuDao, dinh nghia cac danh sach co trong do;
public class ThuRepository {
    private ThuDao mThuDao;
    private LiveData<List<Thu>> mAllThu;
//khoi tao cho thuDao lay cai danh sach co trong bang
public LiveData<Integer> total(){
    return mThuDao.total();
}
    public ThuRepository(Application application) {
        this.mThuDao = AppDatabase.getDatabase(application).thuDao();
        mAllThu =mThuDao.findAll();
    }
//lay cac du lieu trong bang
    public LiveData<List<Thu>> getAllThu() {
        return mAllThu;
    }

    public void insert(Thu thu)
    {
        new InsertAsynTask(mThuDao).execute(thu);
    }
    class InsertAsynTask extends AsyncTask<Thu,Void,Void>{
        private ThuDao mThuDao;
        public InsertAsynTask(ThuDao thuDao){
            this.mThuDao=thuDao;
        }

        @Override
        protected Void doInBackground( Thu... thus) {
            mThuDao.insert(thus[0]);
            return null;
        }
    }

    public void delete(Thu thu)
    {
        new DeleteAsynTask(mThuDao).execute(thu);
    }
    class DeleteAsynTask extends AsyncTask<Thu,Void,Void>{
        private ThuDao mThuDao;
        public DeleteAsynTask(ThuDao thuDao){
            this.mThuDao=thuDao;
        }

        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.delete(thus[0]);
            return null;
        }
    }

    public void update(Thu thu)
    {
        new UpdateAsynTask(mThuDao).execute(thu);
    }
    class UpdateAsynTask extends AsyncTask<Thu,Void,Void>{
        private ThuDao mThuDao;
        public UpdateAsynTask( ThuDao thuDao){
            this.mThuDao=thuDao;
        }

        @Override
        protected Void doInBackground(Thu... thus) {
            mThuDao.update(thus[0]);
            return null;
        }
    }
}
