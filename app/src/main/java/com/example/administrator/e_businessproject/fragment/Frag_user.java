package com.example.administrator.e_businessproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.e_businessproject.R;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/13 20:38
 */

public class Frag_user extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_user,null);
        return view;
    }
}
