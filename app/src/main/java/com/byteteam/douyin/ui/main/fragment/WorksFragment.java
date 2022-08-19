package com.byteteam.douyin.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.FragmentRankBinding;
import com.byteteam.douyin.databinding.FragmentWorksBinding;
import com.byteteam.douyin.douyinapi.ApiUtil;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.WorksDataSource;
import com.byteteam.douyin.logic.factory.RepositoryFactory;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.logic.network.model.WorksResponse;
import com.byteteam.douyin.ui.ViewModelFactory;
import com.byteteam.douyin.ui.rank.fragment.RankViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/18 22:53
 */
public class WorksFragment extends Fragment {

    public static WorksFragment newInstance() {
        return new WorksFragment();
    }

    FragmentWorksBinding binding;

    private CompositeDisposable disposable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWorksBinding.inflate(getLayoutInflater(), container, false);
        disposable = new CompositeDisposable();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 更新标题栏标题
        requireActivity().setTitle("作品");
    }

    @Override
    public void onStart() {
        super.onStart();
        AccessTokenDataSource accessTokenDataSource = RepositoryFactory.providerAccessTokenRepository(requireActivity());
        disposable.add(accessTokenDataSource.getAccessToken()
                .doOnComplete(() -> { // 获取失败()
                    ApiUtil.sendAuth(requireActivity());
                })
                .subscribe(accessToken -> {
                    System.out.println("获取accessToken成功：" + accessToken);
                    WorksDataSource worksDataSource = RepositoryFactory.provideWorksRepository(requireActivity());
                    disposable.add(worksDataSource.queryMyWorks(accessToken.getAccessToken(), accessToken.getOpenId(), 0)
                            .subscribe(new Consumer<WorksResponse>() {
                                @Override
                                public void accept(WorksResponse worksResponse) throws Exception {
                                    System.out.println("获取个人作品成功：" + worksResponse.getList().size());
                                }
                            }, new ErrorConsumer() {
                                @Override
                                protected void error(NetException e) {
                                    System.out.println("获取个人作品失败：" + e.getMsg());
                                }
                            }));
                }));

    }

    @Override
    public void onStop() {
        super.onStop();
        disposable.clear();
    }
}
