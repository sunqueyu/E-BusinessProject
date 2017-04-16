package com.example.administrator.e_businessproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.e_businessproject.R;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/13 20:38
 */

public class Frag_classify extends Fragment{

    private String url = "http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_classify,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Button cat1 = (Button) view.findViewById(R.id.cat1);
        Button cat2 = (Button) view.findViewById(R.id.cat2);
        Button cat3 = (Button) view.findViewById(R.id.cat3);
        Button cat4 = (Button) view.findViewById(R.id.cat4);
        Button cat5 = (Button) view.findViewById(R.id.cat5);
        Button cat6 = (Button) view.findViewById(R.id.cat6);
    }
}
