package com.byteteam.douyin.logic.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.byteteam.douyin.logic.database.dao.AccessTokenDao;
import com.byteteam.douyin.logic.database.dao.ClientTokenDao;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.database.model.ClientToken;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
@Database(entities = {AccessToken.class, ClientToken.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AccessTokenDao accessTokenDao();

    public abstract ClientTokenDao clientTokenDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase get(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "my-database").build();
                }
            }
        }
        return INSTANCE;
    }
}
