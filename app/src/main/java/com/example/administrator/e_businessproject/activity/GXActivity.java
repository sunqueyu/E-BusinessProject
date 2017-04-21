package com.example.administrator.e_businessproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.e_businessproject.R;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/17 20:29
 */

public class GXActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gx_layout);

        initView();
    }

    private void initView() {
        TabLayout tab_gx = (TabLayout) findViewById(R.id.tab_gx);
        ViewPager vp_gx = (ViewPager) findViewById(R.id.vp_gx);
    }
}
