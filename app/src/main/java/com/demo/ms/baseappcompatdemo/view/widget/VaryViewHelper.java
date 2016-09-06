package com.demo.ms.baseappcompatdemo.view.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 多层页面Helper 具体实现类
 *  Created by ms on 2016/9/5.
 */
public class VaryViewHelper implements IVaryViewHelper {


    private final View mView;
    private ViewGroup.LayoutParams mViewParams;
    private ViewGroup mViewParent;
    private int mCurrentInedex;
    private View mCurrentView;

    /**
     *
     * @param view 第一次进入的View
     */
    public VaryViewHelper(View view){
        this.mView = view;
    }

    @Override
    public Context getContext() {
        return mView.getContext();
    }

    @Override
    public View inflateView(int layoutId) {
        return View.inflate(mView.getContext(),layoutId,null);
    }

    @Override
    public void showView(View view) {
        if (mViewParent == null){
            getCurrentViewParams();
        }
        mCurrentView = view;
        if (mViewParent.getChildAt(mCurrentInedex) != view){
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null){
                parent.removeView(view);
            }
            mViewParent.removeViewAt(mCurrentInedex);
            mViewParent.addView(view, mCurrentInedex, mViewParams);
        }
    }

    /**
     * 第一次
     *  获取当前View的布局参数
     *      在Parent中位置，方便替换
     */
    private void getCurrentViewParams() {
        mViewParams = mView.getLayoutParams();
        if (mView.getParent() != null){
            mViewParent =(ViewGroup) mView.getParent();
        }else {
            mViewParent = (ViewGroup) mView.getRootView().findViewById(android.R.id.content);
        }
        for (int i = 0;i < mViewParent.getChildCount() ; i++){
            if (mView == mViewParent.getChildAt(i)){
                mCurrentInedex = i;
                break;
            }
        }
    }

    @Override
    public void resetView() {
        showView(mView);
    }

    @Override
    public View getCurrentView() {
        return mCurrentView;
    }
}
