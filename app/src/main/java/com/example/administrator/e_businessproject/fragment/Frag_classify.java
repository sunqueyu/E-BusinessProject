package com.example.administrator.e_businessproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.activity.ClassifyActivity;
import com.example.administrator.e_businessproject.activity.ImagesActivity;
import com.example.administrator.e_businessproject.adapter.StarAdapter;
import com.example.administrator.e_businessproject.bean.ClassifyBean;
import com.example.administrator.e_businessproject.view.InnerGridView;
import com.example.administrator.e_businessproject.view.OkHttpClientManager;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.List;


/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/13 20:38
 */

public class Frag_classify extends Fragment {

    private String url = "http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";
    private View view;
    private InnerGridView gv_star;
    private ArrayList<Button> btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_classify, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    private void initView() {

        //按肤质分类的按钮
        btn = new ArrayList<>();
        Button cat1 = (Button) view.findViewById(R.id.cat1);
        Button cat2 = (Button) view.findViewById(R.id.cat2);
        Button cat3 = (Button) view.findViewById(R.id.cat3);
        Button cat4 = (Button) view.findViewById(R.id.cat4);
        Button cat5 = (Button) view.findViewById(R.id.cat5);
        Button cat6 = (Button) view.findViewById(R.id.cat6);
        btn.add(cat1);
        btn.add(cat2);
        btn.add(cat3);
        btn.add(cat4);
        btn.add(cat5);
        btn.add(cat6);

        //按肤质的点击跳转
        for(int i = 0; i< btn.size(); i++){
            final int finalI = i;
            btn.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), ClassifyActivity.class);
                    intent.putExtra("position", finalI);
                    startActivity(intent);
                }
            });
        }

        //最上面图片
        ArrayList<ImageView> list_eff = new ArrayList<>();
        ImageView btn_mask = (ImageView) view.findViewById(R.id.btn_mask);
        ImageView btn_water = (ImageView) view.findViewById(R.id.btn_water);
        ImageView btn_lotion = (ImageView) view.findViewById(R.id.btn_lotion);
        ImageView btn_cleanser = (ImageView) view.findViewById(R.id.btn_cleanser);
        ImageView btn_other = (ImageView) view.findViewById(R.id.btn_other);
        ImageView btn_kit = (ImageView) view.findViewById(R.id.btn_kit);
        //list_eff.add(btn_mask);
        list_eff.add(btn_water);
        list_eff.add(btn_lotion);
        list_eff.add(btn_cleanser);
        list_eff.add(btn_other);
        list_eff.add(btn_kit);

        //面膜的点击跳转
        btn_mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClassifyActivity.class);
                intent.putExtra("mask",100);
                startActivity(intent);
            }
        });

        //图片的点击跳转
        for (int i = 0; i < list_eff.size(); i++) {
            final int finalI = i;
            list_eff.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ImagesActivity.class);
                    intent.putExtra("images", finalI);
                    startActivity(intent);
                }
            });
        }

        //按功效分类的按钮
        ArrayList<ImageView> list_gx = new ArrayList<>();
        ImageView btn_hydrating = (ImageView) view.findViewById(R.id.btn_hydrating);
        ImageView btn_soothing = (ImageView) view.findViewById(R.id.btn_soothing);
        ImageView btn_oil = (ImageView) view.findViewById(R.id.btn_oil);
        ImageView btn_whitening = (ImageView) view.findViewById(R.id.btn_whitening);
        ImageView btn_firming = (ImageView) view.findViewById(R.id.btn_firming);
        list_gx.add(btn_hydrating);
        list_gx.add(btn_soothing);
        list_gx.add(btn_oil);
        list_gx.add(btn_whitening);
        list_gx.add(btn_firming);

        //功效点击跳转
        for (int i = 0; i < list_gx.size(); i++) {
            final int finalI = i;
            list_gx.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),ClassifyActivity.class);
                    intent.putExtra("po", finalI);
                    startActivity(intent);
                }
            });
        }


        gv_star = (InnerGridView) view.findViewById(R.id.gv_star);

        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<ClassifyBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(ClassifyBean response) {
                //category集合
                List<ClassifyBean.DataBean.CategoryBean> category = response.getData().getCategory();
                //肤质按钮集合
                List<ClassifyBean.DataBean.CategoryBean.ChildrenBean> children = category.get(2).getChildren();
                Log.i("children", children + "");
                for (int i = 0; i < children.size(); i++) {
                    btn.get(i).setText("#" + children.get(i).getCat_name() + "#");
                }
                //goodsBrief集合
                List<ClassifyBean.DataBean.GoodsBriefBean> goodsBrief = response.getData().getGoodsBrief();
                gv_star.setAdapter(new StarAdapter(getActivity(), goodsBrief));
            }
        });
    }

}
