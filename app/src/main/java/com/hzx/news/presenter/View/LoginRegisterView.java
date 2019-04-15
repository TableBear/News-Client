package com.hzx.news.presenter.View;

import com.hzx.news.model.entity.LoginRegisterStatus;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 23:16
 * @param:
 * @return:
 * @throws:
 */
public interface LoginRegisterView {
    void onLoginAndRegisterSuccess(LoginRegisterStatus status);

    void onLoginAndRegisterError();



}
