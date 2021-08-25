package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiThu;

import java.util.List;

public class LoaiThuSpinnerAdapter extends BaseAdapter {
    private List<LoaiThu> mList;
    private LayoutInflater mLayoutInflater;

    public LoaiThuSpinnerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<LoaiThu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(mList==null)
            return 0;
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if(mList==null)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KhoanThuViewHolder holder;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.spinner_thu_item,null,false);
            holder=new KhoanThuViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(KhoanThuViewHolder)convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).ten);
        return convertView;
    }
    public static class KhoanThuViewHolder{
        public TextView tvName;
        public KhoanThuViewHolder(View view)
        {
            tvName=view.findViewById(R.id.tvName2);
        }
    }
}
