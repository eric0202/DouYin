package com.byteteam.douyin.logic.repository;

import com.byteteam.douyin.logic.dataSource.UserDataSource;
import com.byteteam.douyin.logic.database.dao.UserDao;
import com.byteteam.douyin.logic.database.model.User;

import java.lang.reflect.UndeclaredThrowableException;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LocalUserRepository implements UserDataSource {
    private UserDao userDao;

    public LocalUserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Maybe<User> queryUser() {
        return userDao.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .defaultIfEmpty(new User())
                .flatMap((Function<User, MaybeSource<User>>) user ->{
                    if(user == null){
                        return null;
                    }
                    return Maybe.just(user);
                });
    }
}
