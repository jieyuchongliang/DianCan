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
import com.fujisoft.diancan.bean.TaskHallBean;

/**
 * Created by 860617010 on 2017/8/25.
 */

public class TaskHallRecyclerViewAdapter extends RecyclerView.Adapter<TaskHallRecyclerViewAdapter.TaskHallViewHolder> {

    private Context context;
    private TaskHallBean bean;

    /**
     * 外界加载数据成功后会调用此方法赋值
     * @param bean
     */
    public void setBean(TaskHallBean bean) {
        this.bean = bean;
    }

    public TaskHallRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public TaskHallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task_hall_recyclerview,parent,false);
        TaskHallViewHolder holder = new TaskHallViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TaskHallViewHolder holder, int position) {
        if (bean != null) {
            holder.tvMain.setText(bean.getData().getOptionalTask().get(position).getName());
            holder.tvSecondary.setText(bean.getData().getOptionalTask().get(position).getSubtitle());
            Glide.with(context)
                    .load(ApiService.PICTURE_BASE_URL +bean.getData().getOptionalTask().get(position).getPicUrl())
                    .placeholder(R.drawable.ic_home_page_select)
                    .error(R.drawable.broadcask)
                    .into(holder.iv);
        }
    }

    @Override
    public int getItemCount() {
        if (bean != null) {
            return bean.getData().getOptionalTask().size();
        }
        return 0;
    }

    class TaskHallViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMain,tvSecondary;
        private ImageView iv;
        public TaskHallViewHolder(View itemView) {
            super(itemView);
            tvMain = (TextView) itemView.findViewById(R.id.tv_task_hall_main);
            tvSecondary = (TextView) itemView.findViewById(R.id.tv_task_hall_secondary);
            iv = (ImageView) itemView.findViewById(R.id.iv_task_hall);
        }
    }
}
