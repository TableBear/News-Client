package com.hzx.news.api;

import com.hzx.news.model.NewsResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {
    String GET_NEWS_LIST = "news";
    String GET_CATES_LIST = "cates";

    /**
     * 获取新闻列表
     *
     * @param cate
     * @return 新闻列表
     */
    @GET(GET_NEWS_LIST)
    Observable<NewsResponse> getNewsList(@Query("cate") String cate, @Query("time") String time, @Query("limit") int limit);

}
