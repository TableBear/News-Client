package com.hzx.news.presenter.View;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.News;

import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/24 21:38
 * @param:
 * @return:
 * @throws:
 */
public interface NewsRecomView {
    void onRecommendSuccess(List<News> list);

    void onRecommendError();
}
