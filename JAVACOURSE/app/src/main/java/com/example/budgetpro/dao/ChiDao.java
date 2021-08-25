package com.example.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetpro.entity.Chi;

import java.util.List;

//annotation;
@Dao
public interface ChiDao {
    @Query("SELECT * FROM chi ORDER BY ngay")
    LiveData<List<Chi>> findAll(); //tra tat ca du lieu trong loaichi;

    @Query("SELECT sum(sotien) as tv_tongtien FROM chi")
    LiveData<Integer> total();



    @Insert  //annotation
    void insert(Chi chi);
    @Update
    void update(Chi chi);
    @Delete
    void delete(Chi chi);


}
