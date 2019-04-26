package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.News;

import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/25 17:01
 * @param:
 * @return:
 * @throws:
 */
public interface PSearchView {
    void onSearchSuccess(List<News> list);

    void onSearchError();
}
