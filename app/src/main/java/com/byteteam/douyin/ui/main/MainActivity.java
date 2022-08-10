package com.byteteam.douyin.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.byteteam.douyin.databinding.ActivityMainBinding;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.ui.main.adapter.MainLayoutAdapter;
import com.byteteam.douyin.ui.main.fragment.RankFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * @introduction： 主界面Activity
 * @author： 林锦焜
 * @time： 2022/8/7 18:10
 */
public class MainActivity extends AppCompatActivity {

    private AccessTokenDataSource accessTokenDataSource;

    private ActivityMainBinding binding;

    private MainLayoutAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        /*
        // accessToken 暂时不需要用到，先放这里，获取个人信息的时候需要用到
        accessTokenDataSource = RepositoryFactory.providerAccessTokenRepository(this);

        findViewById(R.id.getAccessToken).setOnClickListener(v -> {
            Disposable disposable = accessTokenDataSource.getAccessToken(System.currentTimeMillis())
                    .doOnComplete(() -> ApiUtil.sendAuth(MainActivity.this))
                    .subscribe(accessToken -> {
                        System.out.println(accessToken);
                    });
        });
        });*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter == null) {
            List<Fragment> list = new ArrayList<>();
            list.add(RankFragment.newInstance());
            adapter = new MainLayoutAdapter(list, getSupportFragmentManager(), getLifecycle());
            binding.viewPager2.setAdapter(adapter);
        }
    }
}