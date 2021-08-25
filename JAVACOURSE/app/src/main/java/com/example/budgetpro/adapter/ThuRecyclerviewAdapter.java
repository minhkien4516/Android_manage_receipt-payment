package com.example.budgetpro.adapter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetpro.R;
import com.example.budgetpro.entity.Thu;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ThuRecyclerviewAdapter extends RecyclerView.Adapter<ThuRecyclerviewAdapter.ThuViewHolder> {
    Activity context;
    private LayoutInflater mLayoutInflater;
    private List<Thu> mList;

    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;

    public ThuRecyclerviewAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setOnItemEditClickListener(ItemClickListener itemEditClickListener) {
        ThuRecyclerviewAdapter.itemEditClickListener = itemEditClickListener;
    }

    public void setOnItemViewClickListener(ItemClickListener itemViewClickListener) {
        ThuRecyclerviewAdapter.itemViewClickListener = itemViewClickListener;
    }

    @NonNull
    @Override
    public ThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.recyclerview_thu_item,parent,false);
        return new ThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuViewHolder holder, int position) {
        if(mList!=null)
        {
            DecimalFormat decimalFormat=new DecimalFormat("###,###");
            holder.tvName.setText(mList.get(position).ten);
            holder.tvAmount.setText(decimalFormat.format(mList.get(position).sotien)+" VNĐ");
            holder.tvNgay.setText(mList.get(position).ngay);
            holder.position=position;
        }
    }

    @Override
    public int getItemCount() {
        if(mList==null)
            return 0;
        return mList.size();
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

    public class ThuViewHolder extends RecyclerView.ViewHolder{

        public TextView tvName,tvAmount;
        public ImageView ivEdit,ivView;
        public CardView cv;
        public int position;
        public TextView tvNgay;

        public ThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= itemView.findViewById(R.id.tvName2);
            ivView= itemView.findViewById(R.id.ivView);
            ivEdit= itemView.findViewById(R.id.ivEdit);
            tvAmount=itemView.findViewById(R.id.tvAmount2);
            tvNgay=itemView.findViewById(R.id.tvNgay);
            cv=(CardView) itemView;

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemEditClickListener!=null)
                        itemEditClickListener.onItemClick(position);
                    tvNgay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final Calendar calendar = Calendar.getInstance();
                            DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                    SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
                                    calendar.set(year,month,dayOfMonth);
                                    String date = simpleDateFormat.format(calendar.getTime());
                                    tvNgay.setText(date);
                                }
                            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                            datePickerDialog.show();
                        }
                    });
                }
            });
        }
    }
}
