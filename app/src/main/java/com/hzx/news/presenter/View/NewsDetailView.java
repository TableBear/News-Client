package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.OptStatus;

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

}
