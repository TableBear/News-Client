package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.UpdateStatus;
import com.hzx.news.model.entity.User;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/15 19:51
 * @param:
 * @return:
 * @throws:
 */
public interface SettingView {
    void onSuccess(User user);

    void onError();

    void onUpdateSucess(UpdateStatus updateStatus);

    void onUpdateError();
}
