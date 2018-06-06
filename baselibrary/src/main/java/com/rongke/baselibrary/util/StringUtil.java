package com.rongke.baselibrary.util;

/**
 * Created by jh352160 on 2018/3/26.
 */

public class StringUtil {
    /**
     * 字符串是否为空
     */
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

    /**
     * 去除字符串中emoji
     */
    public static String replaceEmoji(String str) {
        if(!CommonUtil.isEmpty(str)){
            return str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
        }else{
            return str;
        }
    }
}
