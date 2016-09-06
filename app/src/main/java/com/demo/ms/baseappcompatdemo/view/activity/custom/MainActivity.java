package com.demo.ms.baseappcompatdemo.view.activity.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.ms.baseappcompatdemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ms on 2016/9/6.
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.btn_loading)
    Button mBtnLoad;
    @Bind(R.id.btn_error)
    Button mBtnError;
    @Bind(R.id.btn_empty)
    Button mBtnEmpty;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("多状态页面");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_loading, R.id.btn_error, R.id.btn_empty})
    public void next(View view) {
        switch (view.getId()) {
            case R.id.btn_loading:
                startActivity(new Intent(MainActivity.this,LoadingActivity.class));
                break;
            case R.id.btn_error:
                startActivity(new Intent(MainActivity.this,ErrorActivity.class));
                break;
            case R.id.btn_empty:
                startActivity(new Intent(MainActivity.this,EmptyActivity.class));
                break;
        }
    }
}
