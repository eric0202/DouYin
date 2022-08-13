package com.byteteam.douyin.util;

/**
 * @introduction： 字符串工具类
 * @author： 林锦焜
 * @time： 2022/8/10 21:53
 */
public class StringUtil {

    /**
     * 将字符串数组转换成字符串
     */
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

    /**
     * 将大数转换成以万的计量方式
     */
    public static String numNarrow(long num) {
        if (num < 10000) {
            return String.valueOf(num);
        } else {
            return String.format("%.1f万",num / 10000.0);
        }
    }

}
