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
import com.example.budgetpro.dialog.ThuDialog;
import com.example.budgetpro.entity.Thu;
import com.example.budgetpro.repository.ThuRepository;

import java.text.DecimalFormat;
import java.util.List;

public class ThongkeThuRecyclerViewAdapter extends RecyclerView.Adapter<ThongkeThuRecyclerViewAdapter.ThuViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<Thu> mList;
    public ThongkeThuRecyclerViewAdapter(Context context){
        mLayoutInflater=LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mLayoutInflater.inflate(R.layout.recyclerview_thongke_thu_item,parent,false);
        return new ThuViewHolder(view);
    }
//////Binding data
    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
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

    public Thu getItem(int position)
    {
        if(mList==null||position>=mList.size())
        {
            return null;
        }
        return mList.get(position);
    }

    public void setList(List<Thu> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ThuViewHolder extends RecyclerView.ViewHolder{

        private  TextView tvName,tvAmount,tvNgay;
        private  CardView cv;
        public int position;

        public ThuViewHolder(@NonNull View itemView){
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName2);
            tvAmount=itemView.findViewById(R.id.tvAmount2);
            tvNgay=itemView.findViewById(R.id.tvNgay);
            cv=(CardView)itemView;

        }
    }
}
