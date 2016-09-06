package com.demo.ms.baseappcompatdemo.view.activity.custom;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.demo.ms.baseappcompatdemo.R;
import com.demo.ms.baseappcompatdemo.view.activity.base.BaseMvpAppCompatActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by ms on 2016/9/6.
 */
public class ErrorActivity extends BaseMvpAppCompatActivity {
    @Bind(R.id.et_error)
    AppCompatEditText mEtError;

    @Bind(R.id.fl_target)
    FrameLayout mTargetView;

    @Override
    protected TransitionMode setTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected View getLoadingTargetView() {
        return mTargetView;
    }

    @Override
    protected void initViewsAndEvents() {
        setTitle("Error状态页面");
        showError("网络异常,点击重试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar(v,"刷新重试");
            }
        });
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_error;
    }


    @OnClick({R.id.btn_show_error})
    public void onClick(View view) {
        hideSystemKeyBoard(mEtError);
        switch (view.getId()) {
            case R.id.btn_show_error:
                showError(mEtError.getText().toString(), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSnackbar(v,"刷新重试");
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
