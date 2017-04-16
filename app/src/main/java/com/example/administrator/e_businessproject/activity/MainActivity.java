package com.example.administrator.e_businessproject.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.HomeBean;
import com.example.administrator.e_businessproject.fragment.Frag_car;
import com.example.administrator.e_businessproject.fragment.Frag_classify;
import com.example.administrator.e_businessproject.fragment.Frag_home;
import com.example.administrator.e_businessproject.fragment.Frag_user;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager manager;
    private Frag_home frag_home;
    private Frag_classify frag_classify;
    private Frag_car frag_car;
    private Frag_user frag_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        frag_home = new Frag_home();
        frag_classify = new Frag_classify();
        frag_car = new Frag_car();
        frag_user = new Frag_user();

        transaction.add(R.id.frame, frag_home,"frag_home");
        transaction.add(R.id.frame, frag_classify,"frag_classify");
        transaction.add(R.id.frame, frag_car,"frag_car");
        transaction.add(R.id.frame, frag_user,"frag_user");
        showOrHide(frag_home, frag_classify, frag_car, frag_user);
        transaction.commit();

        TextView tv_home = (TextView) findViewById(R.id.tv_home);
        TextView tv_classify = (TextView) findViewById(R.id.tv_classify);
        TextView tv_car = (TextView) findViewById(R.id.tv_car);
        TextView tv_user = (TextView) findViewById(R.id.tv_user);

        tv_home.setOnClickListener(this);
        tv_classify.setOnClickListener(this);
        tv_car.setOnClickListener(this);
        tv_user.setOnClickListener(this);
    }
    private void showOrHide(Fragment from,Fragment to1,Fragment to2,Fragment to3){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(from);
        transaction.hide(to1);
        transaction.hide(to2);
        transaction.hide(to3);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_home:
                showOrHide(frag_home,frag_classify,frag_car,frag_user);
                break;

            case R.id.tv_classify:
                showOrHide(frag_classify,frag_home,frag_car,frag_user);
                break;

            case R.id.tv_car:
                showOrHide(frag_car,frag_classify,frag_home,frag_user);
                break;

            case R.id.tv_user:
                showOrHide(frag_user,frag_classify,frag_car,frag_home);
                break;
        }
    }
}
