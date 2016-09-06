package com.demo.ms.baseappcompatdemo.view.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.ms.baseappcompatdemo.R;

/**
 * 多层页面控制器
 * Created by ms on 2016/9/5.
 */
public class VaryViewController {

    private VaryViewHelper mHelper;

    /**
     *
     * @param view 要替换的目标View
     */
    public VaryViewController(View view){
        this(new VaryViewHelper(view));
    }

    private VaryViewController(VaryViewHelper helper){
        this.mHelper = helper;
    }

    /**
     * 展示错误页面
     */
    public void showError(String msg, View.OnClickListener onClickListener){
        View layout = mHelper.inflateView(R.layout.vary_layout);
        TextView textView = (TextView) layout.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(msg)) {
            textView.setText(msg);
        } else {
            textView.setText("Error");
        }
        ImageView imageView = (ImageView) layout.findViewById(R.id.iv_msg);
        imageView.setImageResource(R.drawable.ic_error_page);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        mHelper.showView(layout);
    }

    /**
     * 展示加载中页面
     */
    public void showLoading(String msg){
        View layout = mHelper.inflateView(R.layout.loading);
        TextView textView = (TextView) layout.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(msg)) {
            textView.setText(msg);
        } else {
            textView.setText("请稍候...");
        }
        mHelper.showView(layout);
    }

    /**
     * 展示空状态页面
     */
    public void showEmpty(String msg, View.OnClickListener onClickListener){
        View layout = mHelper.inflateView(R.layout.vary_layout);
        TextView textView = (TextView) layout.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(msg)) {
            textView.setText(msg);
        } else {
            textView.setText("暂无数据");
        }
        ImageView imageView = (ImageView) layout.findViewById(R.id.iv_msg);
        imageView.setImageResource(R.drawable.ic_empty_page);
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        mHelper.showView(layout);
    }

    /**
     *  重置页面状态
     */
    public void resetView(){
        mHelper.resetView();
    }


}
