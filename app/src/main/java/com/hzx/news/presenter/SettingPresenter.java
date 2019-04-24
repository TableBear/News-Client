package com.hzx.news.presenter;

import com.hzx.news.model.entity.UpdateStatus;
import com.hzx.news.model.entity.User;
import com.hzx.news.presenter.View.SettingView;
import com.hzx.news.ui.base.BasePresenter;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * @Description:
 * @Author: TableBear
 * @Date: 2019/4/15 19:53
 * @param:
 * @return:
 * @throws:
 */
public class SettingPresenter extends BasePresenter<SettingView> {


    public SettingPresenter(SettingView view) {
        super(view);
    }

    public void getUserInfo() {
        addSubscription(apiService.getUserInfo(), new Subscriber<User>() {
            @Override
            public void onCompleted() {
                KLog.i("个人信息加载完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(User user) {
                view.onSuccess(user);
            }
        });
    }

    public void updateNick(String ncik) {
        addSubscription(apiService.getUpdateNick(ncik), new Subscriber<UpdateStatus>() {

            @Override
            public void onCompleted() {
                KLog.i("个人信息修改完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                view.onUpdateSucess(updateStatus);
            }
        });
    }

    public void updateEmail(String email) {
        addSubscription(apiService.getUpdateEmail(email), new Subscriber<UpdateStatus>() {

            @Override
            public void onCompleted() {
                KLog.i("个人信息修改完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                view.onUpdateSucess(updateStatus);
            }
        });
    }

    public void updateQQ(String qq) {
        addSubscription(apiService.getUpdateQQ(qq), new Subscriber<UpdateStatus>() {

            @Override
            public void onCompleted() {
                KLog.i("个人信息修改完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                view.onUpdateSucess(updateStatus);
            }
        });
    }

    public void updatePhone(String phone) {
        addSubscription(apiService.getUpdatePhone(phone), new Subscriber<UpdateStatus>() {

            @Override
            public void onCompleted() {
                KLog.i("个人信息修改完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                view.onUpdateSucess(updateStatus);
            }
        });
    }

    public void updatePassword(String password) {
        addSubscription(apiService.getUpdatePassword(password), new Subscriber<UpdateStatus>() {

            @Override
            public void onCompleted() {
                KLog.i("个人信息修改完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.onError();
            }

            @Override
            public void onNext(UpdateStatus updateStatus) {
                view.onUpdateSucess(updateStatus);
            }
        });
    }

}
