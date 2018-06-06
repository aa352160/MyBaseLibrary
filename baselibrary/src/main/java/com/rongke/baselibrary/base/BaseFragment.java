package com.rongke.baselibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rongke.baselibrary.util.AutoUtils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by jh352160 on 2018/2/5.
 */

public abstract class BaseFragment<E extends BasePresenter> extends Fragment {
    protected Context mContext;
    public E mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = getPresenterInstance();
        if (mPresenter != null) {
            mPresenter.mContext = getContext();
            mPresenter.mView = this;
        }
        this.mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutRes(), container, false);
        AutoUtils.auto(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initListener();
    }

    public abstract @LayoutRes int setLayoutRes();
    public abstract void initView();
    public void initListener(){}

    private E getPresenterInstance() {
        try {
            return ((Class<E>) ((ParameterizedType) (this.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[0])
                    .newInstance();
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
