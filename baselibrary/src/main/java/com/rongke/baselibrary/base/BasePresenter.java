package com.rongke.baselibrary.base;


import android.content.Context;

/**
 * 公司：
 * 刘宇飞 创建 on 2017/3/6.
 * 描述：p 基类
 */

public abstract class BasePresenter<T>{
    public Context mContext;
    public T mView;

    public void setView(T v) {
        this.mView = v;
    }

    public void onDestroy() {
         mView=null;
    }
}