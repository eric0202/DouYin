package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * @introduction：
 * @author： 何文鹏
 * @time： 2022/8/19
 */

public interface UserDataSource {

    Maybe<User> queryUser();
}
