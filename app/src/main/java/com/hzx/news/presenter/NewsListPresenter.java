package com.hzx.news.presenter;

import com.google.gson.Gson;
import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.ui.base.BasePresenter;
import com.hzx.news.utils.ListUtils;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/2 22:36
 * @param:
 * @return:
 * @throws:
 */
public class NewsListPresenter extends BasePresenter<NewsListView> {
    public NewsListPresenter(NewsListView view) {
        super(view);
    }

    public void getNewsList(String cate, String time, int limit) {
        addSubscription(apiService.getNewsList(cate, time, limit), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {
                KLog.i("加载成功");
            }

            @Override
            public void onError(Throwable e) {
                KLog.i(e.getLocalizedMessage());
                view.onError();
            }

            @Override
            public void onNext(NewsResponse response) {
                List<News> data = response.getList();
                KLog.e(data);
                view.onSuccess(data);
            }
        });
    }


}
