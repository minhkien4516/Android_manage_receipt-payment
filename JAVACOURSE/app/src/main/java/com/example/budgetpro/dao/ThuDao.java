package com.example.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetpro.entity.Thu;

import java.util.List;

//annotation;
@Dao
public interface ThuDao {
    @Query("SELECT * FROM thu")
    LiveData<List<Thu>> findAll(); //tra tat ca du lieu trong loaithu;
    @Query("SELECT sum(sotien) as tv_tongtien FROM thu")
    LiveData<Integer> total();

    @Insert  //annotation
    void insert(Thu thu);
    @Update
    void update(Thu thu);
    @Delete
    void delete(Thu thu);
}
