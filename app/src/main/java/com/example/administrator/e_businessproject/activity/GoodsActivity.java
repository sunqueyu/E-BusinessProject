package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.EffectBean;
import com.example.administrator.e_businessproject.bean.HomeBean;

import java.util.List;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/19 20:43
 */

public class GoodsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goods_layout);

        initView();
    }

    private void initView() {
        ImageView goods_back = (ImageView) findViewById(R.id.goods_back);
        ImageView shop_pic= (ImageView) findViewById(R.id.shop_pic);
        TextView shop_name = (TextView) findViewById(R.id.shop_name);
        TextView shop_money = (TextView) findViewById(R.id.shop_money);
        TextView  market_money = (TextView) findViewById(R.id.market_money);

        goods_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        int sp = intent.getIntExtra("sp", -1);
        int hp = intent.getIntExtra("hp", -1);
        int dp = intent.getIntExtra("dp", -1);
        int ap = intent.getIntExtra("ap", -1);
        int tp = intent.getIntExtra("tp", -1);
        if(sp>=0){
            List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> spring = (List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX>) intent.getSerializableExtra("spring");
            Glide.with(GoodsActivity.this).load(spring.get(sp).getGoods_img()).into(shop_pic);
            shop_name.setText(spring.get(sp).getGoods_name());
            shop_money.setText("￥"+spring.get(sp).getShop_price());
            market_money.setText("￥"+spring.get(sp).getMarket_price());
        }

        if(hp>=0){
            List<HomeBean.DataBean.SubjectsBean.GoodsListBean> hot = (List<HomeBean.DataBean.SubjectsBean.GoodsListBean>) intent.getSerializableExtra("hot");
            Glide.with(GoodsActivity.this).load(hot.get(hp).getGoods_img()).into(shop_pic);
            shop_name.setText(hot.get(hp).getGoods_name());
            shop_money.setText("￥"+hot.get(hp).getShop_price());
            market_money.setText("￥"+hot.get(hp).getMarket_price());
        }

        if(dp>=0){
            List<HomeBean.DataBean.DefaultGoodsListBean> defaults = (List<HomeBean.DataBean.DefaultGoodsListBean>) intent.getSerializableExtra("default");
            Glide.with(GoodsActivity.this).load(defaults.get(dp).getGoods_img()).into(shop_pic);
            shop_name.setText(defaults.get(dp).getGoods_name());
            shop_money.setText("￥"+defaults.get(dp).getShop_price());
            market_money.setText("￥"+defaults.get(dp).getMarket_price());
        }

        if(ap>=0){
            List<EffectBean.DataBean> all = (List<EffectBean.DataBean>) intent.getSerializableExtra("all");
            Glide.with(GoodsActivity.this).load(all.get(ap).getGoods_img()).into(shop_pic);
            shop_name.setText(all.get(ap).getGoods_name());
            shop_money.setText("￥"+all.get(ap).getShop_price());
            market_money.setText("￥"+all.get(ap).getMarket_price());
        }

        if(tp>=0){
            List<EffectBean.DataBean> tab = (List<EffectBean.DataBean>) intent.getSerializableExtra("tab");
            Glide.with(GoodsActivity.this).load(tab.get(tp).getGoods_img()).into(shop_pic);
            shop_name.setText(tab.get(tp).getGoods_name());
            shop_money.setText("￥"+tab.get(tp).getShop_price());
            market_money.setText("￥"+tab.get(tp).getMarket_price());
        }

    }
}
