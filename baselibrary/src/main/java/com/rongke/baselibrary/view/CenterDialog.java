package com.rongke.baselibrary.view;

import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.rongke.baselibrary.base.view.BaseDialog;
import com.rongke.baselibrary.util.AutoUtils;

/**
 * Created by jh352160 on 2018/4/17.
 */
public abstract class CenterDialog extends BaseDialog {
    @Override
    protected void setDisplayType(){
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
        window.getDecorView().
                setPadding(AutoUtils.getDisplayWidthValue(100), 0, AutoUtils.getDisplayWidthValue(100), 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    }
}
