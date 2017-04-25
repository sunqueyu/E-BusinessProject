package com.example.administrator.e_businessproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.activity.LoginActivity;
import com.example.administrator.e_businessproject.activity.OrderActivity;
import com.example.administrator.e_businessproject.bean.DingBean;
import com.example.administrator.e_businessproject.bean.GoodsBean;
import com.example.administrator.e_businessproject.view.QQListView;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的用途：购物车页面
 * Created by ${孙鹊禹}
 * on 2017/4/13 20:38
 */

public class Frag_car extends Fragment{

    private View view;
    private RelativeLayout car_null;
    private RelativeLayout car_detail;
    private List<GoodsBean.CartItemListBean> cartItemList;
    private QQListView car_listView;
    public CarAdapter adapter;
    private CheckBox cb_all;
    private TextView all_price;
    private double money;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_car,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        getData();
        //全选
        cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = ((CheckBox) v).isChecked();
                if(flag) {
                    for (int i = 0; i <cartItemList.size() ; i++) {
                        cartItemList.get(i).setChecked(true);
                        double parseInt = cartItemList.get(i).getPrice()*100/100d;
                        int count = cartItemList.get(i).getCount();
                        money+= parseInt*count ;
                        all_price.setText(money+"");
                    }
                    adapter.notifyDataSetChanged();
                }
                else{
                    for (int i=0;i<cartItemList.size();i++) {
                        cartItemList.get(i).setChecked(false);
                        money=0;
                        all_price.setText(money+"");
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });


    }

    //查询购物车
    private void getData(){
        int userID = LoginActivity.userID;
        String url = "http://169.254.94.62:8080/bullking1/cart?userID="+userID;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                GoodsBean goodsBean = gson.fromJson(result, GoodsBean.class);
                cartItemList = goodsBean.getCartItemList();
                Log.i("xxx",cartItemList+"");
                if(cartItemList.size()==0){
                    car_null.setVisibility(View.VISIBLE);
                    car_detail.setVisibility(View.GONE);
                }else{
                    car_detail.setVisibility(View.VISIBLE);
                    car_null.setVisibility(View.GONE);
                    adapters();
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    //删除
    private void deleteData(int productid,int userID){
        String url = "http://169.254.94.62:8080/bullking1/cart?productid="+productid+"&&userID="+userID;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                Log.i("xxx",result);
                adapter.notifyDataSetChanged();
                if(cartItemList.size()==0){
                    car_null.setVisibility(View.VISIBLE);
                    car_detail.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    public void adapters() {
        if(adapter ==null){
            adapter = new CarAdapter();
            car_listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    private class CarAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return cartItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return cartItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(getActivity(),R.layout.car_item,null);
                holder=new ViewHolder();
                holder.car_image = (ImageView) convertView.findViewById(R.id.car_image);
                holder.car_name = (TextView) convertView.findViewById(R.id.item_name);
                holder.car_price = (TextView) convertView.findViewById(R.id.item_price);
                holder.car_count = (TextView) convertView.findViewById(R.id.car_count);
                holder.cb_item = (CheckBox) convertView.findViewById(R.id.cb_item);
                holder.delete = (TextView) convertView.findViewById(R.id.delete);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            Glide.with(getActivity()).load(cartItemList.get(position).getPic()).into(holder.car_image);
            holder.car_name.setText(cartItemList.get(position).getName());
            holder.car_price.setText("￥"+cartItemList.get(position).getPrice());
            holder.car_count.setText(cartItemList.get(position).getCount()+"");
            //holder.cb_item.setChecked(cartItemList.get(position).isChecked());
            holder.cb_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //获得当前的状态设为相反的状态
                    //  boolean aBoolean = ((CheckBox) v).isChecked();
                    Boolean aBoolean = cartItemList.get(position).isChecked();
                    cartItemList.get(position).setChecked(!aBoolean);
                    boolean checked = cartItemList.get(position).isChecked();
                    adapter.notifyDataSetChanged();
                    Log.i("xxxxxx",checked+"");
                    if(checked){
                        double parseInt = cartItemList.get(position).getPrice()*100/100d;
                        int count = cartItemList.get(position).getCount();
                        money+= (parseInt*count) ;
                        all_price.setText(money+"");
                    }if(!checked){
                        Log.i("xxxxxxxxxxx",checked+"");
                        double parseInt = cartItemList.get(position).getPrice()*100/100d;
                        int count = cartItemList.get(position).getCount();
                        money-= (parseInt*count) ;
                        all_price.setText(money+"");
                    }
                    adapter.notifyDataSetChanged();

                    boolean Boolean_flag=true;
                    for (int i = 0; i <cartItemList.size(); i++) {
                        Boolean ba = cartItemList.get(i).isChecked();
                        if(!ba){
                            //如果有一个为false都为false
                            Boolean_flag=false;
                            cb_all.setChecked(false);
                        }
                    }
                    if(Boolean_flag){
                        cb_all.setChecked(Boolean_flag);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int productID = cartItemList.get(position).getProductID();
                    int userID = cartItemList.get(position).getUserID();
                    deleteData(productID,userID);
                    cartItemList.remove(position);
                    notifyDataSetChanged();
                    car_listView.turnToNormal();
                }
            });
            return convertView;
        }

        class ViewHolder{
            ImageView car_image;
            TextView car_name;
            TextView car_price;
            TextView car_count;
            CheckBox cb_item;
            TextView delete;
        }
    }

    private void initView() {
        car_null = (RelativeLayout) view.findViewById(R.id.rel_car1);
        car_detail = (RelativeLayout) view.findViewById(R.id.rel_car2);
        car_listView = (QQListView) view.findViewById(R.id.car_listview);
        cb_all = (CheckBox) view.findViewById(R.id.cb_all);
        //总价
        all_price = (TextView) view.findViewById(R.id.all_price);
        //逛一逛
        Button bu_g = (Button) view.findViewById(R.id.bu_g);
        bu_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace
            }       });
        //结算
        TextView tv_js = (TextView) view.findViewById(R.id.f3_jiesuan);
        tv_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                List<DingBean> list = new ArrayList<>();
                for(int i=0;i<cartItemList.size();i++){
                    if(cartItemList.get(i).isChecked()==true){
                        DingBean bean = new DingBean();
                        bean.price=cartItemList.get(i).getPrice();
                        bean.title=cartItemList.get(i).getName();
                        bean.im=cartItemList.get(i).getPic();
                        bean.id = cartItemList.get(i).getId();
                        list.add(bean);
                    }
                }
                intent.putExtra("listd", (Serializable) list);
                startActivity(intent);
            }
        });
    }
}
