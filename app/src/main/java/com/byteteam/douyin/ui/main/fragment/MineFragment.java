package com.byteteam.douyin.ui.main.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
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
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.Rotate;
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
import com.byteteam.douyin.util.AnimationUtil;
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

    private View.OnClickListener noFunctionListener, getUserListener, menuClickListener , disNoFunctionListener;





    @Override
    public void onStart() {
        super.onStart();
        doSubscribe();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMineBinding.inflate(getLayoutInflater(),container,false);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(requireActivity().getTitle());


        noFunctionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.doPressAnimation(view);
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
                        })
                        .subscribe(user -> {
                            System.out.println("user: " + user);
                            displayUser(user);
                        });


            }
        };

        menuClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationUtil.doPressAnimation(view);
                showMenu(binding.btnSettings);
            }
        };

        disNoFunctionListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoFunction();
            }
        };





        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(requireContext(), requireActivity().getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        ImageButton settingsButton = binding.btnSettings;
        settingsButton.setOnClickListener(menuClickListener);

        ImageButton btn_expand = binding.btnExpand;
        btn_expand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView tv = binding.tvInfo;
                singleLine = !singleLine;
                tv.setSingleLine(singleLine);
                if (singleLine){
                    Glide.with(getContext()).load(R.drawable.arrow).into(binding.btnExpand);
                }else{
                    Glide.with(getContext()).load(R.drawable.arrow).transform(new Rotate(180)).into(binding.btnExpand);
                }
            }
        });

        ImageButton btn_edit = binding.btnModify;
        btn_edit.setOnClickListener(noFunctionListener);

        // 默认设置背景和头像
        setDefaultBg();

        // 没有这个也可以正常使用,但是这个可以使加载更快,不然会看到默认头像
        initUser();

        binding.imgAvatar.setOnClickListener(getUserListener);

        bindNoFunction();

        return binding.getRoot();

    }

    // 从数据库加载user
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
        binding.cvEditProfile.setOnClickListener(noFunctionListener);
        binding.cvNewFriends.setOnClickListener(noFunctionListener);
        binding.clStore.setOnClickListener(noFunctionListener);
        binding.clOpen.setOnClickListener(noFunctionListener);
        binding.llRates.setOnClickListener(disNoFunctionListener);
    }

    @SuppressLint("CheckResult")
    private void refreshUser(){
        UserDataSource userDataSource = RepositoryFactory.provideUserDataRepository(getContext());
        userDataSource.queryUser()
                .doOnComplete(()->{
                    ApiUtil.sendAuth(getActivity());
                })
                .subscribe(user -> {
                    System.out.println("user: " + user);
                    displayUser(user);
                });
    };

    @SuppressLint("CheckResult")
    private void doSubscribe(){
        UserDataSource userDataSource = RepositoryFactory.provideUserDataRepository(getContext());
        userDataSource.queryUser()
                .subscribe(user -> {
                    System.out.println("user: " + user);
                    displayUser(user);
                });
    }

    @SuppressLint("CheckResult")
    private void doOnComplete(){
        UserDataSource userDataSource = RepositoryFactory.provideUserDataRepository(getContext());
        userDataSource.queryUser()
                .doOnComplete(()->{
                    ApiUtil.sendAuth(getActivity());
                });

    };

    // 展示menu，点击了右上角图标后
    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(getContext(),view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_settings_mine,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.settings:
                        showNoFunction();
                    case R.id.logout:
                        // todo
                    case R.id.change_bg:
                        // todo

                }

                return true;
            }
        });

        popupMenu.show();

    }


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

            binding.tvValueGender.setText(user.getGender());

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

    public void setDefaultBg(){
        RequestOptions options = RequestOptions.bitmapTransform(new CropCircleWithBorderTransformation(3, Color.WHITE));
        Glide.with(this).load(R.drawable.r_key).apply(options).into(binding.imgAvatar);
        RequestOptions options1 = RequestOptions.bitmapTransform(new BlurTransformation(50,1));
        Glide.with(this).load(R.drawable.wall).apply(options1).into(binding.imgWall);
    }

    public void showNoFunction(){
        Toast.makeText(requireContext(),"功能正在开发,即将上线...", Toast.LENGTH_SHORT).show();
    }

}