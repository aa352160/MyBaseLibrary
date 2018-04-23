package com.rongke.baselibrary.base.view;

import android.content.Context;
import android.util.AttributeSet;

import com.rongke.baselibrary.util.StringUtil;

/**
 * Created by jh352160 on 2018/3/26.
 */

public class BaseEditText extends android.support.v7.widget.AppCompatEditText {
    public BaseEditText(Context context) {
        super(context);
    }

    public BaseEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public String getTextStr(){
        return this.getText().toString();
    }

    public boolean isEmpty(){
        return StringUtil.isEmpty(getTextStr());
    }
}
