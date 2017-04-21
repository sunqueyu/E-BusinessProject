package com.example.administrator.e_businessproject.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.HomeBean;

import java.util.List;

/**
 * 类的用途：热门专题recyclerview的适配器
 * Created by ${孙鹊禹}
 * on 2017/4/14 18:44
 */

public class HotRecAdapter extends RecyclerView.Adapter<HotRecAdapter.HotViewHolder>{

    private Context context;
    private List<HomeBean.DataBean.SubjectsBean.GoodsListBean> list;
    private OnItemClickListener onItemClickListener;

    public HotRecAdapter(Context context,List<HomeBean.DataBean.SubjectsBean.GoodsListBean> list){
        this.context=context;
        this.list=list;
    }

    //条目的点击事件
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    //对外提供的方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.recycler_item,null);
        final HotViewHolder hotViewHolder = new HotViewHolder(itemView);
        //条目点击
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = hotViewHolder.getLayoutPosition();
                //注册点击监听
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.goods_pic);
        holder.goods_name.setText(list.get(position).getGoods_name());
        holder.market_price.setText("￥:"+list.get(position).getMarket_price());
        holder.shop_price.setText("￥:"+list.get(position).getShop_price());
        holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HotViewHolder extends RecyclerView.ViewHolder{

        private ImageView goods_pic;
        private TextView goods_name;
        private TextView market_price;
        private TextView shop_price;

        public HotViewHolder(View itemView) {
            super(itemView);
            goods_pic = (ImageView) itemView.findViewById(R.id.goods_pic);
            goods_name = (TextView) itemView.findViewById(R.id.goods_name);
            market_price = (TextView) itemView.findViewById(R.id.market_price);
            shop_price = (TextView) itemView.findViewById(R.id.shop_price);
        }
    }
}
