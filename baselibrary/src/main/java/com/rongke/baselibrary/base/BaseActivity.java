package com.rongke.baselibrary.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rongke.baselibrary.R;
import com.rongke.baselibrary.util.AutoUtils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by jh352160 on 2018/2/5.
 */

public abstract class BaseActivity<E extends BasePresenter> extends FragmentActivity {
    protected Context mContext;
    public E mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.mPresenter = getPresenterInstance();
        if (mPresenter != null) {
            mPresenter.mContext = this;
            mPresenter.mView = this;
        }
        this.mContext = this;

        setContentView();
    }

    public void setContentView() {
        setContentView(R.layout.activity_base);
        FrameLayout container = findViewById(R.id.container);
        LinearLayout rootView = findViewById(R.id.rl_root_root);
//        LayoutInflater.from(this).inflate(setLayoutRes(),container);
        View containerView = LayoutInflater.from(this).inflate(setLayoutRes(), rootView, false);
        rootView.addView(containerView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT>=21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_primary));
        }

        findViewById(R.id.iv_icon_back).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { finish(); }
        });

        AutoUtils.setSize(this, false, 720, 1280);
        AutoUtils.auto(this);
        initView();
        initListener();
    }

    private E getPresenterInstance() {
        try {
            return ((Class<E>) ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0])
                    .newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract @LayoutRes int setLayoutRes();
    public abstract void initView();
    public void initListener(){}

    public void hideTitleBar(boolean isHidden){
        findViewById(R.id.common_title).setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    public void setTitle(String title){
        ((TextView) findViewById(R.id.tv_title)).setText(title);
    }

    /**
     * 显示标题栏右侧文本框
     * @return 标题栏右侧文本框对象
     */
    public TextView showRightText(String text, View.OnClickListener onClickListener){
        TextView rightText = findViewById(R.id.tv_right_text);
        rightText.setText(text);
        rightText.setOnClickListener(onClickListener);
        rightText.setVisibility(View.VISIBLE);
        return rightText;
    }
}
