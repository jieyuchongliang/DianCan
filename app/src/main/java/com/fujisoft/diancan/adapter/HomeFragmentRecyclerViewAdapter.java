package com.fujisoft.diancan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fujisoft.diancan.R;
import com.fujisoft.diancan.api.ApiService;
import com.fujisoft.diancan.bean.CampaignHomeBean;

import java.util.List;

/**
 * Created by 860617010 on 2017/8/23.
 */

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HomeFragmentRecyclerViewAdapter.HomeFragmentViewHolder> {
    private Context context;
    private List<CampaignHomeBean.DataBean.GoodsListBean> bean;

    public HomeFragmentRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(List<CampaignHomeBean.DataBean.GoodsListBean> bean) {
        this.bean = bean;
    }

    @Override
    public HomeFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_home_rv, parent, false);
        HomeFragmentViewHolder holder = new HomeFragmentViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, (int) view.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeFragmentViewHolder holder, int position) {
        if (bean != null) {
            holder.tvDescribe.setText(bean.get(position).getGoodsName());
            holder.tvSurplus.setText("剩余:" + bean.get(position).getGoodsQuantity());
            holder.tvNumber.setText(bean.get(position).getScorePrice());
            Glide.with(context)
                    .load(ApiService.PICTURE_BASE_URL +bean.get(position).getGoodsPicture())
                    .placeholder(R.drawable.ic_home_page_select)
                    .error(R.drawable.broadcask)
                    .into(holder.ivShow);
            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if (bean != null) {
            return bean.size();
        }
        return 0;
    }

    class HomeFragmentViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivShow;
        private TextView tvNumber, tvSurplus, tvDescribe;

        public HomeFragmentViewHolder(View itemView) {
            super(itemView);
            ivShow = (ImageView) itemView.findViewById(R.id.iv_show);
            tvNumber = (TextView) itemView.findViewById(R.id.tv_number);
            tvSurplus = (TextView) itemView.findViewById(R.id.tv_surplus);
            tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListenr(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
