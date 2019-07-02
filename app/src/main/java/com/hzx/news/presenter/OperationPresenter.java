package com.hzx.news.presenter;

import com.hzx.news.model.entity.NewsCommentResponse;
import com.hzx.news.model.entity.OptStatus;
import com.hzx.news.model.entity.UpdateStatus;
import com.hzx.news.presenter.View.NewsDetailView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import java.util.List;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 18:38
 * @param:
 * @return:
 * @throws:
 */
public class OperationPresenter extends BasePresenter<NewsDetailView> {

    public OperationPresenter(NewsDetailView view) {
        super(view);
    }

    public void click(String nid) {
        addSubscription(apiService.postClick(nid), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                KLog.i("上传成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                KLog.i("上传失败");
            }

            @Override
            public void onNext(String o) {
                KLog.i("上传");
            }
        });
    }

    public void collect(String nid) {
        addSubscription(apiService.postCollect(nid), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("收藏成功");
            }

            @Override
            public void onError(Throwable e) {
                view.onCollectError();
            }

            @Override
            public void onNext(String s) {
                KLog.i(s);
                view.onCollectSuccess();
            }
        });
    }

    public void like(String nid) {
        addSubscription(apiService.postLike(nid), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("点赞成功");
            }

            @Override
            public void onError(Throwable e) {
                view.onLikeError();
            }

            @Override
            public void onNext(String s) {
                KLog.i(s);
                view.onLikeSuccess();
            }
        });
    }

    public void cancleCollect(String nid) {
        addSubscription(apiService.postCancleCollect(nid), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("取消收藏成功");
            }

            @Override
            public void onError(Throwable e) {
                view.onCancleCollectError();
            }

            @Override
            public void onNext(String s) {
                KLog.i(s);
                view.onCancleCollectSuccess();
            }
        });
    }

    public void cancleLike(String nid) {
        addSubscription(apiService.postCancleLike(nid), new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("点赞成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onCancleLikeError();
            }

            @Override
            public void onNext(String s) {
                KLog.i(s);
                view.onCancleLikeSuccess();
            }
        });
    }

    public void getOptStatus(String nid) {
        addSubscription(apiService.getOptStatus(nid), new Subscriber<OptStatus>() {
            @Override
            public void onCompleted() {
                System.out.println("点赞成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onGetStatusError();
            }

            @Override
            public void onNext(OptStatus s) {
                KLog.i(s);
                view.onGetStatusSuccess(s);
            }
        });
    }

    public void comment(String nid, long uid, String comment, String
            actionTime) {
        addSubscription(apiService.postCommentNews(nid, uid, comment, actionTime), new Subscriber<UpdateStatus>() {
            @Override
            public void onCompleted() {
                KLog.i("评论完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onCommentError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                KLog.i(updateStatus.getInfo());
                view.onCommentSuccess(updateStatus);
            }
        });
    }

    public void getNewsComment(String nid) {
        addSubscription(apiService.getCommentList(nid), new Subscriber<List<NewsCommentResponse>>() {
            @Override
            public void onCompleted() {
                KLog.i("请求评论完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onGetCommentError();
            }

            @Override
            public void onNext(List<NewsCommentResponse> list) {
                view.onGetCommentSuccess(list);
            }
        });

    }
}
