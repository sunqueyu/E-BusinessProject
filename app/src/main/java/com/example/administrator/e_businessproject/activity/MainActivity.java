package com.example.administrator.e_businessproject.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.example.administrator.e_businessproject.R;
import com.example.administrator.e_businessproject.fragment.Frag_car;
import com.example.administrator.e_businessproject.fragment.Frag_classify;
import com.example.administrator.e_businessproject.fragment.Frag_home;
import com.example.administrator.e_businessproject.fragment.Frag_user;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager manager;
    private Frag_home frag_home;
    private Frag_classify frag_classify;
    private Frag_car frag_car;
    private Frag_user frag_user;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;
    private boolean state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        frag_home = new Frag_home();
        frag_classify = new Frag_classify();
        frag_car = new Frag_car();
        frag_user = new Frag_user();

        transaction.add(R.id.frame, frag_home,"frag_home");
        transaction.add(R.id.frame, frag_classify,"frag_classify");
        transaction.add(R.id.frame, frag_car,"frag_car");
        transaction.add(R.id.frame, frag_user,"frag_user");
        transaction.addToBackStack(null);
        showOrHide(frag_home, frag_classify, frag_car, frag_user);
        transaction.commit();


        RadioButton rb_home = (RadioButton) findViewById(R.id.rb_home);
        RadioButton rb_classify = (RadioButton) findViewById(R.id.rb_classify);
        RadioButton rb_car = (RadioButton) findViewById(R.id.rb_car);
        RadioButton rb_user = (RadioButton) findViewById(R.id.rb_user);

        rb_home.setChecked(true);

        rb_home.setOnClickListener(this);
        rb_classify.setOnClickListener(this);
        rb_car.setOnClickListener(this);
        rb_user.setOnClickListener(this);

        preferences = getSharedPreferences("flag",MODE_PRIVATE);
        editor = preferences.edit();
    }
    private void showOrHide(Fragment from,Fragment to1,Fragment to2,Fragment to3){
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.show(from);
        transaction.hide(to1);
        transaction.hide(to2);
        transaction.hide(to3);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rb_home:
                showOrHide(frag_home,frag_classify,frag_car,frag_user);
                break;

            case R.id.rb_classify:
                showOrHide(frag_classify,frag_home,frag_car,frag_user);
                break;

            case R.id.rb_car:
                state = LoginActivity.state;
                if(state==false){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivityForResult(intent,100);
                }else{
                    showOrHide(frag_car,frag_classify,frag_home,frag_user);
                }

                break;

            case R.id.rb_user:
                showOrHide(frag_user,frag_classify,frag_car,frag_home);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==200){
            String name = data.getStringExtra("name");
            showOrHide(frag_car,frag_classify,frag_home,frag_user);
            frag_user.tv_name.setText(name);
        }
    }
}
