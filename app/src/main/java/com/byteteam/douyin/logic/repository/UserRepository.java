package com.byteteam.douyin.logic.repository;

import com.byteteam.douyin.logic.dataSource.UserDataSource;
import com.byteteam.douyin.logic.database.dao.UserDao;
import com.byteteam.douyin.logic.database.model.User;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.response.ResponseTransformer;
import com.byteteam.douyin.logic.network.service.UserService;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class UserRepository implements UserDataSource {

    UserDao userDao;

    public UserRepository(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public Maybe<User> getUser() {
        return userDao.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable insert(User user) {
        return userDao.insertUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<User> requestUser(String accessToken, String open_id) {
        Retrofit retrofit = NetWorkFactory.provideRetrofit();
        UserService userService = retrofit.create(UserService.class);
        return userService.getUser(accessToken,open_id)
                .compose(ResponseTransformer.obtain())
                .map(data->{
                    User user = new User(open_id);
                    user.setAvatar(data.getAvatar());
                    user.setCity(data.getCity());
                    user.setCountry(data.getCountry());
                    user.setGender(data.getGender());
                    user.setNickname(data.getNickname());

                    insert(user);
                    return user;
                });
    }
}
