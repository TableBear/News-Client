package com.hzx.news.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.nukc.stateview.StateView;
import com.hzx.news.R;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.SearchPresenter;
import com.hzx.news.presenter.View.PSearchView;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.uikit.GlideUtils;
import com.hzx.news.ui.uikit.SearchView;
import com.hzx.news.utils.ListUtils;
import com.socks.library.KLog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity<SearchPresenter> implements PSearchView, SearchView.SearchViewListener {

    @BindView(R.id.rv_content)
    RecyclerView recyclerView;
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.ll_layout)
    LinearLayout linearLayout;

    private NewsAdapter newsAdapter;
    private List<News> datas;
    private SimpleDateFormat sdf;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        stateView = StateView.inject(linearLayout);
        if (stateView != null) {
            stateView.setLoadingResource(R.layout.page_loading);
            stateView.setRetryResource(R.layout.page_net_error);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initListener() {
        super.initListener();
        datas = new ArrayList<>();
        newsAdapter = new NewsAdapter(datas);
        recyclerView.setAdapter(newsAdapter);
        searchView.setSearchViewListener(this);
        newsAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void click(int position, View view) {
                System.out.println("点击了");
                Intent intent = new Intent(getCurrentActivity(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URL, newsAdapter.list.get(position).getArticleUrl());
                intent.putExtra(NewsDetailActivity.NID, newsAdapter.list.get(position).getNid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }


    @Override
    public void onSearchSuccess(List<News> list) {
        if (ListUtils.isEmpty(list)) {
            stateView.showEmpty();
        } else {
            datas.clear();
            datas.addAll(0, list);
            newsAdapter.notifyDataSetChanged();
            stateView.showContent();
        }
    }

    @Override
    public void onSearchError() {
        stateView.showRetry();//显示重试的布局
    }

    @Override
    public void onRefreshAutoComplete(String text) {

    }

    @Override
    public void onSearch(String text) {
        stateView.showLoading();
        presenter.search(text);
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
            KLog.i(list.get(i));
            News news = list.get(i);
            newsHolder.tvTitle.setText(Html.fromHtml(news.getAbstractTitle()));
            newsHolder.tvAuthor.setText(Html.fromHtml(news.getAuthor()));
            newsHolder.tvTime.setText(sdf.format(news.getPublishTime()));
            newsHolder.tvCommentNum.setText("0评论");
            GlideUtils.load(getCurrentActivity(), news.getCoverImageUrl(), newsHolder.ivImg);
            newsHolder.itemView.setOnClickListener(v -> itemClickListener.click(i, v));
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
