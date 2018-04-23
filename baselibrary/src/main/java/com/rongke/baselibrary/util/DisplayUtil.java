package com.rongke.baselibrary.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by jh352160 on 2018/3/8.
 */

public class DisplayUtil {
    /**
     * 将px转化为dip，保证尺寸大小不变
     */
    public static int px2dip(Context context, float pxValue) {
        return (int) (pxValue / getScale(context) + 0.5f);
    }

    /**
     * 将dip值转化为对应的px值
     */
    public static int dip2px(Context context, float dipValue) {
        return (int) (dipValue * getScale(context) + 0.5f);
    }

    /**
     * 将对应的px值转化为sp值
     */
    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / getFontScale(context) + 0.5f);
    }

    /**
     * 将对应的sp值转化为px值
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (spValue * getFontScale(context) + 0.5f);
    }

    /**
     * 获取系统的转化比例
     */
    private static float getScale(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取系统的转化比例
     */
    private static float getFontScale(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }
}
