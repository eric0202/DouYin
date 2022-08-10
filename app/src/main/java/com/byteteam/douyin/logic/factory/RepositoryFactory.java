package com.byteteam.douyin.logic.factory;

import android.content.Context;

import com.byteteam.douyin.logic.dataSource.ClientTokenDataSource;
import com.byteteam.douyin.logic.database.dao.ClientTokenDao;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.repository.AccessTokenRepository;
import com.byteteam.douyin.logic.repository.ClientTokenRepository;
import com.byteteam.douyin.logic.repository.RankItemRepository;

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

    public static RankItemRepository providerRankItemRepository(Context context) {
        return new RankItemRepository(providerClientTokenRepository(context)
                        ,DaoFactory.providerRankItemDao(context));
    }

}
