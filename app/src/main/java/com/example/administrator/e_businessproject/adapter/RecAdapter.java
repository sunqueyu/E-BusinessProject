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

import butterknife.ButterKnife;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/14 9:39
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{

    private Context context;
    private List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> list;
    private OnItemClickListener onItemClickListener;

    public RecAdapter(Context context,List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> list){
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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.recycler_item,null);
        final ViewHolder holder = new ViewHolder(view);

        //条目点击
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                //注册点击监听
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView goods_pic;
        private TextView goods_name;
        private TextView market_price;
        private TextView shop_price;

        public ViewHolder(View itemView) {
            super(itemView);
            goods_pic = (ImageView) itemView.findViewById(R.id.goods_pic);
            goods_name = (TextView) itemView.findViewById(R.id.goods_name);
            market_price = (TextView) itemView.findViewById(R.id.market_price);
            shop_price = (TextView) itemView.findViewById(R.id.shop_price);
            ButterKnife.bind(this,itemView);
        }
    }
}
