package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.e_businessproject.R;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/25 14:31
 */

public class SureActivity extends AppCompatActivity{

    private String orders;
    private String names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sure_layout);
        initView();

        Intent intent = getIntent();
        orders = intent.getStringExtra("order");
        names = intent.getStringExtra("name");

    }

    private void initView() {
        Button bu_sub = (Button) findViewById(R.id.bu_sub);
        //立即支付
        bu_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SureActivity.this,PayDemoActivity.class);
                intent.putExtra("orders",orders);
                intent.putExtra("names",names);
                startActivity(intent);
            }
        });
    }

}