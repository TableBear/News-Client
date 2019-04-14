package com.hzx.news.presenter;

import com.hzx.news.model.entity.News;
import com.hzx.news.presenter.View.NewsListView;
import com.socks.library.KLog;

import java.util.List;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 17:16
 * @param:
 * @return:
 * @throws:
 */
public class LikePresenter extends RecordPresenter {

    public LikePresenter(NewsListView view) {
        super(view);
    }

    @Override
    public void getData() {
        addSubscription(apiService.getLike(), new Subscriber<List<News>>() {
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
