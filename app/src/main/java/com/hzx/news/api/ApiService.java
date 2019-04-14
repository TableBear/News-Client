package com.hzx.news.api;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.News;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiService {
    String GET_NEWS_LIST = "api/news";
    String GET_CATES_LIST = "api/cates";
    String POST_OPT_COLLECT = "opt/click";
    String GET_REC_COLLECT = "record/collect";
    String GET_REC_HISTORY = "record/history";
    String GET_REC_LIKE = "record/like";
    String POST_REGISTER_EMAIL = "user/register/email";


    /**
     * 获取新闻列表
     *
     * @param cate
     * @return 新闻列表
     */
    @GET(GET_NEWS_LIST)
    Observable<NewsResponse> getNewsList(@Query("cate") String cate, @Query("time") String time, @Query("limit") int limit);

    @FormUrlEncoded
    @POST(POST_OPT_COLLECT)
    Observable<String> postCollect(@Field("nid") String nid);

    @GET(GET_REC_HISTORY)
    Observable<List<News>> getHistory();

    @GET(GET_REC_COLLECT)
    Observable<List<News>> getCollect();

    @GET(GET_REC_LIKE)
    Observable<List<News>> getLike();
}
