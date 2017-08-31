package com.fujisoft.diancan.main_frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fujisoft.diancan.R;
import com.fujisoft.diancan.adapter.TaskHallRecyclerViewAdapter;
import com.fujisoft.diancan.api.RetrofitManager;
import com.fujisoft.diancan.bean.TaskHallBean;
import com.fujisoft.diancan.databinding.FragmentTaskHallBinding;

import rx.Subscriber;

/**
 * Created by 860617010 on 2017/8/23.
 */

public class TaskHallFrament extends Fragment {

    private FragmentTaskHallBinding binding;
    private RecyclerView recyclerView;
    private TaskHallRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_hall, container, false);
        initView();
        loadData();
        return binding.getRoot();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.rl_task_hall);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskHallRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

    /**
     * 网络加载数据
     */
    private void loadData() {
        RetrofitManager.toSubscribe(RetrofitManager.getApiService().getTaskHallData("", 1, null), new Subscriber<TaskHallBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TaskHallBean taskHallBean) {
                if (taskHallBean.isSuccess()){
                    adapter.setBean(taskHallBean);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(),"加载数据失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
