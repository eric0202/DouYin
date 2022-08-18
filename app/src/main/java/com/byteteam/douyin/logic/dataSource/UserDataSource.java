package com.byteteam.douyin.logic.dataSource;

import com.byteteam.douyin.logic.database.model.User;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface UserDataSource {

    Maybe<User> getUser();

    Completable insert(User user);

    Observable<User> requestUser(String accessToken, String open_id);
}
