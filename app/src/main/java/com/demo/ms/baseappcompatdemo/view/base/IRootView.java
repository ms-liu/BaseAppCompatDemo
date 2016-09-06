package com.demo.ms.baseappcompatdemo.view.base;

import android.view.View;

/**
 * MVP中根接口 用于Presenter与View的交互
 *      Created by ms on 2016/9/2.
 */
public interface IRootView {
    /**
     * 展示错误页面
     */
    void showError(String msg, View.OnClickListener clickListener);

    /**
     * 展示错误页面
     */
    void showEmpty(String msg, View.OnClickListener clickListener);

    /**
     * 展示加载中页面
     */
    void showLoading(String msg);

    /**
     * 隐藏加载中页面
     */
    void hideLoading();
}
