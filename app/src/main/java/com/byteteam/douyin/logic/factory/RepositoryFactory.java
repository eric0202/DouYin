package com.byteteam.douyin.logic.factory;

import android.content.Context;

import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.dataSource.RankItemDataSource;
import com.byteteam.douyin.logic.dataSource.RankListDataSource;
import com.byteteam.douyin.logic.dataSource.UserDataSource;
import com.byteteam.douyin.logic.database.dao.ClientTokenDao;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.repository.AccessTokenRepository;
import com.byteteam.douyin.logic.repository.ClientTokenRepository;
import com.byteteam.douyin.logic.repository.LocalUserRepository;
import com.byteteam.douyin.logic.repository.RankItemRepository;
import com.byteteam.douyin.logic.repository.RankListRepository;
import com.byteteam.douyin.logic.repository.UserRepository;

/**
 * @introduction： 仓库工厂类
 * @author： 林锦焜
 * @time： 2022/8/7 18:11
 */
public class RepositoryFactory {

    public static AccessTokenDataSource providerAccessTokenRepository(Context context) {
        return new AccessTokenRepository(DaoFactory.providerAccessTokenDao(context));
    }

    public static ClientTokenDataSource providerClientTokenRepository(Context context) {
        return new ClientTokenRepository(DaoFactory.providerClientTokenDao(context));
    }

    public static RankItemDataSource providerRankItemRepository(Context context) {
        return new RankItemRepository(providerClientTokenRepository(context)
                        ,DaoFactory.providerRankItemDao(context));
    }

    public static RankListDataSource providerRankListRepository(Context context) {
        return new RankListRepository(providerClientTokenRepository(context)
                ,DaoFactory.providerRankListDao(context));
    }

    public static UserDataSource provideUserDataRepository(Context context){
        return new UserRepository(providerAccessTokenRepository(context)
                , DaoFactory.provideUserDao(context));
    }

    public static UserDataSource provideLocalUserDataRepository(Context context){
        return new LocalUserRepository(DaoFactory.provideUserDao(context));
    }

}
