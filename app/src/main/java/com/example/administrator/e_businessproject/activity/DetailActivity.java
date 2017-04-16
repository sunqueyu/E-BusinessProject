package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.administrator.e_businessproject.R;

/**
 * 类的用途：webView页
 * Created by ${孙鹊禹}
 * on 2017/4/14 20:52
 */

public class DetailActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        initView();
    }

    private void initView() {
        WebView web_detail = (WebView) findViewById(R.id.web_detail);


        Intent intent= getIntent();
        int position = intent.getIntExtra("position",-1);
        String url = intent.getStringExtra("url");

        web_detail.loadUrl(url);
    }
}
