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

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)||"null".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }
}
