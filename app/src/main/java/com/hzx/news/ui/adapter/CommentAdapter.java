package com.hzx.news.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzx.news.R;
import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.NewsCommentResponse;
import com.hzx.news.ui.activity.SearchActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/5/9 3:04
 * @param:
 * @return:
 * @throws:
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    List<NewsCommentResponse> comments;

    Context context;

    private SimpleDateFormat sdf;

    public CommentAdapter() {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public CommentAdapter(Context context) {
        this.context = context;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public CommentAdapter(Context context, List<NewsCommentResponse> comments) {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.comments = comments;
        this.context = context;
    }



    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_comment, viewGroup, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        System.out.println(commentViewHolder.tvName);
        commentViewHolder.tvName.setText(comments.get(i).getUnick());
        commentViewHolder.tvLikeCount.setText("0");
        commentViewHolder.tvContent.setText(comments.get(i).getComment());
        commentViewHolder.tvTime.setText(sdf.format(comments.get(i).getActionTime()));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public List<NewsCommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<NewsCommentResponse> comments) {
        this.comments = comments;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

