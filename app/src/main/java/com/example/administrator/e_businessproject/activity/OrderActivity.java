package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.bean.AddressBean;
import com.example.administrator.e_businessproject.bean.DingBean;
import com.example.administrator.e_businessproject.bean.YuDingBean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * 类的用途：提交订单
 * Created by ${孙鹊禹}
 * on 2017/4/25 10:49
 */

public class OrderActivity extends AppCompatActivity{

    private TextView tv_address;
    private SharedPreferences.Editor editor;
    private List<DingBean> list;
    private ListView order_lv;
    private EditText edit_name;
    private EditText edit_num;
    private String order_sn;
    private String names;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        initView();

        SharedPreferences preferences = getSharedPreferences("order", MODE_PRIVATE);
        editor = preferences.edit();
        boolean flag = preferences.getBoolean("flag", false);
        if(flag){
            //查询地址
            queryAddress();
        }

        Intent intent = getIntent();
        list = (List<DingBean>) intent.getSerializableExtra("listd");
        Log.i("vvvv",list+"");
        order_lv.setAdapter(new OrderAdapter());
    }

    //适配器
    private class OrderAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(OrderActivity.this,R.layout.order_item,null);
                holder=new ViewHolder();
                holder.z_price = (TextView) convertView.findViewById(R.id.z_price);
                holder.z_name = (TextView) convertView.findViewById(R.id.z_name);
                holder.z_num = (TextView) convertView.findViewById(R.id.z_num);
                holder.z_pic = (ImageView) convertView.findViewById(R.id.z_pic);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            Glide.with(OrderActivity.this).load(list.get(position).im).into(holder.z_pic);
            holder.z_price.setText("￥"+list.get(position).price);
            holder.z_name.setText(list.get(position).title);
            //holder.z_num.setText(list.get(position).id);
            return convertView;
        }

        class ViewHolder{
            ImageView z_pic;
            TextView z_price;
            TextView z_name;
            TextView z_num;
        }
    }

    //查询收货地址
    private void queryAddress(){
        String url = "http://169.254.94.62:8080/bullking1/addressdefault?zipCode="+LoginActivity.userID;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("xxx",result+"");
                Gson gson = new Gson();
                AddressBean addressBean = gson.fromJson(result, AddressBean.class);
                List<AddressBean.DataBean> data = addressBean.getData();
                tv_address.setText(data.get(0).getName()+" "+data.get(0).getPhoneNumber());
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

    private void initView() {
        ImageView order_back = (ImageView) findViewById(R.id.order_back);
        tv_address = (TextView) findViewById(R.id.tv_address);
        ImageView add_address = (ImageView) findViewById(R.id.add_address);
        order_lv = (ListView) findViewById(R.id.order_lv);
        Button bu_order = (Button) findViewById(R.id.z_button);

        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.this.finish();
            }
        });

        //http://169.254.94.62:8080/bullking1/save?name=小白&phoneNumber=12312312312&fixedtel=北京&provinceId=北京&cityId=海淀&areaId=上地&areaDetail=上地7号&zipCode=1(定为userId的值但是拼写的时候写这个地段)

        //添加收货地址
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                final AlertDialog dialog = builder.create();
                View view = View.inflate(OrderActivity.this,R.layout.address_dialog,null);
                dialog.setView(view);
                dialog.show();

                edit_name = (EditText) view.findViewById(R.id.edit_name);
                edit_num = (EditText) view.findViewById(R.id.edit_phoneNum);
                Button sure = (Button) view.findViewById(R.id.sure);

                sure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        addAddress(edit_name.getText().toString(), edit_num.getText().toString());

                    }
                });


            }
        });

        //提交订单
        bu_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDatas();
            }
        });
    }

    private void postDatas() {
        //进行与后台交互，将参数封装到post方法中进行与后台交互，生成预支付订单的信息返回
        AsyncHttpClient client=new AsyncHttpClient();
        String url="http://lexue365.51dangao.cn/api/order/add_order";
        client.addHeader("userid",465+"");
        client.addHeader("cltid", "1");
        client.addHeader("token", "bbb6e99c4cd22930ea4c945d9932f98a");
        client.addHeader("mobile", "15718812708");

        com.loopj.android.http.RequestParams params = new com.loopj.android.http.RequestParams();
        params.put("activity_id",4);
        params.put("time_id",2927);
        params.put("child_num",1);

        params.put("contact",list.get(0).title);
        params.put("mobile","15718812708");
        params.put("remark",1);
        client.post(getApplicationContext(), url, params, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                Log.i("xxx",responseString);
                Gson gson=new Gson();
                YuDingBean bean = gson.fromJson(responseString, YuDingBean.class);
                //拿到订单号
                order_sn = bean.getData().getOrder_sn();
                names = bean.getData().getGoods_name();
            }

        });
        Intent in=new Intent(OrderActivity.this,SureActivity.class);
        in.putExtra("order",order_sn);
        in.putExtra("name",names);
        startActivity(in);//跳转到选择支付方式的界面


    }

    //添加收货地址
    private void addAddress(String name,String num){
        String url = "http://169.254.94.62:8080/bullking1/save?name="+name+"&phoneNumber="+num+"&fixedtel=北京&provinceId=北京&cityId=海淀&areaId=上地&areaDetail=上地7号&zipCode="+ LoginActivity.userID;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(OrderActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                editor.putBoolean("flag",true);
                editor.commit();
                tv_address.setText(edit_name.getText().toString()+"  "+edit_num.getText().toString());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(OrderActivity.this, "添加失败", Toast.LENGTH_SHORT).show();

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
