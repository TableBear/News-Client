package com.hzx.news.presenter;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.presenter.View.PSearchView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/25 17:00
 * @param:
 * @return:
 * @throws:
 */
public class SearchPresenter extends BasePresenter<PSearchView> {
    public SearchPresenter(PSearchView view) {
        super(view);
    }

    public void search(String key) {
        addSubscription(apiService.getSearchNews(key), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {
                KLog.i("搜索完成");
            }

            @Override
            public void onError(Throwable e) {
                view.onSearchError();
                e.printStackTrace();
            }

            @Override
            public void onNext(NewsResponse response) {

                view.onSearchSuccess(response.getList());
            }
        });
    }
}
