package com.hzx.news.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzx.news.R;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.NewsListPresenter;
import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.ui.base.BaseFragment;
import com.hzx.news.ui.uikit.GlideUtils;
import com.hzx.news.utils.DateUtils;
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
 * @Date: 2019/4/8 0:45
 * @param:
 * @return:
 * @throws:
 */
public class NewsListFragment extends BaseFragment<NewsListPresenter> implements NewsListView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.frame_layout)
    FrameLayout fameLayout;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private String cate;
    private NewsAdapter newsAdapter;
    private List<News> datas;
    private SimpleDateFormat sdf;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        cate = bundle.getString("cate");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    protected NewsListPresenter createPresenter() {
        return new NewsListPresenter(this);
    }

    @Override
    public void initData() {
        super.initData();
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    protected void loadDate() {
        stateView.showLoading();
        String time;
        if (datas.size() != 0) {
            News temp = datas.get(datas.size() - 1);
            Timestamp publishTime = temp.getPublishTime();
            time = sdf.format(publishTime);
        } else {
            Date date = new Date();
            time = sdf.format(date);
        }
        KLog.i("time:" + time);
        time = DateUtils.date2TimeStamp(time, "yyyy-MM-dd HH:mm:ss");
        presenter.getNewsList(cate, time, 20);
        stateView.showContent();
    }

    public void initView(View view) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void initListener() {
        super.initListener();
        datas = new ArrayList<>();
        newsAdapter = new NewsAdapter(datas);
        recyclerView.setAdapter(newsAdapter);
        newsAdapter.setItemOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_LONG);
//                Intent intent = new Intent();
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
    public View getStateRootView() {
        return fameLayout;
    }

    public void updateUI(List list) {
        KLog.i("调用");

        NewsAdapter adapter = new NewsAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSuccess(List<News> newsList) {
        datas.addAll(0, newsList);
        newsAdapter.notifyDataSetChanged();
        stateView.showContent();
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public void onError() {

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
        private View.OnClickListener itemOnClickListener;

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
            GlideUtils.load(getContext(), news.getCoverImageUrl(), newsHolder.ivImg);
            if (itemOnClickListener != null)
                newsHolder.itemView.setOnClickListener(itemOnClickListener);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void setItemOnClickListener(View.OnClickListener itemOnClickListener) {
            this.itemOnClickListener = itemOnClickListener;
        }

    }
}
