package com.example.administrator.e_businessproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.fragment.Frag_all;

import java.util.ArrayList;

/**
 * 类的用途：所有商品的类 排序
 * Created by ${孙鹊禹}
 * on 2017/4/17 20:45
 */

public class AllActivity extends AppCompatActivity implements View.OnClickListener{


    private ArrayList<Fragment> list_f;
    private TextView tv_def;
    private TextView tv_up;
    private TextView tv_down;
    private FragmentManager manager;
    private Fragment f1;
    private Fragment f2;
    private Fragment f3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_layout);

        initView();

        list_f=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Frag_all frag_all =  new Frag_all();
            list_f.add(frag_all);
            //传入position 根据position排序
            Bundle bundle = new Bundle();
            bundle.putInt("pos", i);
            frag_all.setArguments(bundle);
        }

        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        f1 = list_f.get(0);
        f2 = list_f.get(1);
        f3 = list_f.get(2);
        transaction.add(R.id.frame_all, f1);
        transaction.add(R.id.frame_all, f2);
        transaction.add(R.id.frame_all, f3);
        showOrHide(f1, f2, f3);
        transaction.commit();
    }

    private void initView() {
        tv_def = (TextView) findViewById(R.id.tv_def);
        tv_up = (TextView) findViewById(R.id.tv_up);
        tv_down = (TextView) findViewById(R.id.tv_down);

        ImageView imageView = (ImageView) findViewById(R.id.all_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllActivity.this.finish();
            }
        });

        tv_def.setOnClickListener(this);
        tv_up.setOnClickListener(this);
        tv_down.setOnClickListener(this);
    }
    private void showOrHide(Fragment from, Fragment to1, Fragment to2) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(from);
        transaction.hide(to1);
        transaction.hide(to2);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_def:
                showOrHide(f1,f2,f3);
                break;
            case R.id.tv_up:
                showOrHide(f2,f1,f3);
                break;
            case R.id.tv_down:
                showOrHide(f3,f2,f1);
                break;
        }
    }
}
