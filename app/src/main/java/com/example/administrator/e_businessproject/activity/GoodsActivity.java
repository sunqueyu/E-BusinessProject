package com.example.administrator.e_businessproject.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.ClassifyBean;
import com.example.administrator.e_businessproject.bean.DingBean;
import com.example.administrator.e_businessproject.bean.EffectBean;
import com.example.administrator.e_businessproject.bean.GoodsBean;
import com.example.administrator.e_businessproject.bean.HomeBean;
import com.example.administrator.e_businessproject.bean.YuDingBean;
import com.example.administrator.e_businessproject.fragment.Frag_car;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：商品详情页 添加到购物车
 * Created by ${孙鹊禹}
 * on 2017/4/19 20:43
 */

public class GoodsActivity extends AppCompatActivity{

    private List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> spring;
    private List<HomeBean.DataBean.SubjectsBean.GoodsListBean> hot;
    private List<HomeBean.DataBean.DefaultGoodsListBean> defaults;
    private List<EffectBean.DataBean> all;
    private List<EffectBean.DataBean> tab;
    private int sp;
    private int hp;
    private int dp;
    private int ap;
    private int tp;
    private int gp;
    private List<ClassifyBean.DataBean.GoodsBriefBean> goods;
    private ImageView a_pic;
    private TextView a_price;
    private TextView a_name;
    private Dialog dialog;
    private int count;
    private TextView a_num;
    private List<DingBean> listd;

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
        ImageView imm_car = (ImageView) findViewById(R.id.imm_car);
        //点击购物车图标跳转到购物车界面
        imm_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassifyActivity activity = new ClassifyActivity();
                Frag_car frag_car = (Frag_car) activity.getSupportFragmentManager().findFragmentByTag("frag_car");
                Intent intent = new Intent(GoodsActivity.this,Frag_car.class);
                startActivity(intent);
                finish();
            }
        });

        goods_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsActivity.this.finish();
            }
        });

        Intent intent = getIntent();
        sp = intent.getIntExtra("sp", -1);
        hp = intent.getIntExtra("hp", -1);
        dp = intent.getIntExtra("dp", -1);
        ap = intent.getIntExtra("ap", -1);
        tp = intent.getIntExtra("tp", -1);
        gp = intent.getIntExtra("gp", -1);
        if(sp >=0){
            spring = (List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX>) intent.getSerializableExtra("spring");
            Glide.with(GoodsActivity.this).load(spring.get(sp).getGoods_img()).into(shop_pic);
            shop_name.setText(spring.get(sp).getGoods_name());
            shop_money.setText("￥"+ spring.get(sp).getShop_price());
            market_money.setText("￥"+ spring.get(sp).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=spring.get(sp).getGoods_img();
            bean.price= spring.get(sp).getShop_price();
            bean.title =spring.get(sp).getGoods_name();
            bean.id=sp;
            listd.add(bean);
        }

        if(hp >=0){
            hot = (List<HomeBean.DataBean.SubjectsBean.GoodsListBean>) intent.getSerializableExtra("hot");
            Glide.with(GoodsActivity.this).load(hot.get(hp).getGoods_img()).into(shop_pic);
            shop_name.setText(hot.get(hp).getGoods_name());
            shop_money.setText("￥"+ hot.get(hp).getShop_price());
            market_money.setText("￥"+ hot.get(hp).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=hot.get(hp).getGoods_img();
            bean.price= hot.get(hp).getShop_price();
            bean.title =hot.get(hp).getGoods_name();
            bean.id=hp;
            listd.add(bean);
        }

        if(dp >=0){
            defaults = (List<HomeBean.DataBean.DefaultGoodsListBean>) intent.getSerializableExtra("default");
            Glide.with(GoodsActivity.this).load(defaults.get(dp).getGoods_img()).into(shop_pic);
            shop_name.setText(defaults.get(dp).getGoods_name());
            shop_money.setText("￥"+ defaults.get(dp).getShop_price());
            market_money.setText("￥"+ defaults.get(dp).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=defaults.get(dp).getGoods_img();
            bean.price= defaults.get(dp).getShop_price();
            bean.title =defaults.get(dp).getGoods_name();
            bean.id=dp;
            listd.add(bean);
        }

        if(ap >=0){
            all = (List<EffectBean.DataBean>) intent.getSerializableExtra("all");
            Glide.with(GoodsActivity.this).load(all.get(ap).getGoods_img()).into(shop_pic);
            shop_name.setText(all.get(ap).getGoods_name());
            shop_money.setText("￥"+ all.get(ap).getShop_price());
            market_money.setText("￥"+ all.get(ap).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=all.get(ap).getGoods_img();
            bean.price= all.get(ap).getShop_price();
            bean.title =all.get(ap).getGoods_name();
            bean.id=ap;
            listd.add(bean);
        }

        if(tp >=0){
            tab = (List<EffectBean.DataBean>) intent.getSerializableExtra("tab");
            Glide.with(GoodsActivity.this).load(tab.get(tp).getGoods_img()).into(shop_pic);
            shop_name.setText(tab.get(tp).getGoods_name());
            shop_money.setText("￥"+ tab.get(tp).getShop_price());
            market_money.setText("￥"+ tab.get(tp).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=tab.get(tp).getGoods_img();
            bean.price= tab.get(tp).getShop_price();
            bean.title =tab.get(tp).getGoods_name();
            bean.id=tp;
            listd.add(bean);
        }

        if(gp>=0){
            goods = (List<ClassifyBean.DataBean.GoodsBriefBean>) intent.getSerializableExtra("goodsBrief");
            Glide.with(GoodsActivity.this).load(goods.get(gp).getGoods_img()).into(shop_pic);
            shop_name.setText(goods.get(gp).getGoods_name());
            shop_money.setText("￥"+ goods.get(gp).getShop_price());
            market_money.setText("￥"+ goods.get(gp).getMarket_price());
            listd=new ArrayList<>();
            DingBean bean = new DingBean();
            bean.im=goods.get(gp).getGoods_img();
            bean.price= goods.get(gp).getShop_price();
            bean.title =goods.get(gp).getGoods_name();
            bean.id=gp;
            listd.add(bean);
        }

        TextView shop_car = (TextView) findViewById(R.id.shop_car);
        TextView shop_buy = (TextView) findViewById(R.id.shop_buy);

        //添加到购物车
        shop_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        //立即购买
        shop_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean state = LoginActivity.state;
                if(state==true){
                    //跳转到订单页
                    if(listd!=null){
                        Intent intent=new Intent(GoodsActivity.this,OrderActivity.class);
                        intent.putExtra("listd", (Serializable) listd);
                        startActivity(intent);
                    }
                }else if(state=false){
                    Toast.makeText(GoodsActivity.this, "还未登录,请登录", Toast.LENGTH_SHORT).show();
                    //dialog();
                    Intent intent = new Intent(GoodsActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void dialog(){
        LayoutInflater factory = LayoutInflater.from(GoodsActivity.this);
        View myView = factory.inflate(R.layout.dialog_car, null);
        dialogView(myView);
        myView.setPadding(10, 10, 10, 30);
        dialog = new AlertDialog.Builder(GoodsActivity.this)
        .setView(myView).create();
        //设置点击Dialog外部任意区域关闭Dialog
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager m =getWindowManager();
        Display d = m.getDefaultDisplay(); //为获取屏幕宽、高
        WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = d.getWidth(); //宽度设置为屏幕
        dialog.getWindow().setAttributes(p); //设置生效
    }

    //dialog控件
    private void dialogView(View myView) {
        a_pic = (ImageView) myView.findViewById(R.id.a_pic);
        a_price = (TextView) myView.findViewById(R.id.a_price);
        a_name = (TextView) myView.findViewById(R.id.a_name);
        a_num = (TextView) myView.findViewById(R.id.a_num);
        Button a_add = (Button) myView.findViewById(R.id.a_add);
        Button a_delete = (Button) myView.findViewById(R.id.a_delete);
        final int userID = LoginActivity.userID;
        count = 1;
        //数量增加
        a_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                a_num.setText(count+"");
            }
        });
        //数量减少
        a_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                a_num.setText(count+"");
            }
        });
        if(sp>=0){
            Glide.with(GoodsActivity.this).load(spring.get(sp).getGoods_img()).into(a_pic);
            a_name.setText(spring.get(sp).getGoods_name());
            a_price.setText("￥"+ spring.get(sp).getShop_price());

        }

        if(hp>=0){
            Glide.with(GoodsActivity.this).load(hot.get(hp).getGoods_img()).into(a_pic);
            a_name.setText(hot.get(hp).getGoods_name());
            a_price.setText("￥"+ hot.get(hp).getShop_price());
        }

        if(dp>=0){
            Glide.with(GoodsActivity.this).load(defaults.get(dp).getGoods_img()).into(a_pic);
            a_name.setText(defaults.get(dp).getGoods_name());
            a_price.setText("￥"+ defaults.get(dp).getShop_price());
        }

        if(ap>=0){
            Glide.with(GoodsActivity.this).load(all.get(ap).getGoods_img()).into(a_pic);
            a_name.setText(all.get(ap).getGoods_name());
            a_price.setText("￥"+ all.get(ap).getShop_price());
        }

        if(tp>=0){
            Glide.with(GoodsActivity.this).load(tab.get(tp).getGoods_img()).into(a_pic);
            a_name.setText(tab.get(tp).getGoods_name());
            a_price.setText("￥"+ tab.get(tp).getShop_price());
        }

        if(gp>=0){
            Glide.with(GoodsActivity.this).load(goods.get(gp).getGoods_img()).into(a_pic);
            a_name.setText(goods.get(gp).getGoods_name());
            a_price.setText("￥"+ goods.get(gp).getShop_price());
        }

        //确定添加到购物车
        Button a_button = (Button) myView.findViewById(R.id.a_button);
        a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sp>=0){
                    addData(sp,count,1,1,userID,a_name.getText().toString(),spring.get(sp).getGoods_img(),spring.get(sp).getShop_price());
                }
                if(hp>=0){
                    addData(hp,count,1,1,userID,a_name.getText().toString(),hot.get(hp).getGoods_img(),hot.get(hp).getShop_price());

                }
                if(dp>=0){
                    addData(dp,count,1,1,userID,a_name.getText().toString(),defaults.get(dp).getGoods_img(),defaults.get(dp).getShop_price());

                }
                if(ap>0){
                    addData(ap,count,1,1,userID,a_name.getText().toString(),all.get(ap).getGoods_img(),all.get(ap).getShop_price());

                }
                if(tp>=0){
                    addData(tp,count,1,1,userID,a_name.getText().toString(),tab.get(tp).getGoods_img(),tab.get(tp).getShop_price());

                }
                if(gp>=0){
                    addData(gp,count,1,1,userID,a_name.getText().toString(),goods.get(gp).getGoods_img(),goods.get(gp).getShop_price());

                }
            }
        });
    }

    //网络请求，把数据添加到后台数据库
    private void addData(int productID,int count,int colorID,int sizeID,int userID,String name,String pic,double price){
        String url="http://169.254.94.62:8080/bullking1/cart?";
        //封装参数
        RequestParams params=new RequestParams(url);
        params.addParameter("productID",productID);
        params.addParameter("count",count);
        params.addParameter("colorID",colorID);
        params.addParameter("sizeID",sizeID);
        params.addParameter("userID",userID);
        params.addParameter("name",name);
        params.addParameter("pic",pic);
        params.addParameter("price",price);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(GoodsActivity.this, "成功添加到购物车", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(GoodsActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
