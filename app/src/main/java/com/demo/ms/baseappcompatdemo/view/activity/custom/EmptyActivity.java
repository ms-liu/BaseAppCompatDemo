package com.demo.ms.baseappcompatdemo.view.activity.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.demo.ms.baseappcompatdemo.R;
import com.demo.ms.baseappcompatdemo.view.activity.base.BaseAppCompatActivity;
import com.demo.ms.baseappcompatdemo.view.activity.base.BaseMvpAppCompatActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ms on 2016/9/6.
 */
public class EmptyActivity extends BaseMvpAppCompatActivity {
    @Bind(R.id.et_empty)
    AppCompatEditText mEtEmpty;

    @Bind(R.id.fl_target)
    FrameLayout mTargetView;

    @Override
    protected TransitionMode setTransitionMode() {
        return TransitionMode.BOTTOM;
    }

    @Override
    protected View getLoadingTargetView() {
        return mTargetView;
    }

    @Override
    protected void initViewsAndEvents() {
        setTitle("空数据页面");
        showEmpty("", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar(v,"暂无数据");
            }
        });
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_empty;
    }

    @OnClick({R.id.btn_show_empty})
    public void onClick(View view) {
        hideSystemKeyBoard(mEtEmpty);
        switch (view.getId()) {
            case R.id.btn_show_empty:
                showEmpty(mEtEmpty.getText().toString(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSnackbar(v,"暂无数据");
                    }
                });
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
