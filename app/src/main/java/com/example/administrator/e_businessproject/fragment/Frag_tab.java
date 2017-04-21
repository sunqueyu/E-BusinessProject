package com.example.administrator.e_businessproject.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.administrator.e_businessproject.activity.ClassifyActivity;
import com.example.administrator.e_businessproject.activity.GoodsActivity;
import com.example.administrator.e_businessproject.bean.EffectBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.List;

import static android.R.attr.data;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/18 20:04
 */

public class Frag_tab extends Fragment{

    private View view;
    private String s;
    private GridView gridView;
    private List<EffectBean.DataBean> data;

    public Frag_tab(String s) {
        this.s=s;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_all,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gv_all);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("tp",position);
                intent.putExtra("tab", (Serializable) data);
                getActivity().startActivity(intent);
            }
        });

        RequestParams params = new RequestParams(s);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson= new Gson();
                EffectBean effectBean = gson.fromJson(result, EffectBean.class);
                data = effectBean.getData();
                gridView.setAdapter(new EffAdapter());
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
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
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
            Glide.with(getActivity()).load(data.get(position).getGoods_img()).into(holder.goods_pic);
            holder.goods_name.setText(data.get(position).getGoods_name());
            holder.market_price.setText("￥:"+data.get(position).getMarket_price());
            holder.shop_price.setText("￥:"+data.get(position).getShop_price());
            holder.market_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
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
