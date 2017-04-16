package com.example.administrator.e_businessproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.activity.DetailActivity;
import com.example.administrator.e_businessproject.adapter.DefaultAdapter;
import com.example.administrator.e_businessproject.adapter.HotAdapter;
import com.example.administrator.e_businessproject.adapter.HotRecAdapter;
import com.example.administrator.e_businessproject.adapter.RecAdapter;
import com.example.administrator.e_businessproject.bean.HomeBean;
import com.example.administrator.e_businessproject.view.FullyLinearLayoutManager;
import com.example.administrator.e_businessproject.view.InnerGridView;
import com.example.administrator.e_businessproject.view.InnerListView;
import com.example.administrator.e_businessproject.view.RecyclerViewDivider;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 类的用途：首页
 * Created by ${孙鹊禹}
 * on 2017/4/13 19:57
 */

public class Frag_home extends Fragment{

    private View view;
    private String url = "http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
    private List<String> list_im = new ArrayList<>();
    List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> list_good= new ArrayList<>();
    private Banner banner;
    private RecyclerView recycler_spring;
    private RecyclerViewHeader mHeader;
    private ArrayList<ImageView> list_imm;
    private ArrayList<TextView> list_title;
    private List<HomeBean.DataBean.Ad1Bean> ad1;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                   HomeBean.DataBean data =  (HomeBean.DataBean) msg.obj;
                    ad1 = data.getAd1();
                    for(int i = 0; i< ad1.size(); i++){
                        String image = ad1.get(i).getImage();
                        list_im.add(image);
                    }

                    //设置图片加载器
                    banner.setImageLoader(new GlideImageLoader());
                    //设置banner动画效果
                    banner.setBannerAnimation(Transformer.FlipVertical);
                    //设置图片集合
                    banner.setImages(list_im);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();

                    //积分商城 每日签到
                    List<HomeBean.DataBean.Ad5Bean> ad5 = data.getAd5();
                    for(int i=0;i<ad5.size();i++){
                       Glide.with(getActivity()).load(ad5.get(i).getImage()).into(list_imm.get(i));
                        String title = ad5.get(i).getTitle();
                        list_title.get(i).setText(title);
                    }


                    //春季热销的集合
                    List<HomeBean.DataBean.BestSellersBean> bestSellers = data.getBestSellers();
                    for(int i=0;i<bestSellers.size();i++){
                        List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> goodsList = bestSellers.get(i).getGoodsList();
                        for(int j=0;j<goodsList.size();j++){
                            list_good.add(goodsList.get(j));
                        }
                    }

                    RecAdapter adapter = new RecAdapter(getActivity(),list_good);
                    recycler_spring.setLayoutManager(new FullyLinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                    recycler_spring.setAdapter(adapter);

                    //优惠活动的集合
                    List<HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean> activityInfoList = data.getActivityInfo().getActivityInfoList();
                    ArrayList<String> list_yh = new ArrayList<>();
                    final ArrayList<ImageView> vp_im = new ArrayList<>();
                    for(int i=0;i<activityInfoList.size();i++){
                        list_yh.add(activityInfoList.get(i).getActivityImg());
                        ImageView imageView = new ImageView(getActivity());
                        Glide.with(getActivity()).load(list_yh.get(i)).into(imageView);
                        vp_im.add(imageView);
                    }

                    banner_yh.setAdapter(new PagerAdapter() {
                        @Override
                        public int getCount() {
                            return Integer.MAX_VALUE;
                        }

                        @Override
                        public boolean isViewFromObject(View view, Object object) {
                            return view==object;
                        }

                        @Override
                        public void destroyItem(ViewGroup container, int position, Object object) {
                            ImageView imageView = vp_im.get(position%vp_im.size());
                            container.removeView(imageView);
                        }

                        @Override
                        public Object instantiateItem(ViewGroup container, int position) {
                            ImageView imageView = vp_im.get(position%vp_im.size());
                            container.addView(imageView);
                            /*banner_yh.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });*/
                            return imageView;
                        }
                    });

                    //热门专题集合
                    List<HomeBean.DataBean.SubjectsBean> subjects = data.getSubjects();

                    lv_hot.setAdapter(new HotAdapter(getActivity(),subjects));

                    //默认
                    List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList = data.getDefaultGoodsList();
                    gv_default.setAdapter(new DefaultAdapter(getActivity(),defaultGoodsList));

                    break;
            }
        }
    };
    private ViewPager banner_yh;
    private InnerListView lv_hot;
    private InnerGridView gv_default;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home,null);
        ButterKnife.bind(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

        getData();
    }

    private void initView() {
        banner = (Banner) view.findViewById(R.id.banner);
        list_imm = new ArrayList<>();
        ImageView im1 = (ImageView) view.findViewById(R.id.im1);
        ImageView im2 = (ImageView) view.findViewById(R.id.im2);
        ImageView im3 = (ImageView) view.findViewById(R.id.im3);
        ImageView im4 = (ImageView) view.findViewById(R.id.im4);
        list_title = new ArrayList<>();
        TextView title1 = (TextView) view.findViewById(R.id.title1);
        TextView title2 = (TextView) view.findViewById(R.id.title2);
        TextView title3 = (TextView) view.findViewById(R.id.title3);
        TextView title4 = (TextView) view.findViewById(R.id.title4);
        list_imm.add(im1);
        list_imm.add(im2);
        list_imm.add(im3);
        list_imm.add(im4);
        list_title.add(title1);
        list_title.add(title2);
        list_title.add(title3);
        list_title.add(title4);

        //春季热销
        recycler_spring = (RecyclerView) view.findViewById(R.id.recycler_spring);
        mHeader = (RecyclerViewHeader) view.findViewById(R.id.header);
        // 将顶部视图与RecyclerView关联即可
        //mHeader.attachTo(recycler_spring, true);

        banner_yh = (ViewPager) view.findViewById(R.id.banner_yh);

        //热门专题
        lv_hot = (InnerListView) view.findViewById(R.id.list_hot);

        //默认
        gv_default = (InnerGridView) view.findViewById(R.id.gv_default);

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                  Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("url",ad1.get(position).getAd_type_dynamic_data());
                startActivity(intent);
            }
        });
    }


    private void getData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(string, HomeBean.class);
                HomeBean.DataBean data = homeBean.getData();
                Message message = Message.obtain(handler,0,data);
                message.sendToTarget();
            }
        });
    }

    //图片加载器
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */

            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);

        }
    }
}
