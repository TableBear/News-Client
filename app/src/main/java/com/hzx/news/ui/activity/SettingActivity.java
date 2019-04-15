package com.hzx.news.ui.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.hzx.news.R;
import com.hzx.news.app.NewsApp;
import com.hzx.news.model.entity.Token;
import com.hzx.news.model.entity.User;
import com.hzx.news.presenter.SettingPresenter;
import com.hzx.news.presenter.View.SettingView;
import com.hzx.news.ui.base.BaseActivity;
import com.hzx.news.ui.base.BasePresenter;

import org.litepal.LitePal;
import org.litepal.crud.callback.UpdateOrDeleteCallback;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingView {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_qq)
    TextView tvQQ;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getUserInfo();
    }

    @OnClick(R.id.iv_back)
    public void backClcik() {
        finish();
    }

    @OnClick(R.id.bt_logout)
    public void logout() {
        NewsApp.token = null;
        LitePal.deleteAllAsync(Token.class).listen(new UpdateOrDeleteCallback() {
            @Override
            public void onFinish(int rowsAffected) {
                if (rowsAffected > 0) {
                    Toast.makeText(getCurrentActivity(), "推出登录成功", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });
    }

    @Override
    public void onSuccess(User user) {
        if (user.getUnick() != null) {
            tvName.setText(user.getUnick());
        }
        if (user.getEmail() != null) {
            tvEmail.setText(user.getEmail());
        }
        if (user.getPhone() != null) {
            tvPhone.setText(user.getPhone());
        }
        if (user.getQq() != null) {
            tvQQ.setText(user.getQq());
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "出现未知异常，稍后重试", Toast.LENGTH_SHORT);
    }
}
