package com.byteteam.douyin.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.byteteam.douyin.databinding.FragmentBlankBinding;
import com.byteteam.douyin.logic.database.model.Works;
import com.byteteam.douyin.logic.database.model.WorksStatistics;
import com.byteteam.douyin.logic.factory.NetWorkFactory;
import com.byteteam.douyin.logic.network.model.WorksResponse;
import com.byteteam.douyin.logic.network.response.DouYinResponse;
import com.byteteam.douyin.logic.network.service.WorksService;
import com.byteteam.douyin.ui.main.adapter.BlankAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @introduction：
 * @author： 林锦焜
 * @time： 2022/8/20 11:20
 */
public class BlankFragment extends Fragment {

    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    FragmentBlankBinding binding;

    BlankAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter == null) {
            List<Works> worksList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                worksList.add(getWorks(i));
            }
            adapter = new BlankAdapter(worksList);
            binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
            binding.recyclerView.setAdapter(adapter);
        }
    }

    private Works getWorks(int playCount) {
        Works works = new Works();
        WorksStatistics worksStatistics = new WorksStatistics();
        worksStatistics.setPlayCount(playCount);
        works.setStatistics(worksStatistics);
        return works;
    }

}
