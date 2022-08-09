package com.byteteam.douyin.logic.factory;

import android.content.Context;

import com.byteteam.douyin.logic.database.AppDatabase;
import com.byteteam.douyin.logic.database.dao.AccessTokenDao;
import com.byteteam.douyin.logic.database.dao.ClientTokenDao;

/**
 * @introduction： Dao工厂类
 * @author： 林锦焜
 * @time： 2022/8/7 18:11
 */
public class DaoFactory {

    public static AppDatabase providerAppDatabase(Context context) {
        return AppDatabase.get(context);
    }

    public static AccessTokenDao providerAccessTokenDao(Context context) {
        return providerAppDatabase(context).accessTokenDao();
    }

    public static ClientTokenDao providerClientTokenDao(Context context) {
        return providerAppDatabase(context).clientTokenDao();
    }

}
