package com.hzx.news.presenter;

import com.hzx.news.presenter.View.NewsListView;
import com.hzx.news.ui.base.BasePresenter;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 17:11
 * @param:
 * @return:
 * @throws:
 */
public abstract class RecordPresenter extends BasePresenter<NewsListView> {

    public RecordPresenter(NewsListView view) {
        super(view);
    }

    public abstract void getData();

}
