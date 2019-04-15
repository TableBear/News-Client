package com.hzx.news.presenter;

import com.hzx.news.app.NewsApp;
import com.hzx.news.model.entity.LoginRegisterStatus;
import com.hzx.news.model.entity.Token;
import com.hzx.news.presenter.View.LoginRegisterView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import org.litepal.LitePal;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/14 23:16
 * @param:
 * @return:
 * @throws:
 */
public class LoginRegisterPresenter extends BasePresenter<LoginRegisterView> {

    public LoginRegisterPresenter(LoginRegisterView view) {
        super(view);
    }

    public void login(String email, String password) {
        addSubscription(apiService.postLogin(email, password), new Subscriber<LoginRegisterStatus>() {
            @Override
            public void onCompleted() {
                KLog.i("登录成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoginAndRegisterError();
            }

            @Override
            public void onNext(LoginRegisterStatus status) {
                view.onLoginAndRegisterSuccess(status);
                LitePal.deleteAllAsync(Token.class);
            }
        });
    }

    public void register(String email, String unick, String password) {
        addSubscription(apiService.postRegister(unick, email, password), new Subscriber<LoginRegisterStatus>() {
            @Override
            public void onCompleted() {
                KLog.i("注册成功");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onLoginAndRegisterError();
            }

            @Override
            public void onNext(LoginRegisterStatus status) {
                view.onLoginAndRegisterSuccess(status);
                LitePal.deleteAllAsync(Token.class);
            }
        });
    }
}
