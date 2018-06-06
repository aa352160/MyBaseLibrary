package com.rongke.baselibrary.base.view;

import android.content.Context;
import android.util.AttributeSet;

import com.bumptech.glide.request.RequestOptions;
import com.rongke.baselibrary.util.GlideApp;

/**
 * Created by jh352160 on 2018/2/6.
 */

public class BaseImageView extends android.support.v7.widget.AppCompatImageView {
    private Context context;

    public BaseImageView(Context context) {
        this(context, null);
    }

    public BaseImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void load(String url){
        GlideApp.with(getContext()).load(url).into(this);
    }

    public void loadCircle(String url){
        GlideApp.with(getContext()).load(url).apply(RequestOptions.circleCropTransform()).into(this);
    }
}
