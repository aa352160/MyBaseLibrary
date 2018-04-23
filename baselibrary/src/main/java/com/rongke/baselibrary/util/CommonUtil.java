package com.rongke.baselibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jh352160 on 2018/2/9.
 */

public class CommonUtil {
    public static void showToast(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
