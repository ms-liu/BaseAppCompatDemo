package com.demo.ms.baseappcompatdemo.view.activity.custom;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.demo.ms.baseappcompatdemo.R;
import com.demo.ms.baseappcompatdemo.view.activity.base.BaseMvpAppCompatActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Loading状态页面
 * Created by ms on 2016/9/6.
 */
public class LoadingActivity extends BaseMvpAppCompatActivity {
    @Bind(R.id.et_load)
    AppCompatEditText mEtLoad;

    @Bind(R.id.fl_target)
    FrameLayout mTargetView;

    @Override
    protected TransitionMode setTransitionMode() {
        return TransitionMode.TOP;
    }

    @Override
    protected View getLoadingTargetView() {
        return mTargetView;
    }

    @Override
    protected void initViewsAndEvents() {
        setTitle("Loading状态页面");
        showLoading("");
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_load;
    }


    @OnClick({R.id.btn_show, R.id.btn_hide})
    public void onClick(View view) {
        hideSystemKeyBoard(mEtLoad);
        switch (view.getId()) {
            case R.id.btn_show:
                showLoading(mEtLoad.getText().toString());
                break;
            case R.id.btn_hide:
                hideLoading();
                break;
        }
    }


    /**
     * 隐藏键盘
     * @param v
     */
    public void hideSystemKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
