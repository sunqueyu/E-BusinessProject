package com.example.administrator.e_businessproject.application;

import android.app.Application;

import org.xutils.x;


/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/16 20:13
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //初始化xutils
        x.Ext.init(this);

        x.Ext.setDebug(false);

    }
}
