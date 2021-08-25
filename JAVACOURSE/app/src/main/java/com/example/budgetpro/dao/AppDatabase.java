package com.example.budgetpro.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.LoaiChi;
import com.example.budgetpro.entity.LoaiThu;
import com.example.budgetpro.entity.Thu;

@Database(entities = {LoaiThu.class, Thu.class, LoaiChi.class, Chi.class},version = 4)
public abstract class AppDatabase extends RoomDatabase {

    //tao doi tuong
    public abstract LoaiThuDao loaiThuDao();
    public abstract ThuDao thuDao();
    public abstract LoaiChiDao loaiChiDao();
    public abstract ChiDao chiDao();

    public static AppDatabase INSTANCE;

    //goi thuc hien lop PopulatData do du lieu ban dau vao bang loaithu
    private static RoomDatabase.Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            new PopulateData(INSTANCE).execute();
        }
    };


    public static AppDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (AppDatabase.class)
            {
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"personal_db")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();
            }
        }
        return INSTANCE;
    }


    //khoi tao gia tri ban dau cho csdl
    public static class PopulateData extends AsyncTask<Void,Void,Void>{
        private LoaiThuDao loaiThuDao;
        private ThuDao thuDao;
        private  LoaiChiDao loaiChiDao;
        private  ChiDao chiDao;

        public PopulateData(AppDatabase db) {
            loaiThuDao =db.loaiThuDao();
            thuDao=db.thuDao();
            loaiChiDao=db.loaiChiDao();
            chiDao=db.chiDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String[] loaithus=new String[]{"Tiền Lương","Tiền Thưởng","Tiền Tiết Kiệm"};
            for(String it: loaithus)
            {
                LoaiThu lt =new LoaiThu();
                lt.ten=it;
                loaiThuDao.insert(lt);
            }
            Thu thu=new Thu();
            thu.ten="Lương tháng 1";
            thu.sotien=500000;
            thu.ltid=1;
            thu.ghichu="";
            thuDao.insert(thu);
            Log.d("BudgetPro: ","insert data");

            String[] loaichis= new String[]{"Tiền Phạt","Tiền Ăn","Tiền Đá Banh"};
            for ( String it :loaichis ){
                LoaiChi lc =new LoaiChi();
                lc.ten=it;
                loaiChiDao.insert(lc);

            }
            Chi chi=new Chi();
            chi.ten="Tiền Phạt";
            chi.sotien=300000;
            chi.lcid=1;
            chi.ghichu="";
            chiDao.insert(chi);
            Log.d("BudgetPro","insert data");
            return null;

        }
    }
}
