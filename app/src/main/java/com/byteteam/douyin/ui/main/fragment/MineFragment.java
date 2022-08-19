package com.byteteam.douyin.ui.main.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.byteteam.douyin.R;
import com.byteteam.douyin.databinding.FragmentMineBinding;
import com.byteteam.douyin.douyinapi.ApiUtil;
import com.byteteam.douyin.logic.dataSource.AccessTokenDataSource;
import com.byteteam.douyin.logic.dataSource.UserDataSource;
import com.byteteam.douyin.logic.database.model.AccessToken;
import com.byteteam.douyin.logic.database.model.User;
import com.byteteam.douyin.logic.factory.RepositoryFactory;
import com.byteteam.douyin.logic.network.exception.ErrorConsumer;
import com.byteteam.douyin.logic.network.exception.NetException;
import com.byteteam.douyin.ui.main.adapter.SectionsPagerAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation;
import retrofit2.http.Url;

/**
 * @introduction： 个人主页Fragment
 * @author： 何文鹏
 * @time： 2022/8/16 21:53
 */
public class MineFragment extends Fragment {


    public MineFragment() {
    }

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    private FragmentMineBinding binding;

    private boolean singleLine = true;

    private View.OnClickListener noFunctionListener;

    private View.OnClickListener getUserListener;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(getLayoutInflater(),container,false);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(requireActivity().getTitle());

        noFunctionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoFunction();
            }
        };

        getUserListener = new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View view) {
                UserDataSource userDataSource = RepositoryFactory.provideUserDataRepository(getContext());
                userDataSource.queryUser()
                        .doOnComplete(()->{
                            ApiUtil.sendAuth(getActivity());
                            Toast.makeText(getContext(),"FAILED TO GET USER",Toast.LENGTH_SHORT);
                        })
                        .subscribe(user -> {
                            System.out.println("user: " + user);
                            displayUser(user);
                        });

            }
        };



        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(requireContext(), requireActivity().getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        ImageButton btn_expand = binding.btnExpand;
        btn_expand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView tv = binding.tvInfo;
                singleLine = !singleLine;
                tv.setSingleLine(singleLine);
            }
        });

        // 设置背景图片
        setBg();

        // 打开页面先尝试从数据库加载user
        initUser();

        binding.imgAvatar.setOnClickListener(getUserListener);

        bindNoFunction();

//        displayUser(new User().getExampleUser());
        return binding.getRoot();
    }

    // 点开页面的时候加载user
    @SuppressLint("CheckResult")
    private void initUser() {
        UserDataSource userDataSource = RepositoryFactory.provideLocalUserDataRepository(getContext());
        userDataSource.queryUser()
                .doOnComplete(()->{
                    Toast.makeText(getContext(),"FAILED TO GET USER",Toast.LENGTH_SHORT);
                })
                .subscribe(user -> {
                    System.out.println("user: " + user);
                    displayUser(user);
                });
    }

    private void bindNoFunction() {
        binding.tvEditProfile.setOnClickListener(noFunctionListener);
        binding.tvNewFriends.setOnClickListener(noFunctionListener);
        binding.clStore.setOnClickListener(noFunctionListener);
        binding.clOpen.setOnClickListener(noFunctionListener);
        binding.llRates.setOnClickListener(noFunctionListener);
    }

    @SuppressLint("CheckResult")
    private void refreshUser(){
        UserDataSource userDataSource = RepositoryFactory.provideUserDataRepository(getContext());
        userDataSource.queryUser()
                .doOnComplete(()->{
                    ApiUtil.sendAuth(getActivity());
                    Toast.makeText(getContext(),"FAILED TO GET USER",Toast.LENGTH_SHORT);
                })
                .subscribe(user -> {
                    System.out.println("user: " + user);
                    displayUser(user);
                });
    };


    // ui中展示user信息
    private void displayUser(User user) {

        RequestOptions options = RequestOptions.bitmapTransform(new CropCircleWithBorderTransformation(3, Color.WHITE));


        // 判断user是否为空
        if (user.getOpen_id().equals("")){
            binding.tvValueNickname.setText(R.string.tap2login);
            Glide.with(this).load(R.drawable.r_key).apply(options).into(binding.imgAvatar);
        }else{
            if (!Objects.equals(user.getCity(), "")) {
                binding.tvValueCity.setText(user.getCity());
            }

            if (!Objects.equals(user.getCountry(), "")){
                binding.tvValueCountry.setText(user.getCountry());
            }

            if (!Objects.equals(user.getNickname(), "")){
                binding.tvValueNickname.setText(user.getNickname());
            }

//            if (!Objects.equals(user.getIntroduction(), "")){
//                binding.tvInfo.setText(user.getIntroduction());
//            }


            if (Objects.equals(user.getAvatar(), "")) {
                Glide.with(this).load(R.drawable.r_key).apply(options).into(binding.imgAvatar);
            }else{
                Glide.with(this).load(user.getAvatar()).apply(options).into(binding.imgAvatar);
            }

//            switch (user.getGender()){
//                case "0":
//                    binding.tvValueGender.setText(R.string.unknown_gender);
//                case "1":
//                    binding.tvValueGender.setText(R.string.gender_female);
//                case "2":
//                    binding.tvValueGender.setText(R.string.gender_male);
//                default:
//            }


            // 性别判断
            if (Objects.equals(user.getGender(), "0")){
                binding.tvValueGender.setText(R.string.unknown_gender);
            } else if (Objects.equals(user.getGender(), "1")){
                binding.tvValueGender.setText(R.string.gender_male);
            }else if(Objects.equals(user.getGender(), "2")){
                binding.tvValueGender.setText(R.string.gender_female);
            }
        }


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_scrolling, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setBg(){
        RequestOptions options1 = RequestOptions.bitmapTransform(new BlurTransformation(50,1));
        Glide.with(this).load(R.drawable.wall).apply(options1).into(binding.imgWall);
    }

    public void showNoFunction(){
        Toast.makeText(requireContext(),"功能正在开发,即将上线...", Toast.LENGTH_SHORT).show();
    }

}