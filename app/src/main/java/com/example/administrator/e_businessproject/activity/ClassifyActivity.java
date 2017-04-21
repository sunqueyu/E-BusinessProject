package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.example.administrator.e_businessproject.fragment.Frag_tab;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/17 13:29
 */

public class ClassifyActivity extends AppCompatActivity {

    //面膜url
    String url_m1 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=9";
    String url_m2 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=10";
    String url_m3 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=23";

    //肤质url
    String url_but1 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=14";
    String url_but2 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=13";
    String url_but3 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=29";
    String url_but4 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=28";
    String url_but5 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=15";
    String url_but6 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=86588&encode=102e81c24a35dbdc9bb130c3c164434b&category_id=37";

    //功效url
    String url_img1 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=17";
    String url_img2 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=31";
    String url_img3 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=19";
    String url_img4 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=18";
    String url_img5 = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=60305&encode=b0358434fda8d7478bfc325b5828b721&category_id=20";
    private String position1;
    private String po1;
    private ViewPager view_pager;
    //tab的集合
    List<String> list_tab = new ArrayList<>();
    List<String> list_tab1 = new ArrayList<>();
    List<String> list_tab_mask = new ArrayList<>();
    private TabLayout tab_layout;
    //fragment的集合
    List<Fragment> list_fragment = new ArrayList<>();
    List<Fragment> list_fragment1 = new ArrayList<>();
    List<Fragment> list_mask = new ArrayList<>();
    //url的集合
    List<String> list_but_url = new ArrayList<>();
    List<String> list_image_url = new ArrayList<>();
    List<String> list_mask_url = new ArrayList<>();
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_layout);

        initView();

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        int po = intent.getIntExtra("po", -1);
        int mask = intent.getIntExtra("mask", -1);


        //面膜
        if (mask == 100) {
            getMaskData();
            Myadapter2 m1 = new Myadapter2(getSupportFragmentManager());
            view_pager.setAdapter(m1);
            //int i = Integer.parseInt(position);
            //view_pager.setCurrentItem(position);
            //这是让他们关联起来
            tab_layout.setupWithViewPager(view_pager);
            tab_layout.setTabsFromPagerAdapter(m1);
            tab_layout.setTabMode(TabLayout.MODE_FIXED);
            tv_title.setText("面膜");
        }

        //肤质
        if (position >= 0) {
            getServadata();
            Myadapter m1 = new Myadapter(getSupportFragmentManager());
            view_pager.setAdapter(m1);
            //int i = Integer.parseInt(position);
            view_pager.setCurrentItem(position);
            //这是让他们关联起来
            tab_layout.setupWithViewPager(view_pager);
            tab_layout.setTabsFromPagerAdapter(m1);
            tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tv_title.setText("按肤质");
        }


        //功效
        if (po >= 0) {
            getimageservadata();
            Myadapter1 m1 = new Myadapter1(getSupportFragmentManager());
            view_pager.setAdapter(m1);

            view_pager.setCurrentItem(po);
            //这是让他们关联起来
            tab_layout.setupWithViewPager(view_pager);
            tab_layout.setTabsFromPagerAdapter(m1);
            tab_layout.setTabMode(TabLayout.MODE_FIXED);
            tv_title.setText("按功效");
        }
    }

    private void initView() {
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        ImageView im_back = (ImageView) findViewById(R.id.im_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClassifyActivity.this.finish();
            }
        });
    }

    //面膜分类
    private void getMaskData() {
        list_mask_url.add(url_m1);
        list_mask_url.add(url_m2);
        list_mask_url.add(url_m3);

        list_tab_mask.add("贴式面膜");
        list_tab_mask.add("睡眠面膜");
        list_tab_mask.add("泥浆面膜");

        for (int i = 0; i < list_tab_mask.size(); i++) {
            tab_layout.addTab(tab_layout.newTab().setText(list_tab_mask.get(i)));
            Frag_tab fragment_tab = new Frag_tab(list_mask_url.get(i));
            list_mask.add(fragment_tab);
        }
    }

    //按肤质
    private void getServadata() {
        list_but_url.add(url_but1);
        list_but_url.add(url_but2);
        list_but_url.add(url_but3);
        list_but_url.add(url_but4);
        list_but_url.add(url_but5);
        list_but_url.add(url_but6);

        list_tab.add("混合性肤质");
        list_tab.add("中性肤质");
        list_tab.add("干性肤质");
        list_tab.add("油性肤质");
        list_tab.add("痘痘肤质");
        list_tab.add("敏感肤质");

        for (int i = 0; i < list_tab.size(); i++) {
            tab_layout.addTab(tab_layout.newTab().setText(list_tab.get(i)));
            Frag_tab fragment_tab = new Frag_tab(list_but_url.get(i));
            list_fragment.add(fragment_tab);
        }
    }

    //按功效
    private void getimageservadata() {
        list_image_url.add(url_img1);
        list_image_url.add(url_img2);
        list_image_url.add(url_img3);
        list_image_url.add(url_img4);
        list_image_url.add(url_img5);

        list_tab1.add("补水保湿");
        list_tab1.add("舒缓修复");
        list_tab1.add("控油祛痘");
        list_tab1.add("美白提亮");
        list_tab1.add("紧致抗皱");
        for (int i = 0; i < list_tab1.size(); i++) {
            tab_layout.addTab(tab_layout.newTab().setText(list_tab1.get(i)));
            Frag_tab fragment_tab = new Frag_tab(list_image_url.get(i));
            list_fragment1.add(fragment_tab);
        }
    }

    //肤质适配器
    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }

        //获取page对应的title
        @Override
        public CharSequence getPageTitle(int position) {
            return list_tab.get(position);
        }
    }

    //功效适配器
    class Myadapter1 extends FragmentPagerAdapter {

        public Myadapter1(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_fragment1.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment1.size();
        }

        //获取page对应的title
        @Override
        public CharSequence getPageTitle(int position) {
            return list_tab1.get(position);
        }
    }

    //面膜的适配器
    class Myadapter2 extends FragmentPagerAdapter {

        public Myadapter2(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_mask.get(position);
        }

        @Override
        public int getCount() {
            return list_mask.size();
        }

        //获取page对应的title
        @Override
        public CharSequence getPageTitle(int position) {
            return list_tab_mask.get(position);
        }
    }

}
