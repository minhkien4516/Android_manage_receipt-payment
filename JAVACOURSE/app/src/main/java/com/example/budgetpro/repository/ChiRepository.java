package com.example.budgetpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.budgetpro.dao.AppDatabase;
import com.example.budgetpro.dao.ChiDao;
import com.example.budgetpro.entity.Chi;

import java.util.List;

    //dinh nghia truong mchiDao, dinh nghia cac danh sach co trong do;
public class ChiRepository {
    private ChiDao mChiDao;
    private LiveData<List<Chi>> mAllChi;
    //khoi tao cho chiDao lay cai danh sach co trong bang
    public ChiRepository(Application application) {
        this.mChiDao = AppDatabase.getDatabase(application).chiDao();
        mAllChi =mChiDao.findAll();
    }

    //Tinh tong so tien cua khoan chi trong bang khi dc them data
    public LiveData<Integer> total(){
        return mChiDao.total();
    }

    //lay cac du lieu trong bang
    public LiveData<List<Chi>> getAllChi() {
        return mAllChi;
    }


    public void insert(Chi chi)
    {
        new InsertAsynTask(mChiDao).execute(chi);
    }
    class InsertAsynTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;
        public InsertAsynTask(ChiDao chiDao){
            this.mChiDao=chiDao;
        }

        @Override
        protected Void doInBackground( Chi... chis) {
            mChiDao.insert(chis[0]);
            return null;
        }
    }

    public void delete(Chi chi)
    {
        new DeleteAsynTask(mChiDao).execute(chi);
    }
    class DeleteAsynTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;
        public DeleteAsynTask(ChiDao chiDao){
            this.mChiDao=chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.delete(chis[0]);
            return null;
        }
    }

    public void update(Chi chi)
    {
        new UpdateAsynTask(mChiDao).execute(chi);
    }
    class UpdateAsynTask extends AsyncTask<Chi,Void,Void>{
        private ChiDao mChiDao;

        public UpdateAsynTask( ChiDao chiDao){

            this.mChiDao=chiDao;
        }

        @Override
        protected Void doInBackground(Chi... chis) {
            mChiDao.update(chis[0]);
            return null;
        }
    }

}
