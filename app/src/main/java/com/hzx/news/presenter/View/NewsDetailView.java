package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.NewsCommentResponse;
import com.hzx.news.model.entity.OptStatus;
import com.hzx.news.model.entity.UpdateStatus;

import java.util.List;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 18:39
 * @param:
 * @return:
 * @throws:
 */
public interface NewsDetailView {
    void onCollectSuccess();

    void onCollectError();

    void onLikeSuccess();

    void onLikeError();

    void onCancleCollectSuccess();

    void onCancleCollectError();

    void onCancleLikeSuccess();

    void onCancleLikeError();

    void onGetStatusSuccess(OptStatus status);

    void onGetStatusError();

    void onCommentSuccess(UpdateStatus updateStatus);

    void onCommentError();

    void onGetCommentSuccess(List<NewsCommentResponse> list);

    void onGetCommentError();

}
