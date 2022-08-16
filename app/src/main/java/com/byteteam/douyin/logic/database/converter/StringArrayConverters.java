package com.byteteam.douyin.logic.database.converter;

import androidx.room.TypeConverter;

import com.byteteam.douyin.util.StringUtil;

import java.util.Date;

/**
 * @introduction： Room数据转换器 String数组与String相互转换
 * @author： 林锦焜
 * @time： 2022/7/28 20:59
 */
public class StringArrayConverters {

    @TypeConverter
    public static String[] StringToArray(String value) {
        if (value == null || value.length() == 0) return new String[]{};
        return value.split(",");
    }

    @TypeConverter
    public static String fromString(String[] value) {
        return StringUtil.arrayToString(value);
    }

}
