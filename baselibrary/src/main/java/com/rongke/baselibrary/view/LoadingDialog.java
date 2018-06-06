package com.rongke.baselibrary.view;

import com.rongke.baselibrary.R;

/**
 * Created by jh352160 on 2018/5/23.
 */
public class LoadingDialog extends CenterDialog {
    @Override
    protected void initView() {
        setCancelable(false);
    }

    @Override
    public int setLayoutRes() {
        return R.layout.dialog_loading;
    }
}
