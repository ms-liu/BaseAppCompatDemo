package com.demo.ms.baseappcompatdemo.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.demo.ms.baseappcompatdemo.view.base.IRootView;

/**
 * Created by ms on 2016/9/6.
 */
public abstract class BaseMvpAppCompatActivity extends BaseAppCompatActivity implements IRootView {


    @Override
    protected TransitionMode setTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    public boolean toggleTransition() {
        return true;
    }

    @Override
    protected void getExtrasFromIntent(Bundle extras) {

    }

    @Override
    protected void getIntentForExtras(Intent intent) {

    }

    @Override
    public void showError(String msg, View.OnClickListener clickListener) {
        toggleShowError(true,msg,clickListener);
    }

    @Override
    public void showEmpty(String msg, View.OnClickListener clickListener) {
        toggleShowEmpty(toggleTransition(),msg,clickListener);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true,msg);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false,"");
    }

}
