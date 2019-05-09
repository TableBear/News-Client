package com.hzx.news.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hzx.news.R;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/5/9 3:05
 * @param:
 * @return:
 * @throws:
 */
public class CommentViewHolder extends RecyclerView.ViewHolder {


    TextView tvName;
    TextView tvLikeCount;
    TextView tvContent;
    TextView tvTime;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvLikeCount = itemView.findViewById(R.id.tv_like_count);
        tvContent = itemView.findViewById(R.id.tv_content);
        tvTime = itemView.findViewById(R.id.tv_time);
    }
}
