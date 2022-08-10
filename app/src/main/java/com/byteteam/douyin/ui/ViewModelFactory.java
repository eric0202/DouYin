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
import com.byteteam.douyin.ui.rank.fragment.MovieViewModel;

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
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(RepositoryFactory.providerRankItemRepository(context));
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
