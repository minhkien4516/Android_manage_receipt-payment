package com.example.budgetpro.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

//annotation;
@Entity
public class Chi {
    //fields:
    @PrimaryKey(autoGenerate = true) //gia tri id tu tang;
    public int ccid;
    @ColumnInfo(name="lcid")
    public int lcid;
    @ColumnInfo(name = "ten")
    public String ten;
    @ColumnInfo(name="sotien")
    public double sotien;
    @ColumnInfo(name="ghichu")
    public String ghichu;
    @ColumnInfo(name="ngay")
    public String ngay;
}
