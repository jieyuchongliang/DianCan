package com.fujisoft.diancan.main_frament;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.fujisoft.diancan.R;
import com.fujisoft.diancan.adapter.HomeFragmentRecyclerViewAdapter;
import com.fujisoft.diancan.adapter.LocalImageHolderView;
import com.fujisoft.diancan.api.ApiService;
import com.fujisoft.diancan.api.RetrofitManager;
import com.fujisoft.diancan.bean.CampaignHomeBean;
import com.fujisoft.diancan.databinding.FragmentHomePageBinding;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunfusheng.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 *
 * Created by 860617010 on 2017/8/23.
 */

public class HomeFrament extends Fragment implements HomeFragmentRecyclerViewAdapter.OnItemClickListener, OnItemClickListener {
    private RecyclerView recyclerView;
    private FragmentHomePageBinding binding;
    private HomeFragmentRecyclerViewAdapter adapter;
    private List<String> notice;
    private MarqueeView marqueeView;//公告控件
    private ConvenientBanner convenientBanner;//轮播控件
    private SmartRefreshLayout refreshLayout;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false);
        loadHomeData();
        initRecyclerView();
        initView();
        return binding.getRoot();
    }

    /**
     * 加载数据
     */
    private void loadHomeData() {
        RetrofitManager.toSubscribe(RetrofitManager.getApiService().getCampaignHomeBean("1"), new Subscriber<CampaignHomeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CampaignHomeBean campaignHomeBean) {
                if (campaignHomeBean.isSuccess()) {
                    //获取数据成功，给xml中的各个控件填充数据
                    fillingData(campaignHomeBean);
                    refreshLayout.finishRefresh();//关闭刷新控件
                } else {
                    //获取数据失败
                }
            }
        });
    }

    /**
     * 如果数据获取成功，走此方法，填充数据
     *
     * @param campaignHomeBean
     */
    private void fillingData(final CampaignHomeBean campaignHomeBean) {
        //绑定数据到xml
        binding.setBean(campaignHomeBean);
        //绑定公告数据
        notice = new ArrayList<>();
        for (CampaignHomeBean.DataBean.NoticeListBean noticeBean :
                campaignHomeBean.getData().getNoticeList()) {
            notice.add(noticeBean.getTitle());
        }
        marqueeView.startWithList(notice);
        //绑定RecyclerView数据
        adapter.setBean(campaignHomeBean.getData().getGoodsList());//给adapter设置数据
        adapter.notifyDataSetChanged();//刷新数据
        //绑定轮播图数据
        List<String> picPath = new ArrayList<>();
        for (CampaignHomeBean.DataBean.CarouselListBean carouselListBean :
                campaignHomeBean.getData().getCarouselList()) {
            picPath.add(ApiService.PICTURE_BASE_URL + carouselListBean.getUrl());
        }
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, picPath)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)//设置指示器的方位
                .startTurning(2000)     //设置自动切换（同时设置了切换时间间隔）
                .setManualPageable(true);  //设置手动影响（设置了该项无法手动切换）;
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.setOnItemClickListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        recyclerView = (RecyclerView) binding.getRoot().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setNestedScrollingEnabled(false);//解决嵌套布局滑动冲突（去掉recyclerview的滑动事件）
        recyclerView.setFocusable(false);//解决ScrollView的页面布局默认起始位置不是最顶部的情况
        adapter = new HomeFragmentRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListenr(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        nestedScrollView = (NestedScrollView) binding.getRoot().findViewById(R.id.nested_scroll_view);
        marqueeView = (MarqueeView) binding.getRoot().findViewById(R.id.marqueeView);
        convenientBanner = (ConvenientBanner) binding.getRoot().findViewById(R.id.convenientBanner);
        refreshLayout = (SmartRefreshLayout) binding.getRoot().findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Toast.makeText(getContext(), "重新加载数据", Toast.LENGTH_SHORT).show();
                loadHomeData();
            }
        });
    }


    private static final String TAG = "FlowerMallFrament";

    /**
     * 给RecyclerView设置条目点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        // TODO: 2017/8/23 RecyclerView条目点击事件
        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * 轮播图图片被点击触发的事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        // TODO: 2017/8/23 轮播图片点击事件
        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            Log.i(TAG, "onHiddenChanged: 隐藏");
        }else {
            Log.i(TAG, "onHiddenChanged: 显示");
        }
    }
}
