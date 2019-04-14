package com.hzx.news.presenter;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import java.util.List;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 14:37
 * @param:
 * @return:
 * @throws:
 */
public class HistoryPresenter extends BasePresenter<NewsListView> {


    public HistoryPresenter(NewsListView view) {
        super(view);
    }

    public void getHistory() {
        addSubscription(apiService.getHistory(), new Subscriber<List<News>>() {
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
            public void onNext(List<News> response) {
                KLog.e(response);
                view.onSuccess(response);
            }
        });
    }
}
