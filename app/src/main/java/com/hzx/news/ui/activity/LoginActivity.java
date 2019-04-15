package com.hzx.news.ui.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.hzx.news.R;
import com.hzx.news.app.NewsApp;
import com.hzx.news.model.entity.LoginRegisterStatus;
import com.hzx.news.model.entity.Token;
import com.hzx.news.presenter.LoginRegisterPresenter;
import com.hzx.news.presenter.View.LoginRegisterView;
import com.hzx.news.ui.base.BaseActivity;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginRegisterPresenter> implements LoginRegisterView {

    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected LoginRegisterPresenter createPresenter() {
        return new LoginRegisterPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.bt_login)
    public void loginClick() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        presenter.login(email, password);
    }

    @OnClick(R.id.tv_go_register)
    public void goToClick() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.iv_back)
    public void backClick() {
        finish();
    }

    @Override
    public void onLoginAndRegisterSuccess(LoginRegisterStatus status) {
        if (status.getCode().equals("200")) {
            NewsApp.token = new Token();
            NewsApp.token.setToken(status.getToken());
            NewsApp.token.setNick(status.getNick());
            KLog.i(NewsApp.token.getToken());
            NewsApp.token.saveAsync().listen(success -> {
                if (success) {
                    KLog.i("保存成功");
                    finish();
                } else {
                    KLog.i("保存失败");
                    Toast.makeText(getCurrentActivity(), "出现未知异常，请尝试重新登陆", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, status.getInfo(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginAndRegisterError() {
        Toast.makeText(this, "发生未知错误请稍后重试！", Toast.LENGTH_SHORT).show();
    }

}
