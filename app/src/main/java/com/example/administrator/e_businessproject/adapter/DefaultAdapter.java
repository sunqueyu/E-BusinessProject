package com.example.administrator.e_businessproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.HomeBean;

import java.util.List;

/**
 * 类的用途：首页默认
 * Created by ${孙鹊禹}
 * on 2017/4/14 19:48
 */

public class DefaultAdapter extends BaseAdapter{

    private Context context;
    private List<HomeBean.DataBean.DefaultGoodsListBean> list;

    public DefaultAdapter(Context context, List<HomeBean.DataBean.DefaultGoodsListBean> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(context,R.layout.recycler_item,null);
            holder=new ViewHolder();
            holder.goods_pic = (ImageView) convertView.findViewById(R.id.goods_pic);
            holder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
            holder.market_price = (TextView) convertView.findViewById(R.id.market_price);
            holder.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getGoods_img()).into(holder.goods_pic);
        holder.goods_name.setText(list.get(position).getGoods_name());
        holder.market_price.setText("￥:"+list.get(position).getMarket_price());
        holder.shop_price.setText("￥:"+list.get(position).getShop_price());
        return convertView;
    }

    class ViewHolder{
        ImageView goods_pic;
        TextView goods_name;
        TextView market_price;
        TextView shop_price;
    }
}
