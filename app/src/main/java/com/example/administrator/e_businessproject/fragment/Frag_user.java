package com.example.administrator.e_businessproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.activity.LoginActivity;

/**
 * 类的用途：我的界面
 * Created by ${孙鹊禹}
 * on 2017/4/13 20:38
 */

public class Frag_user extends Fragment{

    private View view;
    public TextView tv_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_user,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        Button bu_login = (Button) view.findViewById(R.id.login);

        //点击跳转登录页面
        bu_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==200){
            String name = data.getStringExtra("name");
            tv_name.setText(name);
        }
    }
}
