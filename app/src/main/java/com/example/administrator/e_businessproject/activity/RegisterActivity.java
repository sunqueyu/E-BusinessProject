package com.example.administrator.e_businessproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.e_businessproject.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途：注册界面
 * Created by ${孙鹊禹}
 * on 2017/4/20 18:25
 */

public class RegisterActivity extends AppCompatActivity{

    private EditText edit_name;
    private EditText edit_pwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        edit_name = (EditText) findViewById(R.id.reg_name);
        edit_pwd = (EditText) findViewById(R.id.reg_pwd);
        TextView tv_reg = (TextView) findViewById(R.id.btn_reg);
        ImageView reg_back = (ImageView) findViewById(R.id.reg_fan);

        reg_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });


        //点击注册按钮
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = edit_name.getText().toString();
                String pwds = edit_pwd.getText().toString();
                getRegister(names,pwds);

            }
        });
    }

    private void getRegister(String name,String pwd) {

        String url = "http://169.254.94.62:8080/bullking1/register?name="+name+"&pwd="+pwd;

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                RegisterActivity.this.finish();
                Log.i("result",result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();

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
