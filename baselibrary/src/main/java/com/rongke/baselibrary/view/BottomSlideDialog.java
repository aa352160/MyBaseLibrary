package com.rongke.baselibrary.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.rongke.baselibrary.base.view.BaseDialog;

/**
 * Created by jh352160 on 2018/4/17.
 */
public abstract class BottomSlideDialog extends BaseDialog {
    private boolean isOutAnimation = false;

    @Override
    protected void setDisplayType(){
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.getDecorView().setPadding(0,0,0,0);
        window.setAttributes(lp);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().getWindow().getDecorView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    dismiss();
                }
                return true;
            }
        });

        getView().setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { }
        });

        slideToUp(getView());
    }

    @Override
    public void dismiss() {
        if (!isOutAnimation){
            isOutAnimation = true;
            slideToOut(getView());
        }
    }

    private void slideToUp(View inflate){
        TranslateAnimation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                                                          Animation.RELATIVE_TO_SELF, 0f,
                                                          Animation.RELATIVE_TO_SELF, 1f,
                                                          Animation.RELATIVE_TO_SELF, 0f);
        slide.setDuration(400);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        inflate.startAnimation(slide);
    }

    private void slideToOut(View inflate){
        TranslateAnimation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                                                          Animation.RELATIVE_TO_SELF, 0f,
                                                          Animation.RELATIVE_TO_SELF, 0f,
                                                          Animation.RELATIVE_TO_SELF, 1f);
        slide.setDuration(400);
        slide.setFillEnabled(true);
        slide.setFillAfter(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override public void onAnimationStart(Animation animation) { }
            @Override public void onAnimationRepeat(Animation animation) { }
            @Override public void onAnimationEnd(Animation animation) {
                BottomSlideDialog.super.dismiss();
            }
        });

        isOutAnimation = true;
        inflate.startAnimation(slide);
    }
}
