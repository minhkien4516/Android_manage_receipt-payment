package com.example.budgetpro.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

//annotation;
@Dao
public interface LoaiChiDao {
    @Query("SELECT * FROM loaichi")

    LiveData<List<LoaiChi>> findAll(); //tra tat ca du lieu trong loaichi;

    @Insert
    void insert(LoaiChi loaiChi);
    @Update
    void update(LoaiChi loaiChi);
    @Delete
    void delete(LoaiChi loaiChi);
}
