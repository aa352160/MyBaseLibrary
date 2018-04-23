package com.rongke.baselibrary.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.rongke.baselibrary.base.BaseFragment;
import com.rongke.baselibrary.base.BasePresenter;

/**
 * Created by jh352160 on 2018/3/29.
 */

public abstract class LazyPagerFragment<E extends BasePresenter> extends BaseFragment<E>{
    private boolean isVisible = false;
    private boolean isContextInitialize = false;
    private boolean isInitialize = false;

    @Override
    public void initView() {}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isContextInitialize = true;
        if (isVisible && !isInitialize) {
            lazyInitView();
            isInitialize = true;
        }
    }

    public abstract void lazyInitView();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (!isInitialize && isVisible && isContextInitialize){
            lazyInitView();
            isInitialize = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isContextInitialize = false;
        isInitialize = false;
    }
}
