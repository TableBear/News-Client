package com.hzx.news.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.nukc.stateview.StateView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzx.news.R;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.CollectPresenter;
import com.hzx.news.presenter.HistoryPresenter;
import com.hzx.news.presenter.LikePresenter;
import com.hzx.news.presenter.RecordPresenter;
import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.uikit.GlideUtils;
import com.hzx.news.utils.ListUtils;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecordActivity extends BaseActivity<RecordPresenter> implements NewsListView {


    @BindView(R.id.rv_content)
    RecyclerView recyclerView;

    private NewsAdapter newsAdapter;
    private List<News> datas;
    private SimpleDateFormat sdf;

    private int flag;

    @Override
    public void beforCreatePresenter(Intent intent) {
        super.beforCreatePresenter(intent);
        flag = intent.getIntExtra("flag", 0);
    }

    @Override
    protected RecordPresenter createPresenter() {
        switch (flag) {
            case 1:
                return new HistoryPresenter(this);
            case 2:
                return new CollectPresenter(this);
            default:
                return new LikePresenter(this);
        }

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_record;
    }


    @Override
    public void initView() {
        stateView = StateView.inject(this);
        if (stateView != null) {
            stateView.setLoadingResource(R.layout.page_loading);
            stateView.setRetryResource(R.layout.page_net_error);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {
        super.initData();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        stateView.showLoading();
        presenter.getData();
    }

    @Override
    public void initListener() {
        super.initListener();
        datas = new ArrayList<>();
        newsAdapter = new NewsAdapter(datas);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void click(int position, View view) {
                System.out.println("点击了");
                Intent intent = new Intent(getCurrentActivity(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, newsAdapter.list.get(position).getArticleUrl());
                startActivity(intent);
            }
        });
    }


    @Override
    public void onSuccess(List<News> newsList) {
        if (ListUtils.isEmpty(newsList)) {
            if (ListUtils.isEmpty(datas)) {
                stateView.showEmpty();
            } else {
                stateView.showContent();
            }
        } else {
            datas.addAll(0, newsList);
            newsAdapter.notifyDataSetChanged();
            stateView.showContent();
        }
    }

    @Override
    public void onError() {
        if (ListUtils.isEmpty(datas)) {
            //如果一开始进入没有数据
            stateView.showRetry();//显示重试的布局
        }
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
            LayoutInflater layoutInflater = LayoutInflater.from(getCurrentActivity());
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
            GlideUtils.load(getCurrentActivity(), news.getCoverImageUrl(), newsHolder.ivImg);
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
