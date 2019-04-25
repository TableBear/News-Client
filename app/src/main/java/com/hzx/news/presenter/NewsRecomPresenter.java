package com.hzx.news.presenter;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.presenter.View.NewsRecomView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/24 21:37
 * @param:
 * @return:
 * @throws:
 */
public class NewsRecomPresenter extends BasePresenter<NewsRecomView> {

    public NewsRecomPresenter(NewsRecomView view) {
        super(view);
    }

    public void getRecommendNews(int offest, int limit) {
        addSubscription(apiService.getRecomNews(offest, limit), new Subscriber<NewsResponse>() {
            @Override
            public void onCompleted() {
                KLog.i("推荐完成");
            }

            @Override
            public void onError(Throwable e) {
                view.onRecommendError();
                e.printStackTrace();
            }

            @Override
            public void onNext(NewsResponse response) {
                view.onRecommendSuccess(response.getList());
            }
        });
    }
}
