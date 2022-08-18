package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface UserDataSource {

    Maybe<User> queryUser();
}
