package com.example.budgetpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.Chi;
import com.example.budgetpro.entity.Thu;

import java.text.DecimalFormat;
import java.util.List;

public class ThongkeChiRecyclerViewAdapter extends RecyclerView.Adapter<ThongkeChiRecyclerViewAdapter.ChiViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<Chi> mList;
    public ThongkeChiRecyclerViewAdapter(Context context){
        mLayoutInflater=LayoutInflater.from(context);

    }


    @NonNull
    @Override
    public ChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mLayoutInflater.inflate(R.layout.recyclerview_thongke_chi_item,parent,false);
        return new ChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChiViewHolder holder, int position) {
        if(mList!=null)
        {
            DecimalFormat decimalFormat=new DecimalFormat("###,###");
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText(decimalFormat.format(mList.get(position).sotien)+" VNĐ");
            holder.tvNgay.setText(mList.get(position).ngay);
        }
    }

    @Override
    public int getItemCount() {
        if(mList==null)
        {
            return 0;
        }
        else {
            return mList.size();
        } //tra ve so luong phan tu co trong danh sach
    }
    public Chi getItem(int position)
    {
        if(mList==null||position>=mList.size())
        {
            return null;
        }
        return mList.get(position);
    }
    public void setList(List<Chi> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    public static class ChiViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName,tvAmount,tvNgay;
        public CardView cv;
        public int position;
        public ChiViewHolder(@NonNull View itemView){
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName2);
            tvAmount=itemView.findViewById(R.id.tvAmount2);
            tvNgay=itemView.findViewById(R.id.tvNgay);
            cv=(CardView)itemView;
        }
    }
}
