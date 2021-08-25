package com.example.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//annotation;
@Entity
public class LoaiThu {
    //fields:
    @PrimaryKey(autoGenerate = true) //gia tri id tu tang;
    public int lid;
    @ColumnInfo(name = "ten")
    public String ten;
}
