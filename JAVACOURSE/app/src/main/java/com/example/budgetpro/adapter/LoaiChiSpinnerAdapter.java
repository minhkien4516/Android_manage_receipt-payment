package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.LoaiChi;

import java.util.List;

public class LoaiChiSpinnerAdapter extends BaseAdapter {
    private List<LoaiChi> mList;
    private LayoutInflater mLayoutInflater;

    public LoaiChiSpinnerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<LoaiChi> mList) {
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
        KhoanChiViewHolder holder;
        if (convertView==null){
            convertView=mLayoutInflater.inflate(R.layout.spinner_chi_item,null,false);
            holder=new KhoanChiViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder=(KhoanChiViewHolder)convertView.getTag();
        }
        holder.tvName.setText(mList.get(position).ten);
        return convertView;
    }
    public static class KhoanChiViewHolder{
        public TextView tvName;
        public KhoanChiViewHolder(View view)
        {
            tvName=view.findViewById(R.id.tvName2);
        }
    }
}
