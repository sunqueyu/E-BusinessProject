package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.InfoBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * 类的用途：登录界面
 * Created by ${孙鹊禹}
 * on 2017/4/20 18:53
 */

public class LoginActivity extends AppCompatActivity{

    public static boolean state= false;
    private String names;
    private String pwds;
    private EditText log_name;
    private EditText log_pwd;
    public static int userID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        initView();
    }

    private void initView() {
        log_name = (EditText) findViewById(R.id.log_name);
        log_pwd = (EditText) findViewById(R.id.log_pwd);
        TextView btn_log = (TextView) findViewById(R.id.btn_log);
        final TextView tv_reg = (TextView) findViewById(R.id.tv_reg);
        ImageView log_back = (ImageView) findViewById(R.id.log_back);

        //返回
        log_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });


        //注册按钮点击调到注册
        tv_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state==false){
                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivityForResult(intent,101);
                }else{
                    Toast.makeText(LoginActivity.this, "已经登录了", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //登录按钮
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                names = log_name.getText().toString();
                pwds = log_pwd.getText().toString();
                load(names, pwds);

            }
        });
    }

    private void load(String name, String pwd) {

        String url="http://169.254.94.62:8080/bullking1/login?name="+name+"&pwd="+pwd;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Gson gson=new Gson();
                InfoBean bean = gson.fromJson(result, InfoBean.class);
                userID = bean.userID;
                if(bean.str.equals("success")){
                    state=true;
                    Intent intent = new Intent();
                    intent.putExtra("name",names);
                    LoginActivity.this.setResult(200,intent);
                    finish();
                }
                if(bean.str.equals("fail")){
                    Toast.makeText(LoginActivity.this, "用户名不存在或密码不正确", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(LoginActivity.this, "用户名不存在或密码不正确", Toast.LENGTH_SHORT).show();
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
