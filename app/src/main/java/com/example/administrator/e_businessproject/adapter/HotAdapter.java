package com.example.administrator.e_businessproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.HomeBean;
import com.example.administrator.e_businessproject.view.FullyLinearLayoutManager;

import java.util.List;

/**
 * 类的用途：热门专题适配器
 * Created by ${孙鹊禹}
 * on 2017/4/14 18:36
 */

public class HotAdapter extends BaseAdapter{

    private Context context;
    private List<HomeBean.DataBean.SubjectsBean> list;
    public HotAdapter(Context context, List<HomeBean.DataBean.SubjectsBean> list) {
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
            convertView=View.inflate(context, R.layout.hot_item,null);
            holder=new ViewHolder();
            holder.im_hot = (ImageView) convertView.findViewById(R.id.im_hot);
            holder.rcv_hot = (RecyclerView) convertView.findViewById(R.id.rcv_hot);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getImage()).into(holder.im_hot );
        List<HomeBean.DataBean.SubjectsBean.GoodsListBean> goodsList = list.get(position).getGoodsList();
        HotRecAdapter adapter = new HotRecAdapter(context,goodsList);
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.rcv_hot.setLayoutManager(linearLayoutManager);
        holder.rcv_hot.setAdapter(adapter);

        return convertView;
    }

    class ViewHolder{
        ImageView im_hot;
        RecyclerView rcv_hot;
    }
}
