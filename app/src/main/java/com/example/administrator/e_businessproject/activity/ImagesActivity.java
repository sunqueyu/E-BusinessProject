package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.EffectBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/19 19:14
 */

public class ImagesActivity extends AppCompatActivity{

    //图片url
    String url_imm1 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=39";
    String url_imm2 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=40";
    String url_imm3 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=24";
    String url_imm4 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=35";
    String url_imm5 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=33";
    private ArrayList<String> urls = new ArrayList<>();
    private TextView tv_images;
    private GridView gv_images;
    private List<EffectBean.DataBean> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.images_layout);

        initView();
    }

    private void initView() {
        tv_images = (TextView) findViewById(R.id.tv_images);
        gv_images = (GridView) findViewById(R.id.gv_images);
        ImageView imm_back = (ImageView) findViewById(R.id.imm_back);

        //点击结束当前页面
        imm_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagesActivity.this.finish();
            }
        });

        urls.add(url_imm1);
        urls.add(url_imm2);
        urls.add(url_imm3);
        urls.add(url_imm4);
        urls.add(url_imm5);

        ArrayList<String> list_n = new ArrayList<>();
        list_n.add("润肤水");
        list_n.add("润肤乳");
        list_n.add("洁面乳");
        list_n.add("其他");
        list_n.add("实惠套装");


        ArrayList<Integer> id = new ArrayList<>();
        for(int i=0;i<urls.size();i++){
            Intent intent = getIntent();
            int images = intent.getIntExtra("images", -1);
            id.add(images);
        }
        for(int i=0;i<id.size();i++){
            if(id.get(i)==i){
                getData(urls.get(i));
                tv_images.setText(list_n.get(i));
            }
        }
    }

    private void getData(String url){
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson= new Gson();
                EffectBean effectBean = gson.fromJson(result, EffectBean.class);
                lists = effectBean.getData();
                EffAdapter adapter = new EffAdapter();
                gv_images.setAdapter(adapter);
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
                convertView= View.inflate(ImagesActivity.this,R.layout.recycler_item,null);
                holder=new EffAdapter.ViewHolder();
                holder.goods_pic = (ImageView) convertView.findViewById(R.id.goods_pic);
                holder.goods_name = (TextView) convertView.findViewById(R.id.goods_name);
                holder.market_price = (TextView) convertView.findViewById(R.id.market_price);
                holder.shop_price = (TextView) convertView.findViewById(R.id.shop_price);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            Glide.with(ImagesActivity.this).load(lists.get(position).getGoods_img()).into(holder.goods_pic);
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
