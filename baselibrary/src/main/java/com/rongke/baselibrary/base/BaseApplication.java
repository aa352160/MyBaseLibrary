package com.rongke.baselibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by jh352160 on 2018/3/22.
 */

public class BaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static Context getContext(){
        return context;
    }
}
