package com.demo.ms.baseappcompatdemo.view.widget;

import android.content.Context;
import android.view.View;

/**
 * 多层页面接口
 * Created by ms on 2016/9/5.
 */
public interface IVaryViewHelper {

    /**
     * 获取上下文
     * @return context
     */
    Context getContext();

    /**
     *  填充新页面
     * @param layoutId
     * @return
     */
    View inflateView(int layoutId);

    /**
     * 展示页面
     * @param view
     */
    void showView(View view);

    /**
     * 重置页面
     */
    void resetView();

    /**
     * 获取当前显示页面
     * @return 当前显示页面
     */
    View getCurrentView();


}
