package com.example.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//annotation;
@Entity
public class Thu {
    //fields:
    @PrimaryKey(autoGenerate = true) //gia tri id tu tang;
    public int tid;
    @ColumnInfo(name="ltid")
    public int ltid;
    @ColumnInfo(name = "ten")
    public String ten;
    @ColumnInfo(name="sotien")
    public double sotien;
    @ColumnInfo(name="ghichu")
    public String ghichu;
    @ColumnInfo(name="ngay")
    public String ngay;
}
