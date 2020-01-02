package com.bw.miaoheng20200102.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20200102.MainActivity;
import com.bw.miaoheng20200102.R;
import com.bw.miaoheng20200102.entity.ListEntity;

import java.util.List;

/**
 * 时间 :2020/1/2  9:00
 * 作者 :苗恒
 * 功能 :
 */
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.MyViewHolder> {
    Context context;
    List<ListEntity.ResultBean.RxxpBean.CommodityListBean> commodityList;
    public RxxpAdapter(Context context, List<ListEntity.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.commodityList=commodityList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(commodityList.get(position).getCommodityName());
        Glide.with(context).load(commodityList.get(position).getMasterPic())

                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return  commodityList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
