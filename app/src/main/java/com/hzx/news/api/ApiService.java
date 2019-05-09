package com.hzx.news.api;

import com.hzx.news.model.NewsResponse;
import com.hzx.news.model.entity.LoginRegisterStatus;
import com.hzx.news.model.entity.News;
import com.hzx.news.model.entity.NewsComment;
import com.hzx.news.model.entity.NewsCommentResponse;
import com.hzx.news.model.entity.OptStatus;
import com.hzx.news.model.entity.UpdateStatus;
import com.hzx.news.model.entity.User;

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
    String GET_ALL_NEWS = "api/all-offest";
    String POST_OPT_COLLECT = "opt/collect";
    String POST_OPT_LIKE = "opt/like";
    String POST_OPT_CLICK = "opt/click";
    String POST_OPT_CANCLECOLLECT = "opt/cancleCollect";
    String POST_OPT_CANCLELIKE = "opt/cancleLike";
    String GET_OPT_STATUS = "opt/status";
    String GET_REC_COLLECT = "record/collect";
    String GET_REC_HISTORY = "record/history";
    String GET_REC_LIKE = "record/like";
    String POST_REGISTER_EMAIL = "user/register/email";
    String POST_LOGIN_EMAIL = "user/login/email";
    String GET_USER_INFO = "user/info";
    String GET_UPDATE_NICK = "user/update/nick";
    String GET_UPDATE_PHONE = "user/update/phone";
    String GET_UPDATE_EMAIL = "user/update/email";
    String GET_UPDATE_QQ = "user/update/qq";
    String GET_UPDATE_PASSWORD = "user/update/password";
    String GET_RECOM_NEWS = "recom/news";
    String GET_SEARCH_NEWS = "search/key";
    String POST_COMMENT_NEWS = "comment/comment";
    String GET_COMMENT_LIST = "comment/get-comment";

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

    @GET(GET_ALL_NEWS)
    Observable<NewsResponse> getAllNews(@Query("offest") int offest, @Query("limit") int limit);

    @FormUrlEncoded
    @POST(POST_OPT_LIKE)
    Observable<String> postLike(@Field("nid") String nid);

    @FormUrlEncoded
    @POST(POST_OPT_CANCLECOLLECT)
    Observable<String> postCancleCollect(@Field("nid") String nid);

    @FormUrlEncoded
    @POST(POST_OPT_CANCLELIKE)
    Observable<String> postCancleLike(@Field("nid") String nid);

    @FormUrlEncoded
    @POST(POST_OPT_CLICK)
    Observable<String> postClick(@Field("nid") String nid);

    @GET(GET_REC_HISTORY)
    Observable<List<News>> getHistory();

    @GET(GET_REC_COLLECT)
    Observable<List<News>> getCollect();

    @GET(GET_REC_LIKE)
    Observable<List<News>> getLike();

    @GET(GET_OPT_STATUS)
    Observable<OptStatus> getOptStatus(@Query("nid") String nid);

    @FormUrlEncoded
    @POST(POST_LOGIN_EMAIL)
    Observable<LoginRegisterStatus> postLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST(POST_REGISTER_EMAIL)
    Observable<LoginRegisterStatus> postRegister(@Field("unick") String unick, @Field("email") String email, @Field("password") String password);

    @GET(GET_USER_INFO)
    Observable<User> getUserInfo();

    @GET(GET_UPDATE_NICK)
    Observable<UpdateStatus> getUpdateNick(@Query("nick") String nick);

    @GET(GET_UPDATE_EMAIL)
    Observable<UpdateStatus> getUpdateEmail(@Query("email") String email);

    @GET(GET_UPDATE_PHONE)
    Observable<UpdateStatus> getUpdatePhone(@Query("phone") String phone);

    @GET(GET_UPDATE_QQ)
    Observable<UpdateStatus> getUpdateQQ(@Query("qq") String qq);

    @GET(GET_UPDATE_PASSWORD)
    Observable<UpdateStatus> getUpdatePassword(@Query("password") String password);

    @GET(GET_RECOM_NEWS)
    Observable<NewsResponse> getRecomNews(@Query("offest") int offest, @Query("limit") int limit);

    @GET(GET_SEARCH_NEWS)
    Observable<NewsResponse> getSearchNews(@Query("key") String key);

    @FormUrlEncoded
    @POST
    Observable<UpdateStatus> postCommentNews(NewsComment newsComment);

    @GET(GET_COMMENT_LIST)
    Observable<NewsCommentResponse> getCommentList(String nid);
}
