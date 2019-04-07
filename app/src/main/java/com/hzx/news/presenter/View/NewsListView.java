package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.News;

import java.util.List;

/**
 * @Description:
 * @Author:
 * @Date: 2019/4/2 22:40
 * @param:
 * @return:
 * @throws:
 */
public interface NewsListView {
    void onSuccess(List<News> newsList);

    void onError();
}
