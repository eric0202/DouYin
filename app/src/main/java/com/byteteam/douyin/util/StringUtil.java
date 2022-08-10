package com.byteteam.douyin.util;

/**
 * @introduction： 字符串工具类
 * @author： 林锦焜
 * @time： 2022/8/10 21:53
 */
public class StringUtil {

    public static String arrayToString(String[] value) {
        StringBuilder result = new StringBuilder();
        if (value != null) {
            for (int i = 0; i < value.length; i++) {
                if (i > 0) {
                    result.append(",");
                }
                result.append(value[i]);
            }
        }
        return result.toString();
    }

}
