package com.hzx.news.ui.activity;

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

public class RegisterActivity extends BaseActivity<LoginRegisterPresenter> implements LoginRegisterView {

    @BindView(R.id.et_name)
    EditText etName;
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
        return R.layout.activity_register;
    }

    @Override
    public void onLoginAndRegisterSuccess(LoginRegisterStatus status) {
        if (status.getCode().equals("200")) {
            NewsApp.token = new Token();
            NewsApp.token.setToken(status.getToken());
            NewsApp.token.setNick(status.getNick());
            NewsApp.token.setUid(status.getUid());
            KLog.i(NewsApp.token.getToken());
            KLog.i("status:" + status.getUid());
            KLog.i("uid:" + NewsApp.token.getUid());
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

    @OnClick(R.id.bt_register)
    public void registerClick() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        if (name == null || name.equals("")) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email == null || email.equals("")) {
            Toast.makeText(this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password == null || password.equals("")) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.register(email, name, password);
    }

    @OnClick(R.id.iv_back)
    public void backClick() {
        finish();
    }

}
