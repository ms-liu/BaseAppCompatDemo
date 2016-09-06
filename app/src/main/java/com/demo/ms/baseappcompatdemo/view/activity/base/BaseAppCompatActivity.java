package com.demo.ms.baseappcompatdemo.view.activity.base;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.demo.ms.baseappcompatdemo.R;
import com.demo.ms.baseappcompatdemo.view.widget.VaryViewController;

import butterknife.ButterKnife;

/**
 * Activity基类方法
 *  部分方法可以根据需求重载，如：nextPager(),showToast(),toggleShow()...
 *
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    /**
     * 屏幕密度
     */
    protected float mScreenDensity;

    /**
     * 屏幕高度
     */
    protected int mScreenHeight;

    /**
     * 屏幕宽度
     */
    protected int mScreenWidth;

    /**
     * 转场模式
     * ENUM TRANSITION CHANGE MODE
     */
    public enum TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, Gravity, FADE
    }

    private VaryViewController mVaryViewController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //初始化转场动画
        initTransition();
        super.onCreate(savedInstanceState);
        //初始化意图
        initIntentAndExtras();
        //初始化屏幕参数
        initScreenParams();
        //填充布局
        if (getContentViewLayoutId() != 0) {
            setContentView(getContentViewLayoutId());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        //自定义其他初始化
        initViewsAndEvents();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mVaryViewController = new VaryViewController(getLoadingTargetView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * @return 加载替换的View
     */
    protected abstract View getLoadingTargetView();

    /**
     * 初始化view和events
     */
    protected abstract void initViewsAndEvents();

    /**
     * 设置Layout Id
     * @return Layout Id
     */
    protected abstract int getContentViewLayoutId();

    /*--------------------------------------------------------------------------------------------*/

    /**
     * 初始化转场动画
     */
    private void initTransition() {
        if (toggleTransition()){
            switch (setTransitionMode()){
                case LEFT:
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    break;
                case RIGHT:
                    overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    break;
                case TOP:
                    overridePendingTransition(R.anim.top_in, R.anim.top_out);
                    break;
                case BOTTOM:
                    overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out);
                    break;
                case SCALE:
                    overridePendingTransition(R.anim.scale_in, R.anim.scale_out);
                    break;
                case FADE:
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    break;
            }
        }
    }

    /**
     * 设置转场模式{@link TransitionMode}
     * @return TransitionMode 转场模式
     */
    protected abstract TransitionMode setTransitionMode();

    /**
     * 转场开关
     * @return true 开 false 关
     */
    public abstract boolean toggleTransition();

    /*--------------------------------------------------------------------------------------------*/

    /**
     * 初始化Intent和Bundle
     */
    private void initIntentAndExtras() {
        Intent intent = getIntent();
        getIntentForExtras(intent);
        Bundle extras = intent.getExtras();
        if (extras != null){
            getExtrasFromIntent(extras);
        }
    }

    /**
     * @param extras 跳转Bundle
     */
    protected abstract void getExtrasFromIntent(Bundle extras);

    /**
     * @param intent  跳转Intent
     */
    protected abstract void getIntentForExtras(Intent intent);

    /*--------------------------------------------------------------------------------------------*/

    /**
     * 初始化屏幕参数
     */
    private void initScreenParams() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;
    }

    /**
     * 统一Toast的方法
     * @param msg 可以重载多种方法参数
     */
    protected void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 统一Snackbar的方法
     * @param msg 可以重载多种方法参数
     */
    protected void showSnackbar(View views,String msg) {
        Snackbar snackbar = Snackbar.make(views, msg, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mVaryViewController) {
            throw new IllegalArgumentException("You must return a right target view for loading and this target's parent  ");
        }

        if (toggle) {
            mVaryViewController.showLoading(msg);
        } else {
            mVaryViewController.resetView();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewController.resetView();
        }
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewController.showError(msg, onClickListener);
        } else {
            mVaryViewController.resetView();
        }
    }

    /**
     * 进入下张页面
     *  可以重载多种形式
     * start new Activity
     */
    protected void nextPager(@NonNull Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
