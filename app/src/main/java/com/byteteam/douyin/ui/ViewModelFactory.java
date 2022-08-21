/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.byteteam.douyin.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.byteteam.douyin.logic.factory.RepositoryFactory;
import com.byteteam.douyin.ui.main.viewmodel.MyFansViewModel;
import com.byteteam.douyin.ui.main.viewmodel.WorksViewModel;
import com.byteteam.douyin.ui.rank.RankListViewModel;
import com.byteteam.douyin.ui.rank.fragment.FansViewModel;
import com.byteteam.douyin.ui.rank.fragment.RankViewModel;

/**
 * @introduction： ViewModel工厂类
 * @author： 林锦焜
 * @time： 2022/8/10 18:10
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    public static ViewModelFactory provide(Context context) {
        return new ViewModelFactory(context);
    }

    private final Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @Override
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RankViewModel.class)) {
            return (T) new RankViewModel(RepositoryFactory.providerRankItemRepository(context));
        } else if (modelClass.isAssignableFrom(RankListViewModel.class)) {
            return (T) new RankListViewModel(RepositoryFactory.providerRankListRepository(context));
        } else if (modelClass.isAssignableFrom(WorksViewModel.class)) {
            return (T) new WorksViewModel(RepositoryFactory.providerAccessTokenRepository(context)
                    , RepositoryFactory.provideWorksRepository(context));
        } else if (modelClass.isAssignableFrom(MyFansViewModel.class)) {
            return (T) new MyFansViewModel(RepositoryFactory.providerAccessTokenRepository(context)
                    , RepositoryFactory.provideMyFansRepository(context));
        }else if(modelClass.isAssignableFrom(FansViewModel.class)){
            return (T) new FansViewModel(RepositoryFactory.providerAccessTokenRepository(context)
                    ,RepositoryFactory.providerFansItemRepository(context));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
