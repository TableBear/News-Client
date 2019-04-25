package com.hzx.news.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzx.news.R;
import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.NewsListPresenter;
import com.hzx.news.presenter.NewsRecomPresenter;
import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.presenter.View.NewsRecomView;
import com.hzx.news.ui.activity.NewsDetailActivity;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.base.LazyLoadFragment;
import com.hzx.news.ui.uikit.GlideUtils;
import com.hzx.news.utils.DateUtils;
import com.hzx.news.utils.ListUtils;
import com.hzx.news.utils.TipView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.socks.library.KLog;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/8 0:52
 * @param:
 * @return:
 * @throws:
 */
public class RecomFragment extends BaseFragment<NewsRecomPresenter> implements NewsRecomView {

    @BindView(R.id.tip_view)
    TipView tipView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.frame_layout)
    FrameLayout fameLayout;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private NewsAdapter newsAdapter;
    private List<News> datas;
    private SimpleDateFormat sdf;
    private int offest = 0;
    private int limit = 20;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recom;
    }

    @Override
    protected NewsRecomPresenter createPresenter() {
        return new NewsRecomPresenter(this);
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void initListener() {
        super.initListener();
        newsAdapter = new NewsAdapter(datas);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void click(int position, View view) {
                System.out.println("点击了");
                Intent intent = new Intent(activity, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, newsAdapter.list.get(position).getArticleUrl());
                intent.putExtra(NewsDetailActivity.NID, newsAdapter.list.get(position).getNid());
                startActivity(intent);
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadDate();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                smartRefreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected void loadDate() {
        stateView.showLoading();
        presenter.getRecommendNews(offest, limit);
    }

    @Override
    public View getStateRootView() {
        return fameLayout;
    }

    @Override
    public void onRecommendSuccess(List<News> list) {
        smartRefreshLayout.finishRefresh();
        KLog.i(list.size());
//        更新offest的值
        offest += list.size();
        if (ListUtils.isEmpty(list)) {
            tipView.show("暂时没有新推荐");
            if (ListUtils.isEmpty(datas)) {
                stateView.showEmpty();
            } else {
                stateView.showContent();
            }
        } else {
            datas.addAll(0, list);
            newsAdapter.notifyDataSetChanged();
            tipView.show("推荐系统有" + list.size() + "条推荐");
            stateView.showContent();
        }
    }

    @Override
    public void onRecommendError() {
        stateView.showRetry();
    }

    private class NewsHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvAuthor;
        public TextView tvTime;
        public TextView tvCommentNum;
        public ImageView ivImg;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvCommentNum = itemView.findViewById(R.id.tv_comment_num);
            ivImg = itemView.findViewById(R.id.iv_img);
        }
    }

    private class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

        private List<News> list;
        private ItemClickListener itemClickListener;

        public NewsAdapter(List<News> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.item_pic_news, viewGroup, false);
            return new NewsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            String jsonString = gson.toJson(list.get(i));
            KLog.i(list.get(i));
            News news = gson.fromJson(jsonString, News.class);
            newsHolder.tvTitle.setText(news.getTitle());
            newsHolder.tvAuthor.setText(news.getAuthor());
            newsHolder.tvTime.setText(sdf.format(news.getPublishTime()));
            newsHolder.tvCommentNum.setText("0评论");
            GlideUtils.load(getActivity(), news.getCoverImageUrl(), newsHolder.ivImg);
            newsHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.click(i, v);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

    }

    private interface ItemClickListener {
        void click(int position, View view);
    }
}
