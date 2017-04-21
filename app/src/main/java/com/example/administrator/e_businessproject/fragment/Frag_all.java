package com.example.administrator.e_businessproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.activity.GoodsActivity;
import com.example.administrator.e_businessproject.bean.EffectBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 类的用途：展示全部商品
 * Created by ${孙鹊禹}
 * on 2017/4/17 20:50
 */

public class Frag_all extends Fragment{

    private View view;
    private EffAdapter adapter;
    private GridView gv_all;
    private List<EffectBean.DataBean> lists;
    private int position=0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_all,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gv_all = (GridView) view.findViewById(R.id.gv_all);
        getData();

        gv_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("ap",position);
                intent.putExtra("all", (Serializable) lists);
                getActivity().startActivity(intent);
            }
        });

    }

    private void getData() {
        String url = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17%22";
        RequestParams params = new RequestParams(url);
        org.xutils.x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                EffectBean effectBean = gson.fromJson(result, EffectBean.class);
                lists =  effectBean.getData();
                adapter = new EffAdapter();

                //接受到position
                Bundle bundle = getArguments();
                position = bundle.getInt("pos",-1);
                Log.i("aaa",position+"");
                if(position==1){
                    Collections.sort(lists, new Comparator<EffectBean.DataBean>() {
                        @Override
                        public int compare(EffectBean.DataBean lhs, EffectBean.DataBean rhs) {
                            if (lhs.getShop_price() > rhs.getShop_price()) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });
                }else if(position==2){
                    Collections.sort(lists, new Comparator<EffectBean.DataBean>() {
                        @Override
                        public int compare(EffectBean.DataBean lhs, EffectBean.DataBean rhs) {
                            if (lhs.getShop_price() < rhs.getShop_price()) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });
                }

                gv_all.setAdapter(adapter);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private class EffAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return lists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView= View.inflate(getActivity(),R.layout.recycler_item,null);
                holder=new ViewHolder();
                holder.goods_pic = (ImageView) convertView.findViewById(R.id.goods_pic);
                holder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
                holder.market_price = (TextView) convertView.findViewById(R.id.market_price);
                holder.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            Glide.with(getActivity()).load(lists.get(position).getGoods_img()).into(holder.goods_pic);
            holder.goods_name.setText(lists.get(position).getGoods_name());
            holder.market_price.setText("￥:"+lists.get(position).getMarket_price());
            holder.shop_price.setText("￥:"+lists.get(position).getShop_price());
            return convertView;
        }

        class ViewHolder{
            ImageView goods_pic;
            TextView goods_name;
            TextView market_price;
            TextView shop_price;
        }
    }
}
